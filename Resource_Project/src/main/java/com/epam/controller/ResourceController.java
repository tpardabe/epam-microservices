package com.epam.controller;

import org.springframework.core.io.ByteArrayResource;
import  org.springframework.core.io.Resource;
import com.epam.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("resources")
public class ResourceController {


    private final ResourceService resourceService;



    @PostMapping
    public ResponseEntity<Integer> upload(@RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok().body(resourceService.upload(file));
    }

    @GetMapping(value = "/{id}", produces = "audio/mpeg")
    public ResponseEntity<Resource> getResource(@PathVariable(name = "id") Integer id) {
        ByteArrayResource resource = new ByteArrayResource(resourceService.get(id).getData());

        return ResponseEntity.ok()
                .body(resource);
    }

    @DeleteMapping
    public ResponseEntity<List<Integer>> delete(@RequestParam(name = "id")List<Integer> ids) {
        return ResponseEntity.ok()
                .body(resourceService.delete(ids));
    }
}
