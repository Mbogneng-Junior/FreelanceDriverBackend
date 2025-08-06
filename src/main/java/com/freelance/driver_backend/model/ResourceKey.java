package com.freelance.driver_backend.model;

import lombok.Data;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.util.UUID;

@PrimaryKeyClass
@Data
public class ResourceKey implements Serializable {

    @PrimaryKeyColumn(name = "organization_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private UUID organizationId;

    @PrimaryKeyColumn(name = "category_id", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
    private String categoryId;

    @PrimaryKeyColumn(name = "resource_id", ordinal = 2, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    private UUID resourceId;
}