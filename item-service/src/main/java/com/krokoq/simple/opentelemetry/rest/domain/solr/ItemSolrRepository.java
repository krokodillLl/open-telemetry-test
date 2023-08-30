package com.krokoq.simple.opentelemetry.rest.domain.solr;

import com.krokoq.simple.opentelemetry.rest.domain.solr.document.ItemDocument;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.Optional;

public interface ItemSolrRepository extends SolrCrudRepository<ItemDocument, Long> {
    Optional<ItemDocument> findByTitle(String title);
}
