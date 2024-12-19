package com.example.musify.repository;

import com.example.musify.entity.Song;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends MongoRepository<Song, String> {
    List<Song> findByTitleContainingIgnoreCase(String title);
    List<Song> findByAlbumId(String albumId);
}
