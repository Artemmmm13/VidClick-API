package com.api.vidclick.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.Date;

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
    private String picturesOfFundraisingOffer;
    private Integer amount;
    @ManyToOne
    @JoinColumn(name = "creator_id", referencedColumnName = "id")
    private Creator creatorId;
    private String linkToBankAccount;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "offer_created_on")
    private Date offerCreatedOn; // todo (sort by date)

    @PrePersist
    protected void onCreate(){
        offerCreatedOn = new Date();
    }

}

