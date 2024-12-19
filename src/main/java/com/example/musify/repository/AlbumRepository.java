package com.example.musify.repository;

import com.example.musify.entity.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AlbumRepository extends MongoRepository<Album, String> {
    Page<Album> findByTitleContainingIgnoreCase(String title, Pageable pageable);
    Page<Album> findByArtistContainingIgnoreCase(String artist, Pageable pageable);
    Page<Album> findByYear(Integer year, Pageable pageable);
}


