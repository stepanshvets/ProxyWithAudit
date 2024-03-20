package ru.vk.testtask.controller.proxy;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.vk.testtask.dto.proxy.Album;
import ru.vk.testtask.service.ProxyService;

@RestController
@RequestMapping("/api/albums")
@RequiredArgsConstructor
public class AlbumsController {
    private final ProxyService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getAll() {
        return service.getResult("/albums");
    }

    @GetMapping(value={"/{id}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @Cacheable(value = "albums", key = "#id")
    public Object getById(@PathVariable Integer id) {
        return service.getResult("/albums/" + id);
    }

    @GetMapping(value={"/{id}/photos"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getPhotos(@PathVariable Integer id) {
        return service.getResult("/albums/" + id + "/photos");
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Object post(@RequestBody Album album) {
        return service.postResult("/albums", album);
    }

    @PutMapping(value = {"/{id}"})
    @CachePut(value = "albums", key = "#id")
    public Object put(@RequestBody Album album, @PathVariable Integer id) {
        return service.putResult("/albums/" + id, album);
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "albums", key = "#id")
    public void delete(@PathVariable Integer id) {
        service.deleteResult("/albums/" + id);
    }
}
