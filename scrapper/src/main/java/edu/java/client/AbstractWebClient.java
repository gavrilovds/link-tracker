package edu.java.client;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

public abstract class AbstractWebClient {

    protected final HttpServiceProxyFactory factory;

    public AbstractWebClient(String baseUrl) {
        WebClient webClient = WebClient.builder().baseUrl(baseUrl).build();
        WebClientAdapter adapter = WebClientAdapter.create(webClient);
        factory = HttpServiceProxyFactory.builderFor(adapter).build();
    }
}
