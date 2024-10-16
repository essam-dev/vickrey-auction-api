package fr.essam.auction.vickreyapi.domain.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class BidderDTO {

    @NotBlank(message = "userName field is missing")
    private String userName;

    @NotBlank(message = "bids field is missing")
    private List<Float> bids;

}
