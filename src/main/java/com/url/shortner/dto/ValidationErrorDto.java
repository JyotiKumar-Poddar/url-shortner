package com.url.shortner.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Dto for mapping error response in JSON format
 * for the controller.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationErrorDto {
    private String errorMsg;
}
