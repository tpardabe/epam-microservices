package com.epam.song.repo;

import com.epam.song.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Integer> {

    void deleteByIdIn(List<Integer> ids);

    @Query(value = "SELECT id FROM song WHERE id in :ids", nativeQuery = true)
    List<Integer> findIdsIn(List<Integer> ids);
}
