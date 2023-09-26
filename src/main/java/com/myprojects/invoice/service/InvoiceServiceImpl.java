package com.myprojects.invoice.service;

import com.myprojects.invoice.entity.Invoice;
import com.myprojects.invoice.entity.InvoiceEntry;
import com.myprojects.invoice.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Service for CRUD operations with Invoice entity.
 */
@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;

    @Override
    public List<Invoice> getInvoices(Integer pageNo, Integer pageSize, String sortBy, String direction) {

        PageRequest page = PageRequest.of(pageNo, pageSize, Sort.Direction.fromString(direction), sortBy);

        Page<Invoice> pagedResult = invoiceRepository.findAll(page);

        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        }

        return Collections.emptyList();
    }

    @Override
    public Optional<Invoice> getInvoice(Integer id) {
        return invoiceRepository.findById(id);
    }

    @Override
    public Invoice createInvoice(Invoice newInvoice) {
        if (newInvoice.getInvoiceEntries().isEmpty()) {
            newInvoice.getInvoiceEntries().add(getEmptyEntry());
        }

        newInvoice.getInvoiceEntries().forEach( entry -> entry.setInvoice(newInvoice));

        Invoice createdInvoice = invoiceRepository.save(newInvoice);

        return createdInvoice;
    }

    private InvoiceEntry getEmptyEntry() {
        return new InvoiceEntry();
    }

    @Override
    public Invoice updateInvoice(Integer id, Invoice invoice) {
        invoice.setId(id);
        invoice.getInvoiceEntries().forEach(entry -> entry.setInvoice(invoice));

        Invoice updatedInvoice = invoiceRepository.save(invoice);

        return updatedInvoice;
    }

    @Override
    public void deleteInvoice(Integer id) {
        invoiceRepository.deleteById(id);
    }
}
