package com.myprojects.invoice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.myprojects.invoice.utils.YearMonthDateAttributeConverter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

/**
 * Invoice entity.
 */
@Data
@Accessors(chain = true)
@Entity
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "invoice_generator")
    @SequenceGenerator(name = "invoice_generator", sequenceName = "invoice_seq", allocationSize = 1)
    @Schema(description = "Invoice ID", example = "1")
    private int id;

    @Schema(description = "Invoice number", example = "1")
    private int number;

    @Schema(description = "Invoice date", example = "2022-08-04")
    private LocalDate date;

    @Convert(converter = YearMonthDateAttributeConverter.class)
    @Schema(description = "Accounting period (year and month)", example = "2022-07")
    private YearMonth period;

    @JsonProperty("vacations")
    @Schema(description = "Days off amount to automatically count work days for period", example = "0")
    private short daysOffAmount;

    @JsonProperty("workdays")
    @Schema(description = "Workdays amount in the specified period", example = "21")
    private short workDaysAmount;

    @Schema(description = "Total sum without VAT", example = "3000")
    private int total;

    @Schema(description = "VAT value %", example = "0")
    private int vat;

    @JsonProperty("ttp")
    @Schema(description = "Total sum with VAT extracted", example = "3000")
    private int totalToPay;

    @OneToMany(mappedBy="invoice", cascade = CascadeType.ALL)
    private List<InvoiceEntry> invoiceEntries = new ArrayList<>();
}
