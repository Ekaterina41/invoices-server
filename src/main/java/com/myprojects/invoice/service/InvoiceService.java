package com.myprojects.invoice.service;

import com.myprojects.invoice.model.Invoice;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface InvoiceService {
    /**
     * Get the sorted and paged list of invoices.
     *
     * @param pageNo    page number
     * @param pageSize  page size
     * @param sortBy    property name to sort by
     * @param direction sort direction
     * @return list of invoices
     */
    List<Invoice> getInvoices(Integer pageNo, Integer pageSize, String sortBy, String direction);

    /**
     * Get invoice by id.
     *
     * @param id invoice id
     * @return optional invoice
     */
    Optional<Invoice> getInvoice(Integer id);

    /**
     * Create new Invoice
     *
     * @param newInvoice new invoice
     * @return created invoice
     */
    Invoice createInvoice(Invoice newInvoice);

    /**
     * Update existing Invoice.
     *
     * @param id      id of the updated invoice
     * @param invoice invoice for updating
     * @return updated invoice
     */
    Invoice updateInvoice(Integer id, Invoice invoice);

    /**
     * Delete invoice.
     *
     * @param id id of the invoice to delete
     */
    void deleteInvoice(Integer id);
}
