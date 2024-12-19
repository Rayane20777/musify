package com.example.musify.service;

import com.example.musify.dto.request.AlbumRequestDTO;
import com.example.musify.dto.response.AlbumResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AlbumService {
    AlbumResponseDTO createAlbum(AlbumRequestDTO albumRequestDTO);
    AlbumResponseDTO getAlbumById(String id);
    Page<AlbumResponseDTO> getAllAlbums(Pageable pageable);
    AlbumResponseDTO updateAlbum(String id, AlbumRequestDTO albumRequestDTO);
    void deleteAlbum(String id);
    Page<AlbumResponseDTO> searchAlbumsByTitle(String title, Pageable pageable);
    Page<AlbumResponseDTO> searchAlbumsByArtist(String artist, Pageable pageable);
    Page<AlbumResponseDTO> filterAlbumsByYear(Integer year, Pageable pageable);
}

