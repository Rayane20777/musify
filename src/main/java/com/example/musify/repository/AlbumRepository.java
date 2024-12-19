package com.example.musify.repository;

import com.example.musify.entity.Album;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends MongoRepository<Album, String> {
    List<Album> findByTitleContainingIgnoreCase(String title);
    List<Album> findByArtistContainingIgnoreCase(String artist);
    List<Album> findByYear(Integer year);
}

