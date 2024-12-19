package com.example.musify.controller;

import com.example.musify.dto.request.AlbumRequestDTO;
import com.example.musify.dto.response.AlbumResponseDTO;
import com.example.musify.service.AlbumService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AlbumController {

    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @PostMapping("/admin/albums")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AlbumResponseDTO> createAlbum(@Valid @RequestBody AlbumRequestDTO albumRequestDTO) {
        AlbumResponseDTO createdAlbum = albumService.createAlbum(albumRequestDTO);
        return new ResponseEntity<>(createdAlbum, HttpStatus.CREATED);
    }

    @GetMapping("/user/albums/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<AlbumResponseDTO> getAlbumById(@PathVariable String id) {
        AlbumResponseDTO album = albumService.getAlbumById(id);
        return ResponseEntity.ok(album);
    }

    @GetMapping("/user/albums")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Page<AlbumResponseDTO>> getAllAlbums(Pageable pageable) {
        Page<AlbumResponseDTO> albums = albumService.getAllAlbums(pageable);
        return ResponseEntity.ok(albums);
    }

    @PutMapping("/admin/albums/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AlbumResponseDTO> updateAlbum(@PathVariable String id, @Valid @RequestBody AlbumRequestDTO albumRequestDTO) {
        AlbumResponseDTO updatedAlbum = albumService.updateAlbum(id, albumRequestDTO);
        return ResponseEntity.ok(updatedAlbum);
    }

    @DeleteMapping("/admin/albums/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteAlbum(@PathVariable String id) {
        albumService.deleteAlbum(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/albums/search")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Page<AlbumResponseDTO>> searchAlbums(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String artist,
            @RequestParam(required = false) Integer year,
            Pageable pageable) {
        Page<AlbumResponseDTO> albums;
        if (title != null) {
            albums = albumService.searchAlbumsByTitle(title, pageable);
        } else if (artist != null) {
            albums = albumService.searchAlbumsByArtist(artist, pageable);
        } else if (year != null) {
            albums = albumService.filterAlbumsByYear(year, pageable);
        } else {
            albums = albumService.getAllAlbums(pageable);
        }
        return ResponseEntity.ok(albums);
    }
}

