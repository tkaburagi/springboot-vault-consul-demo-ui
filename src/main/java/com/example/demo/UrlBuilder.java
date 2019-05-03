package com.example.demo;


import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;

public class UrlBuilder {

    private final LoadBalancerClient loadBalancerClient;

    public UrlBuilder(LoadBalancerClient loadBalancerClient) {
        this.loadBalancerClient = loadBalancerClient;
    }

    public String getUrl() {
        return this.loadBalancerClient.choose("hashiboot-api").getUri().toString();
    }
}
