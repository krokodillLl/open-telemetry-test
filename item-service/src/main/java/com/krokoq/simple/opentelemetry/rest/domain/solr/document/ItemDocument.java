package com.krokoq.simple.opentelemetry.rest.domain.solr.document;

import com.krokoq.simple.opentelemetry.rest.domain.ConvertibleItem;
import lombok.*;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@SolrDocument(collection = "item")
public class ItemDocument  implements ConvertibleItem {
    @Id
    @Indexed(name = "id", type = "long")
    private Long id;

    @Indexed(name = "title", type = "string")
    private String title;

    @Indexed(name = "content", type = "string")
    private String content;

    @ToString.Exclude
    @Indexed(name = "createDate", type = "date")
    private Date createDate;

    @ToString.Exclude
    @Indexed(name = "updateDate", type = "date")
    private Date updateDate;
}
