package com.epam.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MusicRepository extends JpaRepository<Music, Integer> {
    void deleteByIdIn(List<Integer> ids);

    @Query(value = "SELECT id FROM music WHERE id in :ids", nativeQuery = true)
    List<Integer> findIdsIn(List<Integer> ids);
}
