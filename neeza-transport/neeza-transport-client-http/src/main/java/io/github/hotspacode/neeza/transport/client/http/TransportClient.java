package io.github.hotspacode.neeza.transport.client.http;

import io.github.hotspacode.neeza.base.config.NeezaBaseConfig;
import io.github.hotspacode.neeza.base.util.StringUtil;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author moxingwang
 */
public class TransportClient {

    private StringBuilder queryString(Map<String, String> params) {
        StringBuilder queryStringBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (StringUtil.isEmpty(entry.getValue())) {
                continue;
            }
            String name = urlEncode(entry.getKey());
            String value = urlEncode(entry.getValue());
            if (name != null && value != null) {
                if (queryStringBuilder.length() > 0) {
                    queryStringBuilder.append('&');
                }
                queryStringBuilder.append(name).append('=').append(value);
            }
        }
        return queryStringBuilder;
    }

    private HttpUriRequest postRequest(String url, Map<String, String> params) {
        HttpPost httpPost = new HttpPost(url);
        if (params != null && params.size() > 0) {
            List<NameValuePair> list = new ArrayList<>(params.size());
            for (Map.Entry<String, String> entry : params.entrySet()) {
                list.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(list));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return null;
            }
        }
        return httpPost;
    }

    private String urlEncode(String str) {
        try {
            return URLEncoder.encode(str, NeezaBaseConfig.DEFAULT_CHARSET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }


}
