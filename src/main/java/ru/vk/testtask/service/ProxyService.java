package ru.vk.testtask.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProxyService {
    private final RestTemplate restTemplate;
    private final String urlStart = "https://jsonplaceholder.typicode.com/";

    public ProxyService() {
        this.restTemplate = new RestTemplate();
    }

    public Object getResult(String url) {
        return restTemplate.getForObject(urlStart + url, String.class);
    }

    public Object postResult(String url, Object object) {
        return restTemplate.postForObject(urlStart + url, new HttpEntity<>(object), Object.class);
    }

    public Object putResult(String url, Object object) {
        return restTemplate.exchange(urlStart + url, HttpMethod.PUT, new HttpEntity<>(object), Object.class).getBody();
    }

    public void deleteResult(String url) {
        restTemplate.delete(urlStart + url);
    }

}
