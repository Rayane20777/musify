package com.example.musify.controller;

import com.example.musify.dto.request.SongRequestDTO;
import com.example.musify.dto.response.SongResponseDTO;
import com.example.musify.service.SongService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SongController {

    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @PostMapping("/admin/songs")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SongResponseDTO> createSong(@Valid @RequestBody SongRequestDTO songRequestDTO) {
        SongResponseDTO createdSong = songService.createSong(songRequestDTO);
        return new ResponseEntity<>(createdSong, HttpStatus.CREATED);
    }

    @GetMapping("/user/songs/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<SongResponseDTO> getSongById(@PathVariable String id) {
        SongResponseDTO song = songService.getSongById(id);
        return ResponseEntity.ok(song);
    }

    @GetMapping("/user/songs")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Page<SongResponseDTO>> getAllSongs(Pageable pageable) {
        Page<SongResponseDTO> songs = songService.getAllSongs(pageable);
        return ResponseEntity.ok(songs);
    }

    @PutMapping("/admin/songs/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SongResponseDTO> updateSong(@PathVariable String id, @Valid @RequestBody SongRequestDTO songRequestDTO) {
        SongResponseDTO updatedSong = songService.updateSong(id, songRequestDTO);
        return ResponseEntity.ok(updatedSong);
    }

    @DeleteMapping("/admin/songs/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteSong(@PathVariable String id) {
        songService.deleteSong(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/songs/search")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Page<SongResponseDTO>> searchSongs(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String albumId,
            Pageable pageable) {
        Page<SongResponseDTO> songs;
        if (title != null) {
            songs = songService.searchSongsByTitle(title, pageable);
        } else if (albumId != null) {
            songs = songService.getSongsByAlbumId(albumId, pageable);
        } else {
            songs = songService.getAllSongs(pageable);
        }
        return ResponseEntity.ok(songs);
    }
}