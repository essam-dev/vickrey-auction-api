package fr.essam.auction.vickreyapi.domain.ports;

import fr.essam.auction.vickreyapi.domain.model.AuctionInput;
import fr.essam.auction.vickreyapi.domain.model.AuctionOutput;

public interface AuctionServicePort {

    AuctionOutput calculateAuctionResult(AuctionInput auctionInput);


}
