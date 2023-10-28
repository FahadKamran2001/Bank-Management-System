package Bank;

public class TransactionHistoryTableModel {
	public String transidCol;
	public String accnumCol;
	public String amountCol;
	public String transDateCol;
	public String dueDateCol;
	public String reasonCol;
	
	public TransactionHistoryTableModel(String transidCol, String accnumCol, String amountCol, String transDateCol,String dueDateCol, String reasonCol) {
		this.transidCol = transidCol;
		this.accnumCol = accnumCol;
		this.amountCol = amountCol;
		this.transDateCol = transDateCol;
		this.dueDateCol = dueDateCol;
		this.reasonCol = reasonCol;
	}
	public void setTransidCol(String transidCol) {
		this.transidCol = transidCol;
	}
	public String getTransidCol() {
		return transidCol;
	}
	public void setAccnumCol(String accnumCol) {
		this.accnumCol = accnumCol;
	}
	public String getAccnumCol() {
		return accnumCol;
	}
	public void setAmountCol(String amountCol) {
		this.amountCol = amountCol;
	}
	public String getAmountCol() {
		return amountCol;
	}
	public void setTransDateCol(String transDateCol) {
		this.transDateCol = transDateCol;
	}
	public String getTransDateCol() {
		return transDateCol;
	}
	public void setDueDateCol(String transDateCol) {
		this.transDateCol = transDateCol;
	}
	public String getDueDateCol() {
		return transDateCol;
	}
	public void setReasonCol(String reasonCol) {
		this.reasonCol = reasonCol;
	}
	public String getReasonCol() {
		return reasonCol;
	}
}
