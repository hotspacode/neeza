package io.github.hotspacode.neeza.transport.client.http;

import io.github.hotspacode.neeza.base.util.NeezaConstant;
import io.github.hotspacode.neeza.base.util.StringUtil;
import io.github.hotspacode.neeza.transport.client.http.config.TransportClientConfig;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.DefaultRedirectStrategy;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static io.github.hotspacode.neeza.base.config.NeezaBaseConfig.DEFAULT_CHARSET;

/**
 * @author moxingwang
 */
public class TransportClient {
    private CloseableHttpAsyncClient httpClient;

    public TransportClient() {
        IOReactorConfig ioConfig = IOReactorConfig.custom().setConnectTimeout(3000).setSoTimeout(10000)
                .setIoThreadCount(Runtime.getRuntime().availableProcessors() * 2).build();
        httpClient = HttpAsyncClients.custom().setRedirectStrategy(new DefaultRedirectStrategy() {
            @Override
            protected boolean isRedirectable(final String method) {
                return false;
            }
        }).setMaxConnTotal(4000).setMaxConnPerRoute(1000).setDefaultIOReactorConfig(ioConfig).build();
        httpClient.start();
    }

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
            return URLEncoder.encode(str, DEFAULT_CHARSET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getBody(HttpResponse response) throws Exception {
        Charset charset = null;
        try {
            String contentTypeStr = response.getFirstHeader("Content-type").getValue();
            if (StringUtil.isNotEmpty(contentTypeStr)) {
                ContentType contentType = ContentType.parse(contentTypeStr);
                charset = contentType.getCharset();
            }
        } catch (Exception ignore) {
        }
        return EntityUtils.toString(response.getEntity(), charset != null ? charset : Charset.forName(DEFAULT_CHARSET));
    }

    public CompletableFuture<String> executeCommand(String api, boolean useHttpPost) {
        return executeCommand(TransportClientConfig.getServerIp(), TransportClientConfig.getServerPort(), api, useHttpPost);
    }

    public CompletableFuture<String> executeCommand(String api, Map<String, String> params, boolean useHttpPost) {
        return executeCommand(TransportClientConfig.getServerIp(), TransportClientConfig.getServerPort(), api, params, useHttpPost);
    }

    public CompletableFuture<String> executeCommand(String ip, int port, String api, boolean useHttpPost) {
        return executeCommand(ip, port, api, null, useHttpPost);
    }


    public CompletableFuture<String> executeCommand(String ip, int port, String api, Map<String, String> params, boolean useHttpPost) {
        CompletableFuture<String> future = new CompletableFuture<>();
        if (StringUtil.isBlank(ip) || StringUtil.isBlank(api)) {
            future.completeExceptionally(new IllegalArgumentException("Bad URL or command name"));
            return future;
        }
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append("http://");
        urlBuilder.append(ip).append(':').append(port).append('/').append(api);
        if (params == null) {
            params = Collections.emptyMap();
        }

        if (!useHttpPost) {
            // Using GET in older versions, append parameters after url
            if (!params.isEmpty()) {
                if (urlBuilder.indexOf("?") == -1) {
                    urlBuilder.append('?');
                } else {
                    urlBuilder.append('&');
                }
                urlBuilder.append(queryString(params));
            }
            return executeCommand(new HttpGet(urlBuilder.toString()));
        } else {
            // Using POST
            return executeCommand(postRequest(urlBuilder.toString(), params));
        }
    }

    private CompletableFuture<String> executeCommand(HttpUriRequest request) {
        CompletableFuture<String> future = new CompletableFuture<>();
        httpClient.execute(request, new FutureCallback<HttpResponse>() {
            @Override
            public void completed(final HttpResponse response) {
                int statusCode = response.getStatusLine().getStatusCode();
                try {
                    String value = getBody(response);
                    if (isSuccess(statusCode)) {
                        future.complete(value);
                    } else {
                        if (isCommandNotFound(statusCode, value)) {
                            future.completeExceptionally(new Exception(request.getURI().getPath()));
                        } else {
                            future.completeExceptionally(new Exception(value));
                        }
                    }

                } catch (Exception ex) {
                    future.completeExceptionally(ex);
                    ex.printStackTrace();
                }
            }

            @Override
            public void failed(final Exception ex) {
                future.completeExceptionally(ex);
                ex.printStackTrace();
            }

            @Override
            public void cancelled() {
                future.complete(null);
            }
        });
        return future;
    }

    private boolean isSuccess(int statusCode) {
        return statusCode >= 200 && statusCode < 300;
    }

    public void close() throws Exception {
        httpClient.close();
    }

    private boolean isCommandNotFound(int statusCode, String body) {
        return statusCode == 400 && StringUtil.isNotEmpty(body) && body.contains(NeezaConstant.MSG_UNKNOWN_COMMAND_PREFIX);
    }

}
