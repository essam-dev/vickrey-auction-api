package fr.essam.auction.vickreyapi.application.rest;

import fr.essam.auction.vickreyapi.application.rest.mapper.AuctionMapper;
import fr.essam.auction.vickreyapi.application.rest.request.VickReyRequest;
import fr.essam.auction.vickreyapi.application.rest.response.VickReyResponse;
import fr.essam.auction.vickreyapi.domain.ports.AuctionServicePort;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/v1/auctions")
@RequiredArgsConstructor
@OpenAPIDefinition(
        info = @Info(
                title = "VickRey Auction API",
                version = "1.0",
                description = "Kerdres Agency - Coding Kata - Vickrey Auction"
        )
)
public class AuctionController {

    private final AuctionServicePort auctionServicePort;
    private final AuctionMapper auctionMapper;

    @PostMapping("/vickrey")
    public ResponseEntity<VickReyResponse> calculateVickReyAuction(@RequestBody VickReyRequest vickReyRequest) {
        log.info("calculateVickReyAuction with body: {}", vickReyRequest);

        return ResponseEntity.ok(
                auctionMapper.toVickReyResponse(
                        auctionServicePort.calculateAuctionResult(auctionMapper.toAuctionInput(vickReyRequest))));

    }

}
