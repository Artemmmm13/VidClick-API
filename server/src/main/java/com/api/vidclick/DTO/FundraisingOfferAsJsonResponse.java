package com.api.vidclick.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FundraisingOfferAsJsonResponse {
    @JsonProperty("creator_id")
    private Long creatorId;
    @JsonProperty("id")
    private Long id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;
    @JsonProperty("photos_of_offer")
    private List<String> photosOfFundraisingOffer;
    @JsonProperty("amount")
    private Integer amount;
    @JsonProperty("link_to_bank_account")
    private String linkToBankAccount;
    @JsonProperty("offer_created_on")
    private Date offerCreatedOn;
}
