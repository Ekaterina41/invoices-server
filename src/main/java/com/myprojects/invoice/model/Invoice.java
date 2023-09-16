package com.myprojects.invoice.model;

import com.myprojects.invoice.utils.YearMonthDateAttributeConverter;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

/**
 * Invoice model.
 */
@Data
@Accessors(chain = true)
@Entity
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "invoice_generator")
    @SequenceGenerator(name = "invoice_generator", sequenceName = "invoice_seq", allocationSize = 1)
    private int id;

    private int number;

    private LocalDate date;

    @Convert(converter = YearMonthDateAttributeConverter.class)
    private YearMonth period;

    private short daysOffAmount;

    private short workDaysAmount;

    private int total;

    private int vat;

    private int totalToPay;

    @OneToMany(mappedBy="invoice", cascade = CascadeType.ALL)
    private List<InvoiceEntry> invoiceEntries = new ArrayList<>();
}
