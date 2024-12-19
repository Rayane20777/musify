package com.example.musify.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SongRequestDTO {
    @NotBlank(message = "Title is required")
    private String title;

    @NotNull(message = "Duration is required")
    @Min(value = 1, message = "Duration must be at least 1 second")
    private Integer duration;

    @NotNull(message = "Track number is required")
    @Min(value = 1, message = "Track number must be at least 1")
    private Integer trackNumber;

    @NotBlank(message = "Album ID is required")
    private String albumId;
}
