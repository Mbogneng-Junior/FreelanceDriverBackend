
package com.freelance.driver_backend.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.math.BigDecimal;
import org.springframework.data.annotation.Transient;
import java.time.Instant;
import java.util.Map; // Ajout de l'import
import java.util.UUID;

@Table("resources")
@Data
public class Resource {

@PrimaryKey
private ResourceKey key;

private String name;

// =====================================================================
//                         CORRECTION PRINCIPALE
// =====================================================================
// @JsonProperty est pour la SORTIE (Java -> JSON)
// @JsonAlias est pour l'ENTRÉE (JSON -> Java). Il permet plusieurs noms pour un même champ.

@Column("short_description")
@JsonProperty("short_description")
@JsonAlias("baggageInfo") // Accepte "baggageInfo" en entrée
private String shortDescription;

@Column("long_description")
@JsonProperty("long_description")
@JsonAlias("dropoffLocation") // Accepte "dropoffLocation" en entrée
private String longDescription;

@Column("storage_condition")
@JsonProperty("storage_condition")
@JsonAlias("pickupLocation") // Accepte "pickupLocation" en entrée
private String storageCondition;

@Column("base_price")
@JsonProperty("base_price")
@JsonAlias("cost") // Accepte "cost" en entrée
private BigDecimal basePrice;

@Column("sku_code")
@JsonProperty("sku_code")
@JsonAlias("paymentMethod") // Accepte "paymentMethod" en entrée
private String skuCode;

@Column("expires_at")
@JsonProperty("expires_at")
@JsonAlias("expiresAt") // Accepte "expiresAt" en entrée
private Instant expiresAt;

// --- Champs restants ---
private String state;

@Column("product_type")
@JsonProperty("product_type")
private String productType;

private String accessibility;

@Column("iot_number")
@JsonProperty("iot_number")
private String iotNumber;

@Column("available_quantity")
@JsonProperty("available_quantity")
private Integer availableQuantity;

@Column("is_tangible")
@JsonProperty("is_tangible")
private boolean isTangible;

@Column("created_at")
@JsonProperty("created_at")
private Instant createdAt;

@Column("updated_at")
@JsonProperty("updated_at")
private Instant updatedAt;

@Transient 
private Map<String, String> metadata;   


// Méthodes utilitaires (inchangées)
public UUID getResourceId() { return this.key != null ? this.key.getResourceId() : null; }
public UUID getOrganizationId() { return this.key != null ? this.key.getOrganizationId() : null; }
public String getCategoryId() { return this.key != null ? this.key.getCategoryId() : null; }
public void setResourceId(UUID id) { if(this.key == null) this.key = new ResourceKey(); this.key.setResourceId(id); }
public void setOrganizationId(UUID id) { if(this.key == null) this.key = new ResourceKey(); this.key.setOrganizationId(id); }
public void setCategoryId(String id) { if(this.key == null) this.key = new ResourceKey(); this.key.setCategoryId(id); }
}