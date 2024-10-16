package fr.essam.auction.vickreyapi.application.rest.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class VickReyResponse {

    private Bidder winningBidder;

    private float winningPrice;

}
