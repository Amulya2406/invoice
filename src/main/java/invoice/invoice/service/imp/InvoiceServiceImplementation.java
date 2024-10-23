package invoice.invoice.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dalesbred.Database;

import invoice.invoice.dto.InvoiceDto;
import invoice.invoice.dto.InvoicePayDto;
import invoice.invoice.entity.Invoice;
import invoice.invoice.repo.InvoiceRepo;
import invoice.invoice.service.InvoiceService;

public class InvoiceServiceImplementation implements InvoiceService {

	private InvoiceRepo invoiceRepo;

	public InvoiceServiceImplementation(Database database) {
		this.invoiceRepo = new InvoiceRepo(database);
		invoiceRepo.createTable();
	}

	@Override
	public int addInvoice(InvoiceDto invoiceDto) {
		int id = invoiceRepo.addInvoice(invoiceDto);
		return id;
	}

	@Override
	public List<Invoice> getAllInvoices() {
		return invoiceRepo.getAllInvoices();
	}

	@Override
	public Map<String, String> updateAmount(InvoicePayDto invoicePayDto) {

		Map<String, String> map = new HashMap<>();
		invoiceRepo.updateAmount(invoicePayDto);
		map.put("message", "Updated");
		return map;

	}
}
