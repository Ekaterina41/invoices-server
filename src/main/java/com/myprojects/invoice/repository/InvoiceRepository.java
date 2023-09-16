package com.myprojects.invoice.repository;

import com.myprojects.invoice.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for CRUD operations with Invoice entities
 */
@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
}
