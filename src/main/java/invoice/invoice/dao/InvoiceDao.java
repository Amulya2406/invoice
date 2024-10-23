package invoice.invoice.dao;

import java.util.List;

import invoice.invoice.dto.InvoiceDto;
import invoice.invoice.dto.InvoicePayDto;
import invoice.invoice.entity.Invoice;

public interface InvoiceDao {

	void createTable();

	int addInvoice(InvoiceDto invoiceDto);

	List<Invoice> getAllInvoices();

	void updateAmount(InvoicePayDto invoiceDetails);

	
}
