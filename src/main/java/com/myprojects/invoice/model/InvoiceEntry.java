package com.myprojects.invoice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Invoice entry/line model.
 */
@Data
@Accessors(chain = true)
@Entity
@JsonIgnoreProperties("invoice")
public class InvoiceEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "invoice_entry_generator")
    @SequenceGenerator(name = "invoice_entry_generator", sequenceName = "invoice_entry_seq", allocationSize = 1)
    @Schema(description = "Invoice entry id", example = "1")
    private int id;

    @Schema(description = "Invoice entry number", example = "Services of software development")
    private String serviceName;

    @JsonProperty("quantity")
    @Schema(description = "Quantity hours in the specified period", example = "184")
    private short quantityHours;

    @Schema(description = "Total cost of services", example = "3000")
    private int cost;

    @ManyToOne
    @JoinColumn(name="invoice_id", nullable=false)
    private Invoice invoice;
}
