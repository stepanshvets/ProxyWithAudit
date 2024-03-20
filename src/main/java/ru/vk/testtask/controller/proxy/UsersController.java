package ru.vk.testtask.controller.proxy;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.vk.testtask.dto.proxy.Post;
import ru.vk.testtask.dto.proxy.User;
import ru.vk.testtask.service.ProxyService;


@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UsersController {
    private final ProxyService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getAll() {
        return service.getResult("/users");
    }

    @GetMapping(value={"/{id}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @Cacheable(value = "users", key = "#id")
    public Object getById(@PathVariable Integer id) {
        return service.getResult("/users/" + id);
    }

    @GetMapping(value={"/{id}/albums"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getComments(@PathVariable Integer id) {
        return service.getResult("/users/" + id + "/albums");
    }

    @PostMapping(value = {""}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object post(@RequestBody User user) {
        return service.postResult("/users", user);
    }

    @PutMapping(value = {"/{id}"})
    @CachePut(value = "users", key = "#id")
    public Object put(@RequestBody User user, @PathVariable Integer id) {
        return service.putResult("/users/" + id, user);
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "users", key = "#id")
    public void delete(@PathVariable Integer id) {
        service.deleteResult("/users/" + id);
    }
}
