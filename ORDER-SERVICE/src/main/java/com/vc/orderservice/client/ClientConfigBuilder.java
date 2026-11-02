package com.vc.orderservice.client;

import lombok.Builder;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
@Builder
public record ClientConfigBuilder<T>(
        String baseUrl,
        Class<T> clientClass
) {
    public T build(RestClient.Builder restClientBuilder){
        RestClient restClient = restClientBuilder.baseUrl(baseUrl).build();

        RestClientAdapter adapter = RestClientAdapter.create(restClient);

        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(clientClass);
    }
}
