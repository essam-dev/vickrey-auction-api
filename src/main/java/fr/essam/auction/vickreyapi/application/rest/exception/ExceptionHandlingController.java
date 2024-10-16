package fr.essam.auction.vickreyapi.application.rest.exception;

import fr.essam.auction.vickreyapi.domain.exception.AuctionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlingController extends ResponseEntityExceptionHandler {


    @ExceptionHandler(AuctionException.class)
    protected ResponseEntity<Object> handleIamOtpSmsException(final AuctionException auctionException) {
        log.error("AuctionException occurred: {}", auctionException.getMessage());
        return new ResponseEntity<>(new ErrorResponse(BAD_REQUEST.value(), auctionException.getMessage()), new HttpHeaders(), BAD_REQUEST);
    }


}
