package com.krokoq.starter.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateItemRequest {
    @NotBlank
    private String content;
}
