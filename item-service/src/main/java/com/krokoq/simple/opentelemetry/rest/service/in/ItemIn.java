package com.krokoq.simple.opentelemetry.rest.service.in;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ItemIn {
    private Long id;
    private String title;
    private String content;
    private Date createDate;
    private Date updateDate;
}
