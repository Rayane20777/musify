package com.example.musify.service;

import com.example.musify.dto.request.SongRequestDTO;
import com.example.musify.dto.response.SongResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SongService {
    SongResponseDTO createSong(SongRequestDTO songRequestDTO);
    SongResponseDTO getSongById(String id);
    Page<SongResponseDTO> getAllSongs(Pageable pageable);
    SongResponseDTO updateSong(String id, SongRequestDTO songRequestDTO);
    void deleteSong(String id);
    Page<SongResponseDTO> searchSongsByTitle(String title, Pageable pageable);
    Page<SongResponseDTO> getSongsByAlbumId(String albumId, Pageable pageable);
}

