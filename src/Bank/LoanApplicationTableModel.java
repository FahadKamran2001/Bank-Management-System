package Bank;

public class LoanApplicationTableModel {
	public String loanidCol;
	public String accnumCol;
	public String amountCol;
	public String applydateCol;
	public String statusCol;
	
	public LoanApplicationTableModel(String loanidCol, String accnumCol, String amountCol, String applydateCol, String statusCol) {
		this.loanidCol = loanidCol;
		this.accnumCol = accnumCol;
		this.amountCol = amountCol;
		this.applydateCol = applydateCol;
		this.statusCol = statusCol;
	}
	public void setLoanidCol(String loanidCol) {
		this.loanidCol = loanidCol;
	}
	public String getLoanidCol() {
		return loanidCol;
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
	public void setApplydateCol(String applydateCol) {
		this.applydateCol = applydateCol;
	}
	public String getApplydateCol() {
		return applydateCol;
	}
	public void setStatusCol(String statusCol) {
		this.statusCol = statusCol;
	}
	public String getStatusCol() {
		return statusCol;
	}
	
	
}
