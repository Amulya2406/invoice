package invoice.invoice.entity;

import invoice.invoice.enums.InvoiceEnum;

public class Invoice {
	private int Id;
	private double amount;
	private double paidAmount;
	private String dueDate;
	private InvoiceEnum status;
	


	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public double getAmount() {
		return amount;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(double paidAmount) {
		this.paidAmount = paidAmount;
	}

	public InvoiceEnum getStatus() {
		return status;
	}
	public void setStatus(InvoiceEnum status) {
		this.status = status;
	}
	
}