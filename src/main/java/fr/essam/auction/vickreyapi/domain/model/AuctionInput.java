package fr.essam.auction.vickreyapi.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class AuctionInput {

    private List<BidderDTO> bidders;

    private float reservePrice;
}
