package com.vidclick.API.models;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class FundraisingPost {
    @Id
    private Long id;
    private List<String> photos;
    private String title;
    private String bankAccountLink;
    private String description;
}
