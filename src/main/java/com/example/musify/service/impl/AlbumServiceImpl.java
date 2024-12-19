package com.example.musify.service.impl;

import com.example.musify.dto.request.AlbumRequestDTO;
import com.example.musify.dto.response.AlbumResponseDTO;
import com.example.musify.entity.Album;
import com.example.musify.exception.ResourceNotFoundException;
import com.example.musify.mapper.AlbumMapper;
import com.example.musify.repository.AlbumRepository;
import com.example.musify.service.AlbumService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;
    private final AlbumMapper albumMapper;

    public AlbumServiceImpl(AlbumRepository albumRepository, AlbumMapper albumMapper) {
        this.albumRepository = albumRepository;
        this.albumMapper = albumMapper;
    }

    @Override
    @Transactional
    public AlbumResponseDTO createAlbum(AlbumRequestDTO albumRequestDTO) {
        Album album = albumMapper.toEntity(albumRequestDTO);
        Album savedAlbum = albumRepository.save(album);
        return albumMapper.toDto(savedAlbum);
    }

    @Override
    @Transactional(readOnly = true)
    public AlbumResponseDTO getAlbumById(String id) {
        Album album = albumRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Album not found with id: " + id));
        return albumMapper.toDto(album);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AlbumResponseDTO> getAllAlbums(Pageable pageable) {
        Page<Album> albumPage = albumRepository.findAll(pageable);
        return albumPage.map(albumMapper::toDto);
    }

    @Override
    @Transactional
    public AlbumResponseDTO updateAlbum(String id, AlbumRequestDTO albumRequestDTO) {
        Album album = albumRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Album not found with id: " + id));
        albumMapper.updateEntityFromDto(albumRequestDTO, album);
        Album updatedAlbum = albumRepository.save(album);
        return albumMapper.toDto(updatedAlbum);
    }

    @Override
    @Transactional
    public void deleteAlbum(String id) {
        if (!albumRepository.existsById(id)) {
            throw new ResourceNotFoundException("Album not found with id: " + id);
        }
        albumRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AlbumResponseDTO> searchAlbumsByTitle(String title, Pageable pageable) {
        Page<Album> albumPage = albumRepository.findByTitleContainingIgnoreCase(title, pageable);
        return albumPage.map(albumMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AlbumResponseDTO> searchAlbumsByArtist(String artist, Pageable pageable) {
        Page<Album> albumPage = albumRepository.findByArtistContainingIgnoreCase(artist, pageable);
        return albumPage.map(albumMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AlbumResponseDTO> filterAlbumsByYear(Integer year, Pageable pageable) {
        Page<Album> albumPage = albumRepository.findByYear(year, pageable);
        return albumPage.map(albumMapper::toDto);
    }
}

