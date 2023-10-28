package Bank;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class LoanPaymentController {

    @FXML
    private Button closeButton;

    @FXML
    private Label paymentMessageLabel;

    @FXML
    private Button paymentButton;

    @FXML
    void closeButtonOnAction(ActionEvent event) {
    	Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
    }

    @FXML
    void paymentOnAction(ActionEvent event) {
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
    	String newBalance = "";
    	String balance = "";
    	String deduct = "";
    	String amountQuery = "select amount from banktransaction where reason = 'Loan' and acc# = (select acc# from bankAccount where custid = (select custid from bankcustomer where username = '"+username+"'))";
    	DatabaseConnection connectNow = new DatabaseConnection();
    	Connection connectDB = connectNow.getConnection();
    	try {
    		Statement st = connectDB.createStatement();
    		ResultSet rs = st.executeQuery(amountQuery);
    		while(rs.next()) {
    			deduct = rs.getString(1);
    		}
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	String getbalance = "select balance from bankaccount where custid = (select custid from bankcustomer where username = '"+username+"')";
    	DatabaseConnection connectNow2 = new DatabaseConnection();
    	Connection connectDB2 = connectNow2.getConnection();
    	try {
    		Statement st2 = connectDB2.createStatement();
    		ResultSet rs2 = st2.executeQuery(getbalance);
    		while(rs2.next()) {
    			balance = rs2.getString(1);
    		}
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	int bal = Integer.parseInt(balance);
    	int minus = Integer.parseInt(deduct);
    	bal = bal - minus;
    	newBalance = Integer.toString(bal);
    	String updateBalance = "update bankaccount set balance = "+newBalance+" where custid = (select custid from bankcustomer where username = '"+username+"')";
    	DatabaseConnection connectNow3 = new DatabaseConnection();
    	Connection connectDB3 = connectNow3.getConnection();
    	try {
    		Statement st3 = connectDB3.createStatement();
    		int rs3 = st3.executeUpdate(updateBalance);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	String deletefromLoan = "delete from bankloan where acc# = (select acc# from bankaccount where custid = (select custid from bankcustomer where username = '"+username+"'))";
    	DatabaseConnection connectNow4 = new DatabaseConnection();
    	Connection connectDB4 = connectNow4.getConnection();
    	try {
    		Statement st4 = connectDB4.createStatement();
    		int rs4 = st4.executeUpdate(deletefromLoan);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	String deletefromLoanApp = "delete from bankloanapplication where acc# = (select acc# from bankaccount where custid = (select custid from bankcustomer where username = '"+username+"'))";
    	DatabaseConnection connectNow5 = new DatabaseConnection();
    	Connection connectDB5 = connectNow5.getConnection();
    	try {
    		Statement st5 = connectDB5.createStatement();
    		int rs5 = st5.executeUpdate(deletefromLoanApp);
    		paymentMessageLabel.setText("Payment Made SuccessFully\nNow You Can Apply For\n More Loans");
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }

}
