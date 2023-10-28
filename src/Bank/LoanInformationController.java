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

public class LoanInformationController {

    @FXML
    private Button closeButton;

    @FXML
    private Label infoShow;

    @FXML
    private Button showStatusButton;

    @FXML
    void closeButtonOnAction(ActionEvent event) {
    	Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
    }

    @FXML
    void showInfoOnAction(ActionEvent event) {
    	//first checking if there is no previous loan
    	boolean flag = false;
    	String loancheck = "";
    	loancheck = "select count(*) from BankLoan where accc# = (select acc# from BankAccount where custid = (select custid from BankCustomer where username = 'username'))";
    	int appliCount = 0;
    	
    	String infoQuery = "";
    	String username = "";
    	try {
    		File myObj2 = new File("username.txt");
    	    Scanner myReader2 = new Scanner(myObj2);
    	    while (myReader2.hasNextLine()) {
    	        username = myReader2.nextLine();
    	        infoQuery = "select * from BankLoan where acc# = (select acc# from bankaccount where custid = (select custid from bankcustomer where username = '"+username+"'))";
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
	    			if(appliCount <= 0) {
	    				flag = true;
	    				infoShow.setText("No Granted Application");
	    			}
    			}
    	}
    	catch(Exception e2) {
    		e2.printStackTrace();
    	}
    	
    	if(flag == false) {
    		DatabaseConnection connectNow = new DatabaseConnection();
        	Connection connectDB = connectNow.getConnection();
        	try {
        		Statement statement = connectDB.createStatement();
        		ResultSet queryResult = statement.executeQuery(infoQuery);
        			String text = "";
        			while(queryResult.next()) {
    	    				text = text + "LoanID:"+queryResult.getString(1)+"\nAccount#:"+queryResult.getString(2)+"\nAmount(PKR.):"+queryResult.getString(3)+"\nInterest Rate(%):"+queryResult.getString(4)+"\nAccepted Date:"+queryResult.getString(5)+"\nEnd Date:"+queryResult.getString(6)+"\n";
    	    			}
        			infoShow.setText(text);
        	}
        	catch(Exception e2) {
        		e2.printStackTrace();
        	}
        	
        }
    }
    

}