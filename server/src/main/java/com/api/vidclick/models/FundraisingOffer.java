package com.api.vidclick.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "fundraising-offers")
public class FundraisingOffer {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String description;
    private List<String> picturesOfFundraisingOffer;
    private Integer amountToGather;
    @ManyToOne
    @JoinColumn(name = "creator_id", referencedColumnName = "id")
    private Creator creatorId;
    private String linkToBankAccount;
    private Date accountCreatedOn; // needed to be converted into sql
}