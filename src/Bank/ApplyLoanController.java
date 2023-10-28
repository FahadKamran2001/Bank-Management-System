package Bank;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.lang.Boolean;

public class ApplyLoanController {

    @FXML
    private Button closeButton;

    @FXML
    private Button applyButton;

    @FXML
    private TextField amountField;

    @FXML
    private TextField durationField;
    
    @FXML
    private Label applyMessageLabel;

    @FXML
    void applyButtonOnAction(ActionEvent event) {
    	//first checking if there is no previous loan
    	boolean flag = false;
    	String loancheck = "";
    	loancheck = "select count(*) from BankLoan where accc# = (select acc# from BankAccount where custid = (select custid from BankCustomer where username = 'username'))";
    	int appliCount = 0;
    	
    	String statusQuery = "";
    	String username = "";
    	try {
    		File myObj2 = new File("username.txt");
    	    Scanner myReader2 = new Scanner(myObj2);
    	    while (myReader2.hasNextLine()) {
    	        username = myReader2.nextLine();
    	        statusQuery = "select status from BankAccount where custid = (select custid from bankcustomer where username = '"+username+"')";
    	        loancheck = "select count(*) from BankLoan where acc# = (select acc# from BankAccount where custid = (select custid from BankCustomer where username = '"+username+"'))";	
    	    }
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	DatabaseConnection connectNow6 = new DatabaseConnection();
    	Connection connectDB6 = connectNow6.getConnection();
    	try {
    		Statement statement6 = connectDB6.createStatement();
    		ResultSet queryResult6 = statement6.executeQuery(loancheck);
    			while(queryResult6.next()) {
	    			appliCount = Integer.parseInt(queryResult6.getString(1));
	    			if(appliCount > 0) {
	    				flag = true;
	    				applyMessageLabel.setText("Already Have A Loan In Progress");
	    			}
    			}
    	}
    	catch(Exception e2) {
    		e2.printStackTrace();
    	}
    	if(flag == false) {
	    	String status = "";
	    	DatabaseConnection connectNow = new DatabaseConnection();
	    	Connection connectDB = connectNow.getConnection();
	    	try {
	    		Statement statement = connectDB.createStatement();
	    		ResultSet queryResult = statement.executeQuery(statusQuery);
	    			while(queryResult.next()) {
		    			status = queryResult.getString(1);
	    			}
	    	}
	    	catch(Exception e2) {
	    		e2.printStackTrace();
	    	}
	    	if(status.equals("Active")) {
	    		String addApplicationQuery = "";
	    		String accNum = "";
	    		String count = "";
	    		int amount = Integer.parseInt(amountField.getText());
	    		String accQuery = "Select acc# from BankAccount where custid = (select custid from BankCustomer where username = '"+username+"')";
	    		String countQuery = "Select count(*) from BankLoanApplication";
	    		DatabaseConnection connectNow2 = new DatabaseConnection();
	        	Connection connectDB2 = connectNow2.getConnection();
	        	try {
	        		Statement statement2 = connectDB2.createStatement();
	        		ResultSet queryResult2 = statement2.executeQuery(accQuery);
	        			while(queryResult2.next()) {
	    	    			accNum = queryResult2.getString(1);
	        			}
	        	}
	        	catch(Exception e2) {
	        		e2.printStackTrace();
	        	}
	        	DatabaseConnection connectNow3 = new DatabaseConnection();
	        	Connection connectDB3 = connectNow3.getConnection();
	        	try {
	        		Statement statement3 = connectDB3.createStatement();
	        		ResultSet queryResult3 = statement3.executeQuery(countQuery);
	        			while(queryResult3.next()) {
	    	    			count = queryResult3.getString(1);
	    	    			int counter = Integer.parseInt(count);
	    	    			counter++;
	    	    			count = Integer.toString(counter);
	        			}
	        	}
	        	catch(Exception e2) {
	        		e2.printStackTrace();
	        	}
	        	addApplicationQuery = "insert into BankLoanApplication values('"+count+"','"+accNum+"', "+amount+",TO_DATE(sysdate, 'yyyy/mm/dd hh24:mi:ss'),'Pending')";
	        	DatabaseConnection connectNow4 = new DatabaseConnection();
	        	Connection connectDB4 = connectNow4.getConnection();
	        	try {
	        		Statement statement4 = connectDB4.createStatement();
	        		int queryResult4 = statement4.executeUpdate(addApplicationQuery);
	        		
	                	try {
	                		System.out.println(addApplicationQuery);
	                		Statement statement5 = connectDB4.createStatement();
	                		ResultSet queryResult5 = statement5.executeQuery("commit");
	                		applyMessageLabel.setText("Loan Application Successfully Sent\nPending Approval...\nKindly Wait For Status To Be Updated\nThank You");
	                	}catch(Exception e3) {
	                		e3.printStackTrace();
	                	}
	                	
	        		
	        	}
	        	catch(Exception e2) {
	        		e2.printStackTrace();
	        	}
	    	}
	    	else {
	    		applyMessageLabel.setText("Account Is Inactive, Please Activate Before Applying.\nThank You");
	    	}
    	}
    }

    @FXML
    void closeButtonOnAction(ActionEvent event) {
    	Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
    }
    

}
