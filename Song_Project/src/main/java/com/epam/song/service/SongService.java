package com.epam.song.service;

import com.epam.song.entity.Song;

import java.util.List;

public interface SongService {

    int createMetadata(Song song);

    Song getMetadata(Integer id);

    List<Integer> deleteMetadata(List<Integer> songIds);
}
