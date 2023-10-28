package Bank;

public class CustomerTableModel {
	public String custidCol;
	public String nameCol;
	public String phonenumCol;
	public String emailCol;
	public String usernameCol;
	public String passwordCol;
	
	public CustomerTableModel(String custidCol, String nameCol, String phonenumCol, String emailCol,String usernameCol, String passwordCol) {
		this.custidCol = custidCol;
		this.nameCol = nameCol;
		this.phonenumCol = phonenumCol;
		this.emailCol = emailCol;
		this.usernameCol = usernameCol;
		this.passwordCol = passwordCol;
	}
	public void setCustidCol(String custidCol) {
		this.custidCol = custidCol;
	}
	public String getCustidCol() {
		return custidCol;
	}
	public void setNameCol(String nameCol) {
		this.nameCol = nameCol;
	}
	public String getNameCol() {
		return nameCol;
	}
	public void setPhonenumCol(String phonenumCol) {
		this.phonenumCol = phonenumCol;
	}
	public String getPhonenumCol() {
		return phonenumCol;
	}
	public void setEmailCol(String emailCol) {
		this.emailCol = emailCol;
	}
	public String getEmailCol() {
		return emailCol;
	}
	public void setUsernameCol(String usernameCol) {
		this.usernameCol = usernameCol;
	}
	public String getUsernameCol() {
		return usernameCol;
	}
	public void setPasswordCol(String passwordCol) {
		this.passwordCol = passwordCol;
	}
	public String getPasswordCol() {
		return passwordCol;
	}
}
