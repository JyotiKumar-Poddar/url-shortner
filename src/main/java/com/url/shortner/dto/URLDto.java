package com.url.shortner.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * Dto to map the incoming request
 * in JSON format to the controller {@link com.url.shortner.contoller.ShortUrlController}
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class URLDto {

    @Pattern(regexp = "((([A-Za-z]{3,9}:(?:\\/\\/)?)(?:[\\-;:&=\\+\\$,\\w]+@)?[A-Za-z0-9\\.\\-]+|(?:www\\.|[\\-;:&=\\+\\$,\\w]+@)[A-Za-z0-9\\.\\-]+)((?:\\/[\\+~%\\/\\.\\w\\-_]*)?\\??(?:[\\-\\+=&;%@\\.\\w_]*)#?(?:[\\.\\!\\/\\\\\\w]*))?)"
            , message = "{url.invalid}"
    )
    @NotBlank(message = "{url.isEmpty}")
    private String url;
}
