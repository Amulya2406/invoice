package invoice.invoice.service;

import java.util.List;
import java.util.Map;

import invoice.invoice.dto.InvoiceDto;
import invoice.invoice.dto.InvoicePayDto;
import invoice.invoice.entity.Invoice;

public interface InvoiceService {

	int addInvoice(InvoiceDto invoiceDto);

	List<Invoice> getAllInvoices();

	Map<String, String> updateAmount(InvoicePayDto invoicePayDto);

}
