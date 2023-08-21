package com.krokoq.simple.opentelemetry.rest.service.out;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemOut {
    private Long id;
    private String title;
    private String content;
    private Date createDate;
    private Date updateDate;
}
