package com.example.musify.service.impl;

import com.example.musify.dto.request.SongRequestDTO;
import com.example.musify.dto.response.SongResponseDTO;
import com.example.musify.entity.Album;
import com.example.musify.entity.Song;
import com.example.musify.exception.ResourceNotFoundException;
import com.example.musify.mapper.SongMapper;
import com.example.musify.repository.AlbumRepository;
import com.example.musify.repository.SongRepository;
import com.example.musify.service.SongService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;
    private final SongMapper songMapper;

    public SongServiceImpl(SongRepository songRepository, AlbumRepository albumRepository, SongMapper songMapper) {
        this.songRepository = songRepository;
        this.albumRepository = albumRepository;
        this.songMapper = songMapper;
    }

    @Override
    @Transactional
    public SongResponseDTO createSong(SongRequestDTO songRequestDTO) {
        Album album = albumRepository.findById(songRequestDTO.getAlbumId())
                .orElseThrow(() -> new ResourceNotFoundException("Album not found with id: " + songRequestDTO.getAlbumId()));
        Song song = songMapper.toEntity(songRequestDTO);
        song.setAlbum(album);
        Song savedSong = songRepository.save(song);
        return songMapper.toDto(savedSong);
    }

    @Override
    @Transactional(readOnly = true)
    public SongResponseDTO getSongById(String id) {
        Song song = songRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Song not found with id: " + id));
        return songMapper.toDto(song);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SongResponseDTO> getAllSongs(Pageable pageable) {
        Page<Song> songPage = songRepository.findAll(pageable);
        return songPage.map(songMapper::toDto);
    }

    @Override
    @Transactional
    public SongResponseDTO updateSong(String id, SongRequestDTO songRequestDTO) {
        Song song = songRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Song not found with id: " + id));
        Album album = albumRepository.findById(songRequestDTO.getAlbumId())
                .orElseThrow(() -> new ResourceNotFoundException("Album not found with id: " + songRequestDTO.getAlbumId()));
        songMapper.updateEntityFromDto(songRequestDTO, song);
        song.setAlbum(album);
        Song updatedSong = songRepository.save(song);
        return songMapper.toDto(updatedSong);
    }

    @Override
    @Transactional
    public void deleteSong(String id) {
        if (!songRepository.existsById(id)) {
            throw new ResourceNotFoundException("Song not found with id: " + id);
        }
        songRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SongResponseDTO> searchSongsByTitle(String title, Pageable pageable) {
        Page<Song> songPage = songRepository.findByTitleContainingIgnoreCase(title, pageable);
        return songPage.map(songMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SongResponseDTO> getSongsByAlbumId(String albumId, Pageable pageable) {
        Page<Song> songPage = songRepository.findByAlbumId(albumId, pageable);
        return songPage.map(songMapper::toDto);
    }
}

