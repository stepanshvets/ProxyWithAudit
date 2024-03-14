package ru.vk.testtask.controller.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.vk.testtask.dto.proxy.Album;
import ru.vk.testtask.service.ProxyService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/albums")
public class AlbumsController {
    @Autowired
    private ProxyService service;

    public String getURLValue(HttpServletRequest request) {
        return request.getRequestURI();
    }

    @GetMapping(value = {"", "/", "/{id}", "/{id}/posts"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object get(HttpServletRequest request) {
        System.out.println();
        String url = getURLValue(request).substring("/api".length());
        return service.getResult(url);
    }

    @PostMapping(value = {"", "/"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object post(@RequestBody Album album) {
        return service.postResult("albums", album);
    }

    @PutMapping(value = {"/{id}"})
    public Object put(@RequestBody Album album, HttpServletRequest request) {
        String url = getURLValue(request).substring("/api".length());
        return service.putResult(url, album);
    }

    @DeleteMapping(value = {"/{id}"})
    public void delete(HttpServletRequest request) {
        String url = getURLValue(request).substring("/api".length());
        service.deleteResult(url);
    }

}
