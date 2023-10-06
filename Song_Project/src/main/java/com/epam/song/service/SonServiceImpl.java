package com.epam.song.service;


import com.epam.song.entity.Song;
import com.epam.song.repo.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SonServiceImpl implements SongService {

    private final SongRepository songRepository;
    @Override
    public int createMetadata(Song song) {
        return songRepository.save(song).getId();
    }

    @Override
    public Song getMetadata(Integer id) {
        return songRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("The song metadata with the specified id does not exist"));
    }

    @Override
    public List<Integer> deleteMetadata(List<Integer> songIds) {
        songRepository.deleteByIdIn(songIds);

        List<Integer> idsLeft = songRepository.findIdsIn(songIds);
        songIds.removeAll(idsLeft);
        return songIds;
    }
}
