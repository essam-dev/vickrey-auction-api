package fr.essam.auction.vickreyapi.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuctionOutput {

    private BidderDTO winningBidder;

    private float winningPrice;
}
