package com.myprojects.invoice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    private int id;

    private String serviceName;

    private short quantityHours;

    private int cost;

    @ManyToOne
    @JoinColumn(name="invoice_id", nullable=false)
    private Invoice invoice;
}
