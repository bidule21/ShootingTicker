package de.janbrodda.shootingticker.client.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import de.janbrodda.shootingticker.client.settings.Settings;

public class WebRequest {

    public static enum Method {

        GET,
        POST
    }

    private static HttpClient CLIENT;
    private static int CONNECTION_TIMEOUT = 10 * 1000;
    private static Settings settings = Settings.get();

    public Method method;
    public String url;
    public Map<String, String> parameters = new HashMap<>();

    public WebRequest(Method method, String url) {
        if (CLIENT == null) {
            prepareHttpClient();
        }

        this.method = method;
        this.url = url;
    }

    public String load() throws IOException {
        HttpResponse response = null;

        switch (method) {
            case GET:
                response = loadGet();
                break;
            case POST:
                response = loadPost();
        }

        if (response == null) {
            return null;
        }

        switch (response.getStatusLine().getStatusCode()) {
            case 200:

                try {
                    BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while ((line = rd.readLine()) != null) {
                        result.append(line);
                    }

                    return result.toString();

                } catch (UnsupportedOperationException ex) {
                    return null;
                }

            default:
                throw new IOException("Webrequest finished with Error: " + response.getStatusLine());
        }
    }

    private void prepareHttpClient() {
        RequestConfig.Builder requestBuilder = RequestConfig.custom()
                .setConnectTimeout(CONNECTION_TIMEOUT)
                .setConnectionRequestTimeout(CONNECTION_TIMEOUT)
                .setSocketTimeout(CONNECTION_TIMEOUT)
                .setContentCompressionEnabled(false);

        CredentialsProvider proxyCredentials = new BasicCredentialsProvider();

        if (settings.useProxy) {
            requestBuilder.setProxy(new HttpHost(settings.proxyHost, settings.proxyPort));
            if (settings.proxyPass != null && settings.proxyUser != null) {
                UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(settings.proxyUser, settings.proxyPass);
                proxyCredentials.setCredentials(AuthScope.ANY, credentials);
            }
        }

        HttpClientBuilder builder = HttpClientBuilder.create();
        builder.setDefaultRequestConfig(requestBuilder.build());

        if (settings.useProxy) {
            CLIENT = builder.setDefaultCredentialsProvider(proxyCredentials).build();
        } else {
            CLIENT = builder.build();
        }
    }

    private HttpResponse loadGet() throws IOException {
        HttpGet request = new HttpGet(url);
        return CLIENT.execute(request);
    }

    private HttpResponse loadPost() throws IOException {
        HttpPost request = new HttpPost(url);

        ArrayList<NameValuePair> postParameters = new ArrayList<>();

        for (Entry<String, String> entry : parameters.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            postParameters.add(new BasicNameValuePair(key, value));
        }

        // This bit is EXTREMLY important for transmitting umlauts!
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(postParameters);
        entity.setContentType("application/x-www-form-urlencoded; charset=iso-8859-1");
        request.setEntity(entity);

        return CLIENT.execute(request);

    }
}
