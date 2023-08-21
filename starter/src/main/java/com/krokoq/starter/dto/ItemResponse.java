package com.krokoq.starter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemResponse {

    private Long id;
    private String title;
    private String content;
    private Date createDate;
    private Date updateDate;
}
