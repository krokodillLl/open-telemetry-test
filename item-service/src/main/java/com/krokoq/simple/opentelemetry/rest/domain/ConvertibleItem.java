package com.krokoq.simple.opentelemetry.rest.domain;

import java.util.Date;

public interface ConvertibleItem {
    Long getId();
    void setId(Long id);

    String getTitle();
    void setTitle(String title);

    String getContent();
    void setContent(String content);

    Date getCreateDate();
    void setCreateDate(Date createDate);

    Date getUpdateDate();
    void setUpdateDate(Date updateDate);
}
