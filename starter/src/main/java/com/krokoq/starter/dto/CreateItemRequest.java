package com.krokoq.starter.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateItemRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String content;
}
