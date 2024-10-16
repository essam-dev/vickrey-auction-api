package fr.essam.auction.vickreyapi.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import fr.essam.auction.vickreyapi.domain.exception.AuctionException;
import fr.essam.auction.vickreyapi.domain.model.AuctionInput;
import fr.essam.auction.vickreyapi.domain.model.AuctionOutput;
import fr.essam.auction.vickreyapi.domain.model.BidderDTO;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class AuctionServiceTest {


    @Test
    void testReservePriceValue() {

        assertThrows(AuctionException.class, () -> new AuctionService().calculateAuctionResult(new AuctionInput(new ArrayList<>(), 0.0f)));
    }

    @Test
    void testCalculateAuctionResult() {
        // Arrange
        BidderDTO winningBidder = new BidderDTO("Jane", List.of(30.0f));
        ArrayList<BidderDTO> bidderDTOList = new ArrayList<>();
        bidderDTOList.add(new BidderDTO("Joe", List.of(10.0f, 17.0f)));
        bidderDTOList.add(new BidderDTO("Sam", new ArrayList<>()));
        bidderDTOList.add(winningBidder);
        AuctionInput auctionInput = new AuctionInput(bidderDTOList, 10.0f);

        // Act
        AuctionOutput actualCalculateAuctionResultResult = new AuctionService().calculateAuctionResult(auctionInput);

        // Assert
        assertEquals(17.0f, actualCalculateAuctionResultResult.getWinningPrice());
        assertSame(winningBidder, actualCalculateAuctionResultResult.getWinningBidder());
    }
}
