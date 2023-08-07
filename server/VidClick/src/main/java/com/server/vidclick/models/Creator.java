package com.server.vidclick.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "creators")
public class Creator {
    @Id
    private Long id;
    private String name;
    private String password;
    private String email;
    private String creatorProfileImage;
    private Date accountCreatedOn; // needed to be converted into sql
}
