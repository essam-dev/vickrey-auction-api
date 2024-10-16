package fr.essam.auction.vickreyapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.essam.auction.vickreyapi.application.rest.request.Bidder;
import fr.essam.auction.vickreyapi.application.rest.request.VickReyRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class VickreyAuctionApiApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void validationBusinessRules() throws Exception {
		Bidder bidderA = new Bidder("A", List.of(110F, 130F));
		Bidder bidderB = new Bidder("B", Collections.emptyList());
		Bidder bidderC = new Bidder("C", List.of(125F));
		Bidder bidderD = new Bidder("D", List.of(105F, 115F, 90F));
		Bidder bidderE = new Bidder("E", List.of(132F, 135F, 140F));

		float reservePrice = 100;

		VickReyRequest vickReyRequest = new VickReyRequest(List.of(bidderA, bidderB, bidderC, bidderD, bidderE), reservePrice);

		this.mockMvc.perform(
						post("/v1/auctions/vickrey")
								.contentType(MediaType.APPLICATION_JSON)
								.content(asJsonString(vickReyRequest)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.winningPrice").value("130.0"))
				.andExpect(jsonPath("$.winningBidder.userName").value("E"))
				.andExpect(jsonPath("$.winningBidder.bids[0]", is(132.0)))
				.andExpect(jsonPath("$.winningBidder.bids[1]", is(135.0)))
				.andExpect(jsonPath("$.winningBidder.bids[2]", is(140.0)));

	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
