package Bank;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DepositAmountController {
	@FXML
	private Button closeButton;
	
	@FXML
	private Button depositButton;
	
	@FXML
	private Label depositMessageLabel;
	
	@FXML
	private TextField depositAmountField;
	
	
	
	
	public void depositButtonOnAction(ActionEvent e) {
		String previousBalance;
		int balanceChange;
		String newBalance;
		String username;
		String balanceQuery="";
		try {
    		File myObj = new File("username.txt");
    	    Scanner myReader = new Scanner(myObj);
    	    while (myReader.hasNextLine()) {
    	        username = myReader.nextLine();
    	        balanceQuery = "select balance from bankaccount where custid = (select custid from bankcustomer where username = '"+username+"')";
    	    }
    	    myReader.close();
    	    }catch (FileNotFoundException e3) {
    	    	e3.printStackTrace();
    	 }
		DatabaseConnection connectNow = new DatabaseConnection();
    	Connection connectDB = connectNow.getConnection();
    	try {
    		Statement statement = connectDB.createStatement();
    		ResultSet queryResult = statement.executeQuery(balanceQuery);
    			while(queryResult.next()) {
	    			previousBalance = queryResult.getString(1);
	    			balanceChange = Integer.parseInt(previousBalance);
	    			String plusAmount = depositAmountField.getText();
	    			int plus = Integer.parseInt(plusAmount);
	    			balanceChange = balanceChange + plus;
	    			newBalance = Integer.toString(balanceChange);
	    			String updateBalanceQuery = "";
	    			try {
	    	    		File myObj2 = new File("username.txt");
	    	    	    Scanner myReader2 = new Scanner(myObj2);
	    	    	    while (myReader2.hasNextLine()) {
	    	    	        username = myReader2.nextLine();
	    	    	        updateBalanceQuery = "update BankAccount set balance = "+newBalance+" where custid = (select custID from BankCustomer where username = '"+username+"')";
	    	    	        DatabaseConnection connectNow2 = new DatabaseConnection();
	    	    	    	Connection connectDB2 = connectNow2.getConnection();
	    	    	        try {
	    	    	    		Statement statement2 = connectDB2.createStatement();
	    	    	    		ResultSet queryResult2 = statement2.executeQuery(updateBalanceQuery);
	    	    	    			String messageForSuccess = "Deposit Successful \nPrevious Balance(PKR.)    : "+previousBalance+"\nAmount Deducted(PKR.) : "+plusAmount+"\nNew Balance(PKR.)          : "+newBalance;
    	    		    			depositMessageLabel.setText(messageForSuccess);
    	    		    			String countCheck = "select count(*) from BankTransaction";
    	 	    	    	    	int count = 0;
    	 	    	    	    	String counter ="";
    	 	    	    	    	DatabaseConnection connectNow4 = new DatabaseConnection();
    		    	    	    	Connection connectDB4 = connectNow4.getConnection();
    	 	    	    	    	try {
    	 	    	    	    		Statement statement4 = connectDB4.createStatement();
    	 	    	    	    		ResultSet queryResult4 = statement4.executeQuery(countCheck);
    	 	    	    	    			while(queryResult4.next()) {
    	 	    	    		    			String s = queryResult4.getString(1);
    	 	    	    		    			count = Integer.parseInt(s);
    	 	    	    		    			count++;
    	 	    	    		    			counter = Integer.toString(count);
    	 	    	    	    			}
    	 	    	    	    		}catch(Exception e11) {
    	 	    	    	    		e11.printStackTrace();
    	 	    	    	    	}
    	 	    	    	    	DatabaseConnection connectNow3 = new DatabaseConnection();
    	 	    	    	    	Connection connectDB3 = connectNow3.getConnection();
    	 	    	    	    	String getidQuery = "select acc# from BankAccount where custID = (select custid from BankCustomer where username = '"+username+"')";
    	 	    	    	    	try {
    	 	    	    	    		Statement statement3 = connectDB3.createStatement();
    	 	    	    	    		ResultSet queryResult3 = statement3.executeQuery(getidQuery);
    	 	    	    	    		while(queryResult3.next()) {
    	 	    	    	    			String accnum = queryResult3.getString(1);
    	 	    	    	    			String transactionUpdateQuery = "insert into BankTransaction(transactionID,acc#,transDate,amount,reason) values('"+counter+"','"+accnum+"',TO_DATE(sysdate, 'yyyy/mm/dd hh24:mi:ss'),"+plusAmount+",'Deposit')";
    	 	    	    	    			DatabaseConnection connectNow5 = new DatabaseConnection();
    	    	 	    	    	    	Connection connectDB5 = connectNow5.getConnection();
    	    	 	    	    	    	try {
    	    	 	    	    	    		Statement statement5 = connectDB5.createStatement();
    	    	 	    	    	    		ResultSet queryResult5 = statement5.executeQuery(transactionUpdateQuery);
    	    	 	    	    	    	}catch(Exception e3) {
    	    	 	    	    	    		e3.printStackTrace();
    	    	 	    	    	    	}
    	 	    	    	    		}
    	 	    	    	    		}catch(Exception e2) {
    	 	    	    	    		e2.printStackTrace();
    	 	    	    	    		depositMessageLabel.setText("Deposit Failed");
    	 	    	    	    	}
	    	    	    		}catch(Exception e2) {
	    	    	    		e2.printStackTrace();
	    	    	    		depositMessageLabel.setText("Deposit Failed");
	    	    	    	}
	    	    	    }
	    	    	    myReader2.close();
	    	    	    }catch (FileNotFoundException e3) {
	    	    	    	e3.printStackTrace();
	    	    	 }
    			}
    		}catch(Exception e2) {
    		e2.printStackTrace();
    	}
    	
	}
	
	
	
	public void closeButtonOnAction(ActionEvent e) {
    	Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
    }
}
