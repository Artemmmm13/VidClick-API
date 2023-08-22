package com.api.vidclick.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateFundraisingOfferRequest {
    private String title;
    private String description;
    private List<String> picturesOfFundraisingOffer;
    private Integer amountToGather;
    private String linkToBankAccount;
    private Long creatorId;
}
