package org.example.laji.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Service
public class BaiduAIService {
    
    @Value("${baidu.api.key:}")
    private String apiKey;
    
    @Value("${baidu.secret.key:}")
    private String secretKey;
    
    @Value("${baidu.easydl.url:}")
    private String easyDLUrl;
    
    private final OkHttpClient client;
    private final Gson gson;
    
    public BaiduAIService() {
        this.client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
        this.gson = new Gson();
    }
    
    /**
     * 获取百度AI access token
     */
    public String getAccessToken() throws IOException {
        String url = String.format(
            "https://aip.baidubce.com/oauth/2.0/token?grant_type=client_credentials&client_id=%s&client_secret=%s",
            apiKey, secretKey
        );
        
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("获取access token失败: " + response);
            }
            
            String responseBody = response.body().string();
            JsonObject jsonObject = gson.fromJson(responseBody, JsonObject.class);
            return jsonObject.get("access_token").getAsString();
        }
    }
    
    /**
     * 调用百度EasyDL进行垃圾识别
     * @param imageBase64 图片的Base64编码
     * @return 识别结果JSON字符串
     */
    public String recognizeGarbage(String imageBase64) throws IOException {
        String accessToken = getAccessToken();
        
        // 构建请求体
        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("image", imageBase64);
        
        RequestBody body = RequestBody.create(
            requestBody.toString(),
            MediaType.parse("application/json")
        );
        
        String url = easyDLUrl + "?access_token=" + accessToken;
        
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("垃圾识别失败: " + response);
            }
            
            return response.body().string();
        }
    }
    
    /**
     * 解析识别结果
     */
    public JsonObject parseRecognitionResult(String resultJson) {
        return gson.fromJson(resultJson, JsonObject.class);
    }
}

