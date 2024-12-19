package com.example.musify.entity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "songs")
@NoArgsConstructor
@AllArgsConstructor
public class Song {

    @Id
    private String id;

    @NotBlank(message = "Title is required")
    private String title;

    @NotNull(message = "Duration is required")
    @Min(value = 1, message = "Duration must be at least 1 second")
    private Integer duration;

    @NotNull(message = "Track number is required")
    @Min(value = 1, message = "Track number must be at least 1")
    private Integer trackNumber;

    @DBRef
    private Album album;
}
