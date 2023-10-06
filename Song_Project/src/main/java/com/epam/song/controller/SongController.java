package com.epam.song.controller;


import com.epam.song.entity.Song;
import com.epam.song.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("songs")
public class SongController {

    private final SongService songService;

    @PostMapping
    public ResponseEntity<Integer> createSongMetadata(@RequestBody Song song) {
        return ResponseEntity.ok()
                .body(songService.createMetadata(song));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Song> getMetadata(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok()
                .body(songService.getMetadata(id));
    }

    @DeleteMapping
    public ResponseEntity<List<Integer>> deleteMetadata(@RequestParam(name = "id") List<Integer> songIds) {
        return ResponseEntity.ok()
                .body(songService.deleteMetadata(songIds));
    }

}
