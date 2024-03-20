package ru.vk.testtask.controller.proxy;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.vk.testtask.dto.proxy.Album;
import ru.vk.testtask.dto.proxy.Post;
import ru.vk.testtask.service.ProxyService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostsController {
    private final ProxyService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getAll() {
        return service.getResult("/posts");
    }

    @GetMapping(value={"/{id}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @Cacheable(value = "posts", key = "#id")
    public Object getById(@PathVariable Integer id) {
        return service.getResult("/posts/" + id);
    }

    @GetMapping(value={"/{id}/comments"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getComments(@PathVariable Integer id) {
        return service.getResult("/posts/" + id + "/comments");
    }

    @PostMapping(value = {""}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object post(@RequestBody Post post) {
        return service.postResult("/posts", post);
    }

    @PutMapping(value = {"/{id}"})
    @CachePut(value = "posts", key = "#id")
    public Object put(@RequestBody Post post, @PathVariable Integer id) {
        return service.putResult("/posts/" + id, post);
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "posts", key = "#id")
    public void delete(@PathVariable Integer id) {
        service.deleteResult("/posts/" + id);
    }
}
