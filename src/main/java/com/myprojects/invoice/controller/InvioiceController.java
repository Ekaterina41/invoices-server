package com.myprojects.invoice.controller;

import com.myprojects.invoice.entity.Invoice;
import com.myprojects.invoice.service.InvoiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for CRUD operations with Invoice entities.
 */
@RestController
@RequestMapping("/invoice")
@RequiredArgsConstructor
public class InvioiceController {

    private final InvoiceService invoiceService;

    /**
     * Get paged and sorted list of invoices.
     * @param pageNo page number
     * @param pageSize page size
     * @param sortBy sort field, one of: id, number, date
     * @param direction sort direction, one of: asc, desc
     * @return response entity with the list of invoices
     */
    @Operation(summary = "Get paged and sorted list of invoices")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The list of invoices",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Invoice.class)) })
    })
    @GetMapping("/all")
    public ResponseEntity<List<Invoice>> getAllInvoices(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        List<Invoice> invoices = invoiceService.getInvoices(pageNo, pageSize, sortBy, direction);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Access-Control-Allow-Origin", "*");
        responseHeaders.set("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,PATCH");

        return new ResponseEntity<>(invoices, responseHeaders, HttpStatus.OK);
    }

    /**
     * Get invoice by Id.
     * @param id invoice id
     * @return invoice responce entity if found
     */
    @Operation(summary = "Get invoice by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found invoices",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Invoice.class)) }),
            @ApiResponse(responseCode = "404", description = "Invoice with provided id is not found",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getInvoice(
            @Parameter(description = "id of invoice to be searched")
            @PathVariable("id") Integer id) {
        Optional<Invoice> invoice = invoiceService.getInvoice(id);

        return invoice.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Create new invoice.
     * @param newInvoice new invoice
     * @return created invoice responce entity
     */
    @Operation(summary = "Create new invoice")
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
    @Operation(summary = "Update existing invoice")
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
    @Operation(summary = "Delete invoice")
    @DeleteMapping("/{id}")
    public ResponseEntity<Invoice> deleteInvoice(@PathVariable Integer id) {
        invoiceService.deleteInvoice(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
