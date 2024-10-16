package fr.essam.auction.vickreyapi.application.rest.mapper;

import fr.essam.auction.vickreyapi.application.rest.request.VickReyRequest;
import fr.essam.auction.vickreyapi.application.rest.response.VickReyResponse;
import fr.essam.auction.vickreyapi.domain.model.AuctionInput;
import fr.essam.auction.vickreyapi.domain.model.AuctionOutput;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuctionMapper {

    AuctionInput toAuctionInput(VickReyRequest vickReyRequest);

    VickReyResponse toVickReyResponse(AuctionOutput auctionOutput);
}
