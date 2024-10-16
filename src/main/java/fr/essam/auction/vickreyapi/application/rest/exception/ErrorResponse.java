package fr.essam.auction.vickreyapi.application.rest.exception;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ErrorResponse {

    private int httpStatus;

    private String message;

}