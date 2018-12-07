package com.url.shortner.contoller;

import com.url.shortner.dto.URLDto;
import com.url.shortner.dto.ValidationErrorDto;
import com.url.shortner.service.UrlConverterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Controller to handel the incoming request
 * and for valid request return the shot url.
 * <p>
 * Responsible for handling exception and invalid short url
 * by redirecting to error page.
 */
@RestController
@Slf4j
public class ShortUrlController {

    @Value("${message.welcome}")
    private String welcomeMessage;

    @Value("${message.idNotFoundMessage}")
    private String idNotFoundMessage;

    @Value("${message.requestBodyMissing}")
    private String requestBodyMissing;

    private final UrlConverterService urlConverterService;

    public ShortUrlController(final UrlConverterService urlConverterService) {
        this.urlConverterService = urlConverterService;
    }

    @GetMapping(path = "/")
    public String homeResponse() {
        return welcomeMessage;
    }

    // In case when url is not found
    @GetMapping(path = "/error-message")
    public String errorResponse() {
        return idNotFoundMessage;
    }

    @PostMapping(path = "/short-url", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public URLDto shortLongUrl(@Valid @RequestBody final URLDto incomingURL) {
        final String shortUrl = urlConverterService.getShortUrl(incomingURL.getUrl());
        return new URLDto(shortUrl);
    }


    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ValidationErrorDto handleException(final MethodArgumentNotValidException exception) {
        log.warn("Error message: {}", exception.getMessage());
        final String errorMsg = exception.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .findFirst()
                .orElse(exception.getMessage());
        return new ValidationErrorDto(errorMsg);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ValidationErrorDto handleException(final HttpMessageNotReadableException exception) {
        log.warn("Error message: {}", exception.getMessage());
        return new ValidationErrorDto(requestBodyMissing);
    }


}
