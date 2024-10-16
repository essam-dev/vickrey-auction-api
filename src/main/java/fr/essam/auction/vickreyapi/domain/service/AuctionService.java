package fr.essam.auction.vickreyapi.domain.service;

import fr.essam.auction.vickreyapi.domain.exception.AuctionException;
import fr.essam.auction.vickreyapi.domain.model.AuctionInput;
import fr.essam.auction.vickreyapi.domain.model.AuctionOutput;
import fr.essam.auction.vickreyapi.domain.model.BidderDTO;
import fr.essam.auction.vickreyapi.domain.ports.AuctionServicePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuctionService implements AuctionServicePort {

    @Override
    public AuctionOutput calculateAuctionResult(AuctionInput auctionInput) {

        //check if the reserve price is not negative or equal to 0
        if(auctionInput.getReservePrice() <= 0) {
            throw new AuctionException("The reserve price must be greater than 0");
        }

        //define the winning bidder
        BidderDTO winningBidder = getWinningBidder(auctionInput.getBidders(), auctionInput.getReservePrice());


        //define the winning price
        float winningPrice = defineWinningPrice(auctionInput.getBidders(), winningBidder, auctionInput.getReservePrice());


        return new AuctionOutput(winningBidder, winningPrice);
    }

    private BidderDTO getWinningBidder(List<BidderDTO> bidders, float reservePrice) {
        //determine the highest bid for each bidder
        HashMap<BidderDTO, Float> highestBids = new HashMap<>();
        bidders.forEach(bidder -> bidder.getBids()
                .stream()
                .max(Float::compareTo)
                .ifPresent(highestBid -> {
                            if(highestBid >= reservePrice) highestBids.put(bidder, highestBid);
                        }));

        //determine the winning bidder
        return Collections.max(highestBids.entrySet(), Map.Entry.comparingByValue())
                .getKey();
    }

    private float defineWinningPrice(List<BidderDTO> bidders, BidderDTO winningBidder, float reservePrice) {
        //remove the winning bidder from the list
        bidders.remove(winningBidder);

        //define the highest price possible
        float highestPrice = bidders.stream()
                .map(bidder -> bidder.getBids()
                        .stream()
                        .max(Float::compareTo)
                        .orElse(0f))
                .max(Float::compareTo)
                .orElse(0f);


        return Math.max(highestPrice, reservePrice);
    }
}
