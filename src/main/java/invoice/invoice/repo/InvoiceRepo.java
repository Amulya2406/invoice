package invoice.invoice.repo;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dalesbred.Database;
import org.dalesbred.query.SqlQuery;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import invoice.invoice.dao.InvoiceDao;
import invoice.invoice.dto.InvoiceDto;
import invoice.invoice.dto.InvoicePayDto;
import invoice.invoice.entity.Invoice;
import invoice.invoice.enums.InvoiceEnum;

public class InvoiceRepo implements InvoiceDao {
	private Database database;
	private static final String DATA_SOURCE = "db/table.sql";

	public InvoiceRepo() {
	}

	public InvoiceRepo(Database database) {
		this.database = database;
	}

	private String generateTableIfExists() throws IOException {
		URL url = Resources.getResource(DATA_SOURCE);
		String table = Resources.toString(url, Charsets.UTF_8);
		return table;
	}
	
	private Invoice getInvoice(int id) {
		final String GET_INVOICE = "SELECT * from INVOICE WHERE id = ?";

		return database.findUnique(Invoice.class, GET_INVOICE, id);
	}

	@Override
	public void createTable() {
		try {
			String table = generateTableIfExists();
			database.update(table);
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	@Override
	public int addInvoice(InvoiceDto invoiceDto) {
		final String ADD_INVOICE = "INSERT INTO INVOICE(amount,paid_amount,due_date,status) VALUES (:amount,:paid_amount,:due_date,:status) returning id";
		Map<String, Object> map = new HashMap<>();
		map.put("amount", invoiceDto.getAmount());
		map.put("paid_amount", 0);
		map.put("due_date", invoiceDto.getDueDate());
		map.put("status", InvoiceEnum.PENDING);

		return database.findUniqueInt(SqlQuery.namedQuery(ADD_INVOICE, map));
	}
	
	@Override
	public List<Invoice> getAllInvoices() {
		final String GET_ALL_INVOICES = "SELECT * FROM INVOICE";
		List<Invoice> allInvoices = database.findAll(Invoice.class, GET_ALL_INVOICES);

		return allInvoices;
	}

	@Override
	public void updateAmount(InvoicePayDto invoicePayDto) {
		final String UPDATE_AMOUNT = "UPDATE INVOICE SET paid_amount = :paid_amount, amount = :amount, status = :status WHERE id = :id";
		Invoice getAmount = getInvoice(invoicePayDto.getId());
		InvoiceEnum status;
		if (getAmount.getAmount() <= invoicePayDto.getPaidAmount()) {
		    status = InvoiceEnum.PAID;
		} else {
		    status = InvoiceEnum.PENDING;
		}

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("paid_amount", getAmount.getPaidAmount() + invoicePayDto.getPaidAmount());
		parameters.put("amount", getAmount.getAmount() - invoicePayDto.getPaidAmount());
		parameters.put("status", status);
		parameters.put("id", invoicePayDto.getId());

		database.update(SqlQuery.namedQuery(UPDATE_AMOUNT, parameters));
	}
}
