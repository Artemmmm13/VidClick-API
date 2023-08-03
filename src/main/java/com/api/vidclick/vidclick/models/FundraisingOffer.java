package com.api.vidclick.vidclick.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "fundraisings")
public class FundraisingOffer {
    @Id
    private Integer id;
    private String title;
    private String description;

}
