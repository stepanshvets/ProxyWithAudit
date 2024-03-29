package ru.vk.testtask.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("/free")
    public ResponseEntity<?> free() {
        Map<Object, Object> response = new HashMap<>();
        response.put("free", "ok");
        response.put("time", new Date());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/foo")
//    @PreAuthorize("hasAnyAuthority(\"" + Permission.Constants.VIEWER_POSTS_VALUE + "\")")
    public ResponseEntity<?> foo() {
        Map<Object, Object> response = new HashMap<>();
        response.put("foo", "ok");
        response.put("time", new Date());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/bar")
//    @PreAuthorize("hasAnyAuthority(\"" + Permission.Constants.VIEWER_POSTS_VALUE + "\")")
    public ResponseEntity<?> bar() {
        Map<Object, Object> response = new HashMap<>();
        response.put("bar", "ok");
        response.put("time", new Date());
        return ResponseEntity.ok(response);
    }
}
