package com.api.vidclick.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "fundraisingOffers")
public class FundraisingOffer {
    @Id
    private Long id;
    private String title;
    private String description;
    private List<String> picturesOfFundraisingOffer;
    private Integer amountToGather;
    private Integer creatorId;
    private String linkToBankAccount;
    private Date accountCreatedOn; // needed to be converted into sql
}
