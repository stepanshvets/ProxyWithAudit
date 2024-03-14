package ru.vk.testtask.controller.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.vk.testtask.dto.proxy.User;
import ru.vk.testtask.service.ProxyService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/users")
public class UsersController {
    @Autowired
    private ProxyService service;

    public String getURLValue(HttpServletRequest request) {
        String url = request.getRequestURI();
        return url;
    }

    @GetMapping(value = {"", "/", "/{id}", "/{id}/albums", "/{id}/posts"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object get(HttpServletRequest request) {
        String url = getURLValue(request).substring("/api".length());
        return service.getResult(url);
    }

    @PostMapping(value = {"", "/"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object post(@RequestBody User user) {
        return service.postResult("users", user);
    }

    @PutMapping(value = {"/{id}"})
    public Object put(@RequestBody User user, HttpServletRequest request) {
        String url = getURLValue(request).substring("/api".length());
        return service.putResult(url, user);
    }

    @DeleteMapping(value = {"/{id}"})
    public void delete(HttpServletRequest request) {
        String url = getURLValue(request).substring("/api".length());
        service.deleteResult(url);
    }

}
