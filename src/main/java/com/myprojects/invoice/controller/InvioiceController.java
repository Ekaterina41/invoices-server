package com.myprojects.invoice.controller;

import com.myprojects.invoice.model.Invoice;
import com.myprojects.invoice.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for CRUD operations with Invoice entities.
 */
@RestController
@RequestMapping("invoice")
@RequiredArgsConstructor
public class InvioiceController {

    private final InvoiceService invoiceService;

    /**
     * Get paged and sorted list of invoices.
     * @param pageNo page number
     * @param pageSize page size
     * @param sortBy sort field, one of: id, number, date
     * @param direction sort direction, one of: asc, desc
     * @return responce entity with the list of invoices
     */
    @GetMapping("/all")
    public ResponseEntity<List<Invoice>> getAllInvoices(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        List<Invoice> invoices = invoiceService.getInvoices(pageNo, pageSize, sortBy, direction);

        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }

    /**
     * Get invoice by Id.
     * @param id invoice id
     * @return invoice responce entity if found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getInvoice(@PathVariable("id") Integer id) {
        Optional<Invoice> invoice = invoiceService.getInvoice(id);

        return invoice.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Create new invoice.
     * @param newInvoice new invoice
     * @return created invoice responce entity
     */
    @PostMapping
    public ResponseEntity<Invoice> createInvoice(@RequestBody Invoice newInvoice) {
        return new ResponseEntity<>(invoiceService.createInvoice(newInvoice), HttpStatus.CREATED);
    }

    /**
     * Update existing invoice.
     * @param id invoice id
     * @param invoice existing invoice
     * @return updated invoice responce entity
     */
    @PutMapping("/{id}")
    public ResponseEntity<Invoice> updateInvoice(@PathVariable Integer id, @RequestBody Invoice invoice) {
        return new ResponseEntity<>(invoiceService.updateInvoice(id, invoice), HttpStatus.OK);
    }

    /**
     * Delete invoice.
     *
     * @param id id of the invoice to delete
     * @return response status
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Invoice> deleteInvoice(@PathVariable Integer id) {
        invoiceService.deleteInvoice(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
