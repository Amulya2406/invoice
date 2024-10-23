package invoice.invoice.resources;

import java.util.List;
import java.util.Map;

import org.dalesbred.Database;
import org.json.simple.JSONObject;

import invoice.invoice.dto.InvoiceDto;
import invoice.invoice.dto.InvoicePayDto;
import invoice.invoice.entity.Invoice;
import invoice.invoice.service.InvoiceService;
import invoice.invoice.service.imp.InvoiceServiceImplementation;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/invoices")
@Produces(MediaType.APPLICATION_JSON)
public class InvoiceResource {
	
	private InvoiceService invoiceservice;
	
	public InvoiceResource(Database database) {
		this.invoiceservice = new InvoiceServiceImplementation(database);
	}
	
	@POST
	@Path("")
	public String addInvoice(InvoiceDto invoiceDto) {
		int id = invoiceservice.addInvoice(invoiceDto);
	    JSONObject jsonObject = new JSONObject();
	    jsonObject.put("id", id);
	    return jsonObject.toString();
	}
	
	@GET
	@Path("/get")
	public List<Invoice> getAllInvoices() {
		return invoiceservice.getAllInvoices();
	}
	
	@POST
	@Path("/{id}/payments")
	public Map<String, String> updateInvoice(@PathParam("id") int id, InvoicePayDto invoicePayDto) {
			invoicePayDto.setId(id);
			Map<String, String> map = invoiceservice.updateAmount(invoicePayDto);
			return map;
	}
	
}
