package com.example.myapp.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.UUID;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(Collections.singletonList(new RequestIdInterceptor()));
        return restTemplate;
    }
}

class RequestIdInterceptor implements ClientHttpRequestInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(RequestIdInterceptor.class);
    private static final String REQUEST_ID_HEADER = "x-request-id";

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {

        HttpRequestWrapper requestWrapper = new HttpRequestWrapper(request);

        // Generate a new requestId if not present
        String requestId = MDC.get(REQUEST_ID_HEADER);
        if (requestId == null) {
            requestId = UUID.randomUUID().toString();
            MDC.put(REQUEST_ID_HEADER, requestId);
        }

        requestWrapper.getHeaders().set(REQUEST_ID_HEADER, requestId);

        // Log outgoing request
        logOutgoingRequest(requestWrapper, body);

        // Execute the request
        ClientHttpResponse response = execution.execute(requestWrapper, body);


        // Remove requestId from MDC after the request completes
        MDC.remove(REQUEST_ID_HEADER);

        return response;
    }

    private void logOutgoingRequest(HttpRequest request, byte[] body) {
        logger.info("Outgoing request: {} {} with body: {} and requestId: {}",
                request.getMethod(), request.getURI(), new String(body, StandardCharsets.UTF_8), MDC.get(REQUEST_ID_HEADER));
    }
}
