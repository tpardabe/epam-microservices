package com.epam.feign;

import com.epam.dto.SongDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(/*name = "SONG-SERVICE",*/ value = "song", url = "${song-service.url}")
//TODO: need uri for song service
public interface SongService {

    @PostMapping(value = "/songs")
    int saveMetadata(@RequestBody SongDTO songDTO);
}
