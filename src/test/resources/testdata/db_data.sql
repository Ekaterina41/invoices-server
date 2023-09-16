-- Invoice data
INSERT INTO invoice
(id, "number", "date", "period", days_off_amount, work_days_amount, total, vat, total_to_pay)
VALUES(1, 1, '2023-08-02', '2023-07-01', 0, 21, 3000, 0, 3000);
INSERT INTO invoice
(id, "number", "date", "period", days_off_amount, work_days_amount, total, vat, total_to_pay)
VALUES(2, 2, '2023-09-04', '2023-08-01', 0, 23, 3300, 0, 3300);

-- Invoice_entry data
INSERT INTO invoice_entry
(id, service_name, quantity_hours, "cost", invoice_id)
VALUES(1, 'Services of software development for July 2023', 168, 3000, 1);
INSERT INTO invoice_entry
(id, service_name, quantity_hours, "cost", invoice_id)
VALUES(2, 'Services of software development for August 2023', 184, 3300, 2);