package com.manhpd.springasyncresttemplate.shared;

import com.manhpd.springasyncresttemplate.domain.domain_object.RequestContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRequestCallback;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.ResponseExtractor;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Future;

@Component
public class AsyncRestTemplateUtils {

    @Autowired
    private AsyncRestTemplate asyncRestTemplate;

    public Future<String> getRequest(RequestContent requestContent) {
        HttpHeaders headers = this.createHttpHeaders(requestContent);
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        AsyncRequestCallback requestCallback = arg0 -> System.out.println(arg0.getURI());
        ResponseExtractor<String> responseExtractor = arg0 -> arg0.getStatusText();

        ListenableFuture<String> future = this.asyncRestTemplate.execute(requestContent.getUrl(),
                HttpMethod.GET, requestCallback, responseExtractor);
        return future;
    }

    public Future<String> postRequest(RequestContent requestContent) {
        HttpHeaders headers = this.createHttpHeaders(requestContent);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        AsyncRequestCallback requestCallback = arg0 -> System.out.println(arg0.getURI());
        ResponseExtractor<String> responseExtractor = arg0 -> arg0.getStatusText();

        ListenableFuture<String> future = this.asyncRestTemplate.execute(requestContent.getUrl(),
                                                HttpMethod.POST, requestCallback, responseExtractor);
        return future;
    }

    // ========================== Utility methods =============================
    private  void setParametersToRequestHeader(HttpHeaders headers, Map<String, String> paramHeaders) {
        if (Objects.isNull(paramHeaders)) {
            System.out.println("Do not exist parameters in Http Header.");
            return;
        }

        for (Map.Entry<String, String> me : paramHeaders.entrySet()) {
            headers.set(me.getKey().toString(), me.getValue().toString());
        }
    }

    private HttpHeaders createHttpHeaders(RequestContent requestContent) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(Constant.AUTHORIZATION_FIELD, requestContent.getAccessToken());
        this.setParametersToRequestHeader(headers, requestContent.getHeaderParams());

        return headers;
    }

}
