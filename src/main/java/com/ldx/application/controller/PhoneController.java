package com.ldx.application.controller;

import com.ldx.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

@RestController
@RequestMapping("query")
@Slf4j
public class PhoneController {
    // 电话查询数据
    // 138API TOKEN
    String TOKEN_138 = "0c96c8d357f8536bfb531b3787f2600b";
    String QUERY_PHONE_LOCATION = "https://www.baifubao.com/callback?cmd=1059&callback=phone&phone=";
    String Params = "&datatype=text";

    @GetMapping("phone")
    private ResponseResult<String> get(@RequestParam("phone") String phone) throws IOException {
        log.info("手机号:{}", phone);
        InputStream inputStream = null;
        String resultJsonData = "";
        URL url = new URL(QUERY_PHONE_LOCATION + phone);
        System.out.println(url);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setDoInput(true);
        urlConnection.setDoOutput(true);
        urlConnection.setRequestMethod("GET");
        urlConnection.setRequestProperty("token", TOKEN_138);
        urlConnection.connect();
        if (urlConnection.getResponseCode() != 200) {
            return ResponseResult.errorResult(urlConnection.getResponseCode(), urlConnection.getResponseMessage());
        } else {
            byte[] bytes = new byte[1024];
            inputStream = urlConnection.getInputStream();
            int readLen = 0;
            while ((readLen = inputStream.read(bytes)) != -1) {
                resultJsonData += new String(bytes, 0, readLen);
            }
            log.info("手机号返回的信息:{}", resultJsonData);
            return ResponseResult.okResult(resultJsonData);
        }
    }
}
