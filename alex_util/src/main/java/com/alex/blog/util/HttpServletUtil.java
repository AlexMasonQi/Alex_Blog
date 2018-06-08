package com.alex.blog.util;

import org.apache.http.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * HttpServletUtil
 *
 * @author ysj
 * @Date: 2015-1-30 下午2:07:55
 */
public class HttpServletUtil
{
    private static CloseableHttpClient httpclient;
    private static RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000).build();
    private final static Logger LOG = LoggerFactory.getLogger(HttpServletUtil.class);

    /**
     * Post:访问Web服务并返回数据字符串
     *
     * @param params 向服务器端传的参数
     * @param url
     * @return String 数据字符串
     * @throws Exception
     */
    public static String doPost(List<NameValuePair> params, String url) throws Exception
    {
        String result = null;
        httpclient = PoolManager.getHttpClient();
        HttpPost httpPost = new HttpPost(url);

        HttpEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
        httpPost.setEntity(entity);
        //设置请求和传输超时时间
        httpPost.setConfig(requestConfig);
        CloseableHttpResponse httpResp = httpclient.execute(httpPost);
        try
        {
            int statusCode = httpResp.getStatusLine().getStatusCode();
            // 判断是够请求成功
            if (statusCode == HttpStatus.SC_OK)
            {
                LOG.debug("状态码:" + statusCode);
                LOG.debug(url + "请求成功!");
                // 获取返回的数据
                result = EntityUtils.toString(httpResp.getEntity(), "UTF-8");
            }
            else
            {
                LOG.debug("状态码:" + httpResp.getStatusLine().getStatusCode());
                LOG.debug(url + "HttpPost方式请求失败!");
            }
        }
        finally
        {
            httpResp.close();
            httpPost.releaseConnection();
//            httpclient.close();
        }
        return result;
    }

    /**
     * Get:访问数据库并返回数据字符串
     *
     * @param url
     * @return String 数据字符串
     * @throws Exception
     */
    public static String doGet(String url) throws Exception
    {
        String result = null;
        httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        //设置请求和传输超时时间
        httpGet.setConfig(requestConfig);
        CloseableHttpResponse httpResp = httpclient.execute(httpGet);
        try
        {
            int statusCode = httpResp.getStatusLine().getStatusCode();
            // 判断是够请求成功
            if (statusCode == HttpStatus.SC_OK)
            {
                LOG.debug("状态码:" + statusCode);
                LOG.debug("请求成功!" + url);
                // 获取返回的数据
                result = EntityUtils.toString(httpResp.getEntity(), "UTF-8");
            }
            else
            {
                LOG.debug("状态码:" + httpResp.getStatusLine().getStatusCode());
                LOG.debug("HttpGet方式请求失败!" + url);
            }
        }
        finally
        {
            httpResp.close();
            httpGet.releaseConnection();
//            httpclient.close();
        }
        return result;
    }

    /**
     * Get:访问数据库并返回数据字符串,指定host
     *
     * @param url
     * @return String 数据字符串
     * @throws Exception
     */
    public static String doGetWithHost(String url, String host) throws Exception
    {
        String result = null;
        httpclient = HttpClients.createDefault();
        HttpHost httpHost = HttpHost.create(host);
        HttpGet httpGet = new HttpGet(url);
        //设置请求和传输超时时间
        httpGet.setConfig(requestConfig);
        CloseableHttpResponse httpResp = httpclient.execute(httpHost, httpGet);
        try
        {
            int statusCode = httpResp.getStatusLine().getStatusCode();
            // 判断是够请求成功
            if (statusCode == HttpStatus.SC_OK)
            {
                LOG.debug("状态码:" + statusCode);
                LOG.debug("请求成功!" + url);
                // 获取返回的数据
                result = EntityUtils.toString(httpResp.getEntity(), "UTF-8");
            }
            else
            {
                LOG.debug("状态码:" + httpResp.getStatusLine().getStatusCode());
                LOG.debug("HttpGet方式请求失败!" + url);
            }
        }
        finally
        {
            httpResp.close();
            httpGet.releaseConnection();
//            httpclient.close();
        }
        return result;
    }

    /**
     * @param
     * @return
     * @description 带请求头的post请求
     * @author lishibin
     * @date 2017/11/15 10:49
     */
    public static String doPostWithHeader(List<NameValuePair> params, String url, Map<String, String> header) throws Exception
    {
        String result = null;
        CloseableHttpResponse httpResp = null;
        httpclient = PoolManager.getHttpClient();
        HttpPost httpPost = new HttpPost(url);
        // 设置请求的header
        httpPost.addHeader("AppKey", header.get("AppKey"));
        httpPost.addHeader("Nonce", header.get("Nonce"));
        httpPost.addHeader("CurTime", header.get("CurTime"));
        httpPost.addHeader("CheckSum", header.get("CheckSum"));
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        HttpEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
        httpPost.setEntity(entity);
        //设置请求和传输超时时间
        httpPost.setConfig(requestConfig);
        try
        {
            httpResp = httpclient.execute(httpPost);
            int statusCode = httpResp.getStatusLine().getStatusCode();
            // 判断是够请求成功
            if (statusCode == HttpStatus.SC_OK)
            {
                LOG.debug("状态码:" + statusCode);
                LOG.debug(url + "请求成功!");
                // 获取返回的数据
                result = EntityUtils.toString(httpResp.getEntity(), "UTF-8");
            }
            else
            {
                LOG.debug("状态码:" + httpResp.getStatusLine().getStatusCode());
                LOG.debug(url + "HttpPost方式请求失败!");
            }
        }
        finally
        {
            if (httpResp != null)
            {
                httpResp.close();
            }
            httpPost.releaseConnection();
//            httpclient.close();
        }
        return result;
    }

    /**
     * @param
     * @return java.lang.String
     * @description 数据格式为Json字符串的post请求
     * @author lishibin
     * @date 2018/2/27 19:20
     */
    public static String doPostWithJson(String entityStr, String url) throws Exception
    {
        String result = null;
        httpclient = PoolManager.getHttpClient();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-Type", "application/json");
        StringEntity entity = new StringEntity(entityStr, Consts.UTF_8);
        httpPost.setEntity(entity);
        //设置请求和传输超时时间
        httpPost.setConfig(requestConfig);
        CloseableHttpResponse httpResp = httpclient.execute(httpPost);
        try
        {
            int statusCode = httpResp.getStatusLine().getStatusCode();
            // 判断是够请求成功
            if (statusCode == HttpStatus.SC_OK)
            {
                LOG.debug("状态码:" + statusCode);
                LOG.debug("请求成功!" + url);
                // 获取返回的数据
                result = EntityUtils.toString(httpResp.getEntity(), "UTF-8");
            }
            else
            {
                LOG.debug("状态码:" + httpResp.getStatusLine().getStatusCode());
                LOG.debug("HttpPost方式请求失败!" + url);
            }
        }
        finally
        {
            httpResp.close();
            httpPost.releaseConnection();
//            httpclient.close();
        }
        return result;
    }

    /**
     * 自定义超时时间
     *
     * @return : java.lang.String
     * @author:hushihai
     * @date:10:33 2018/5/15
     * @params:[params, url, config]
     */
    public static String doPost(List<NameValuePair> params, String url, RequestConfig config) throws Exception
    {
        String result = null;
        httpclient = PoolManager.getHttpClient();
        HttpPost httpPost = new HttpPost(url);

        HttpEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
        httpPost.setEntity(entity);
        //设置请求和传输超时时间
        httpPost.setConfig(config);
        CloseableHttpResponse httpResp = httpclient.execute(httpPost);
        try
        {
            int statusCode = httpResp.getStatusLine().getStatusCode();
            // 判断是够请求成功
            if (statusCode == HttpStatus.SC_OK)
            {
                LOG.debug("状态码:" + statusCode);
                LOG.debug(url + "请求成功!");
                // 获取返回的数据
                result = EntityUtils.toString(httpResp.getEntity(), "UTF-8");
            }
            else
            {
                LOG.debug("状态码:" + httpResp.getStatusLine().getStatusCode());
                LOG.debug(url + "HttpPost方式请求失败!");
            }
        }
        finally
        {
            httpResp.close();
            httpPost.releaseConnection();
//            httpclient.close();
        }
        return result;
    }
}
