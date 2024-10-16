package fr.essam.auction.vickreyapi.application.rest.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class VickReyRequest {

    @NotNull(message = "bidder field is missing")
    private List<Bidder> bidders;

    @NotNull(message = "reservePrice field is missing")
    private float reservePrice;
}
