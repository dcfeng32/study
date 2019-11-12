package com.feng.backstage.utils.httputils;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * 完全没看懂 ？？？
 * Create on 2019/11/9 9:34
 * @author Administrator
 */
public class HttpUtil {

    private final static Logger logger = LoggerFactory.getLogger("HttpUtil.class");

    public static int timeOut = 30000;

    /**
     * Get请求
     *
     * @param url
     * @return
     */
    public static String executeGet(String url) {
        try {
            HttpGet httpGet = new HttpGet(url);
            // 该方法已过时 HttpClient httpClient = new DefaultHttpClient();
            HttpClient client = HttpClientBuilder.create().build();
            HttpResponse httpResponse;
            httpResponse = client.execute(httpGet);
            String resultContent = new Utf8ResponseHandler()
                    .handleResponse(httpResponse);
            return resultContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Post请求
     *
     * @param url
     * @param body
     * @return
     */
    public static String executePost(String url, String body) {
        try {
            HttpPost httpPost = new HttpPost(url);
            StringEntity entity = new StringEntity(body, "UTF-8");
            httpPost.setEntity(entity);
            // HttpClient httpClient = new DefaultHttpClient();
            HttpClient client = HttpClientBuilder.create().build();
            HttpResponse httpResponse = client.execute(httpPost);
            String resultContent = new Utf8ResponseHandler()
                    .handleResponse(httpResponse);
            logger.info("result=" + resultContent);
            return resultContent;
        } catch (Exception e) {
            System.out.println(e.getMessage() + e.getCause() + e.getStackTrace());
            throw new RuntimeException(e);
        }
    }

    /**
     * 静态内部类，utf-8编码
     */

    static class Utf8ResponseHandler implements ResponseHandler<String> {
        private static final Integer TIME_OUT = 300;

        @Override
        public String handleResponse(final HttpResponse response)
                throws IOException {
            final StatusLine statusLine = response.getStatusLine();
            final HttpEntity entity = response.getEntity();
            if (statusLine.getStatusCode() >= TIME_OUT) {
                EntityUtils.consume(entity);
                throw new HttpResponseException(statusLine.getStatusCode(),
                        statusLine.getReasonPhrase());
            }
            return entity == null ? null : EntityUtils
                    .toString(entity, "UTF-8");
        }
    }

    public static HttpRespObject sendPost(String url, String constant, Map<String, String> headers) {
        int statusCode = 0;
        String rspBody = "";
        CloseableHttpClient client = SslUtil.createSslClientDefault();
        HttpPut put = new HttpPut(url);
        HttpPost post = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(1000)
                .setConnectTimeout(timeOut).setSocketTimeout(4000).build();
        post.setConfig(requestConfig);

        if (headers != null && headers.size() > 0) {
            post.setHeaders(assemblyHeader(headers));
        }
        InputStream in = null;
        CloseableHttpResponse response = null;
        try {
            StringEntity sEntity = new StringEntity(constant);
            put.setEntity(sEntity);
            response = client.execute(put);
            statusCode = response.getStatusLine().getStatusCode();
            HttpEntity entity = response.getEntity();
            in = entity.getContent();
            rspBody = inputStream2Str(in);
            put.abort();
        } catch (IOException e) {
            logger.warn(e.getMessage(), e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (response != null) {
                    response.close();
                }
                if (client != null) {
                    client.close();
                }
            } catch (IOException e) {
                statusCode = -1;
                rspBody = e.getMessage();
                logger.warn(e.getMessage(), e);
            }
        }
        HttpRespObject respObject = handleRsp(statusCode, url, rspBody, "Put");
        return respObject;
    }

    private static HttpRespObject handleRsp(int statusCode, String url, String content, String method) {
        boolean success = false;
        switch (statusCode) {
            case 0:
                logger.error("请求api：" + url + " 时响应超时[" + method + "]，超时时间=" + timeOut);
                break;
            case 200:
            case 201:
            case 202:
            case 203:
                success = true;
                break;
            default:
                logger.warn("请求api：" + url + " 时异常[" + method + "]，statusCode=" + statusCode + "响应内容=" + content);
        }

        return new HttpRespObject(success, statusCode, content);
    }

    private static String inputStream2Str(InputStream is) {
        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        StringBuffer buffer = new StringBuffer();
        String line = "";

        try {
            while ((line = in.readLine()) != null) {
                buffer.append(line);
            }
        } catch (IOException var5) {
            logger.error("===read input stream to string error===>>>", var5);
        }
        return buffer.toString();
    }

    private static Header[] assemblyHeader(Map<String, String> headers) {
        Header[] allheader = new BasicHeader[headers.size()];
        int i = 0;
        for (String str : headers.keySet()) {
            allheader[i] = new BasicHeader(str, headers.get(str));
            i++;
        }
        return allheader;
    }

}
