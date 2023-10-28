package Bank;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.control.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.fxml.Initializable;
import javafx.beans.property.StringProperty;
import java.lang.Boolean;
import java.net.URL;
import javafx.collections.ObservableList;

public class ManageLoanApplicationController implements Initializable{

    @FXML
    private Button closeButton;

    @FXML
    private Button AcceptButton;
    
    @FXML
    private Button DeclineButton;
    
    @FXML
    private Label manageMessageLabel;

    @FXML
    private TableView<LoanApplicationTableModel> loanAppTable;

    @FXML
    private TableColumn<LoanApplicationTableModel, String> loanidCol;

    @FXML
    private TableColumn<LoanApplicationTableModel, String> accnumCol;

    @FXML
    private TableColumn<LoanApplicationTableModel, String> amountCol;

    @FXML
    private TableColumn<LoanApplicationTableModel, String> applydateCol;

    @FXML
    private TableColumn<LoanApplicationTableModel, String> statusCol;
    
    @FXML
    private TextField idField;
  
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showLoanApplications();
 
    }
 
    public ObservableList<LoanApplicationTableModel> getLoanApplicationsList(){
        ObservableList<LoanApplicationTableModel> List = FXCollections.observableArrayList();
        DatabaseConnection connectNow = new DatabaseConnection();
    	Connection connectDB = connectNow.getConnection();
        String query = "select * from BankLoanApplication";
        Statement st;
        ResultSet rs;
        try{
            st = connectDB.createStatement();
            rs = st.executeQuery(query);
            LoanApplicationTableModel row ;
            while(rs.next()){
            	if(rs.getString(5).equals("Pending")) {
	                row = new LoanApplicationTableModel(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
	                List.add(row);
            	}
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return List;
    }
 
    public void showLoanApplications(){
        ObservableList<LoanApplicationTableModel> list = getLoanApplicationsList();
        loanidCol.setCellValueFactory(new PropertyValueFactory<LoanApplicationTableModel, String>("loanidCol"));
	   	accnumCol.setCellValueFactory(new PropertyValueFactory<LoanApplicationTableModel, String>("accnumCol"));
	   	amountCol.setCellValueFactory(new PropertyValueFactory<LoanApplicationTableModel, String>("amountCol"));
	   	applydateCol.setCellValueFactory(new PropertyValueFactory<LoanApplicationTableModel, String>("applydateCol"));
	   	statusCol.setCellValueFactory(new PropertyValueFactory<LoanApplicationTableModel, String>("statusCol"));
 
        loanAppTable.setItems(list)  ;
    }

    @FXML
    void closeButtonOnAction(ActionEvent event) {
    	Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
    }

    @FXML
    void acceptButtonOnAction(ActionEvent event) {
    	String username = "";
    	try {
    		File myObj2 = new File("username.txt");
    	    Scanner myReader2 = new Scanner(myObj2);
    	    while (myReader2.hasNextLine()) {
    	        username = myReader2.nextLine();  
    	    }
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	if(idField.getText().isBlank() == false) {
    		String loanid = idField.getText();
    		String updateAcceptQuery = "Update BankLoanApplication set status = 'Active' where loanid = '"+loanid+"'";
    		DatabaseConnection connectNow2 = new DatabaseConnection();
        	Connection connectDB2 = connectNow2.getConnection();
            
            try{
            	Statement st2= connectDB2.createStatement();
                int rs2 = st2.executeUpdate(updateAcceptQuery);
                String interestRate = Integer.toString(5);
                String accountNumber = "";
                String amount = "";
                String getValueQuery = "select acc#, amount from bankloanapplication where loanid = '"+loanid+"'";
                DatabaseConnection connectNow3 = new DatabaseConnection();
            	Connection connectDB3 = connectNow3.getConnection();
            	try {
            		Statement st3= connectDB3.createStatement();
                    ResultSet rs3 = st3.executeQuery(getValueQuery);
                    while(rs3.next()) {
                    	accountNumber = rs3.getString(1);
                    	amount = rs3.getString(2);
                    }
            	}
            	catch(Exception e2) {
            		e2.printStackTrace();
            	}
                String insertAcceptedQuery = "insert into BankLoan values('"+loanid+"','"+accountNumber+"',"+amount+","+interestRate+",TO_DATE(sysdate, 'yyyy/mm/dd hh24:mi:ss'),add_months(TO_DATE(sysdate, 'yyyy/mm/dd hh24:mi:ss'),36),'Active')";                                            
                DatabaseConnection connectNow4 = new DatabaseConnection();
            	Connection connectDB4 = connectNow4.getConnection();
            	try {
            		Statement st4= connectDB4.createStatement();
                    int rs4 = st4.executeUpdate(insertAcceptedQuery);
                    manageMessageLabel.setText("Application has Been Accepted and Added to Accepted Table");
            	}
            	catch(Exception e2) {
            		e2.printStackTrace();
            	}
            	String getbalanceQuery = "select balance from bankaccount where acc# = '"+accountNumber+"'";
            	String balance = "";
            	int bal = 0;
            	DatabaseConnection connectNow9 = new DatabaseConnection();
            	Connection connectDB9 = connectNow9.getConnection();
            	try {
            		Statement st9= connectDB9.createStatement();
                    ResultSet rs9 = st9.executeQuery(getbalanceQuery);
                    while(rs9.next()) {
                    	balance = rs9.getString(1);
                    	bal = Integer.parseInt(balance);
                    	balance = Integer.toString(bal);
                    }
            	}
            	catch(Exception e4) {
            		e4.printStackTrace();
            	}
            	String addAmounttobalancequery = "update BankAccount set balance = "+balance+" where acc# = '"+accountNumber+"'";
                DatabaseConnection connectNow5 = new DatabaseConnection();
            	Connection connectDB5 = connectNow5.getConnection();
            	try {
            		Statement st5= connectDB5.createStatement();
                    int rs5 = st5.executeUpdate(addAmounttobalancequery);
            	}
            	catch(Exception e2) {
            		e2.printStackTrace();
            	}
            	String transactionID = "";
            	String gettranID = "select count(*) from BankTransaction";
            	DatabaseConnection connectNow6 = new DatabaseConnection();
            	Connection connectDB6 = connectNow6.getConnection();
            	try {
            		Statement st6 = connectDB6.createStatement();
            		ResultSet rs6 = st6.executeQuery(gettranID);
            		while(rs6.next()) {
            			transactionID = rs6.getString(1);
            			int num = Integer.parseInt(transactionID);
            			num++;
            			transactionID = Integer.toString(num);
            		}
            	}catch(Exception e5) {
            		e5.printStackTrace();
            	}
            	String addtransactionquery = "insert into BankTransaction values('"+transactionID+"','"+accountNumber+"',TO_DATE(sysdate, 'yyyy/mm/dd hh24:mi:ss'),"+amount+",ADD_MONTHS(SYSDATE, +60),'Loan')";
                DatabaseConnection connectNow7 = new DatabaseConnection();
            	Connection connectDB7 = connectNow7.getConnection();
            	try {
            		Statement st7= connectDB7.createStatement();
                    int rs7 = st7.executeUpdate(addtransactionquery);
            	}
            	catch(Exception e2) {
            		e2.printStackTrace();
            	}
            	String deleteexcessapplications = "delete from bankLoanApplication where loanid != '"+loanid+"' and acc# = "+accountNumber;
            	DatabaseConnection connectNow8 = new DatabaseConnection();
            	Connection connectDB8 = connectNow8.getConnection();
            	try {
            		Statement st8 = connectDB8.createStatement();
                    int rs8 = st8.executeUpdate(deleteexcessapplications);
            	}
            	catch(Exception e2) {
            		e2.printStackTrace();
            	}
            	
            	
            }catch (Exception e){
                e.printStackTrace();
            }
    	}
    	
    }

    
    @FXML
    void declineButtonOnAction(ActionEvent event) {
    	String username = "";
    	try {
    		File myObj2 = new File("username.txt");
    	    Scanner myReader2 = new Scanner(myObj2);
    	    while (myReader2.hasNextLine()) {
    	        username = myReader2.nextLine();  
    	    }
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	if(idField.getText().isBlank() == false) {
    		String loanid = idField.getText();
    		String updateAcceptQuery = "Update BankLoanApplication set status = 'InActive' where loanid = '"+loanid+"'";
    		DatabaseConnection connectNow2 = new DatabaseConnection();
        	Connection connectDB2 = connectNow2.getConnection();
            try{
            	Statement st2= connectDB2.createStatement();
                int rs2 = st2.executeUpdate(updateAcceptQuery);
                manageMessageLabel.setText("Decline Successful");

            }catch (Exception e){
                e.printStackTrace();
            }
    	}
    	
    }
}
