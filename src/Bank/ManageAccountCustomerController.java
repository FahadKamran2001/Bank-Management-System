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

public class ManageAccountCustomerController {

    @FXML
    private Button closeButton;

    @FXML
    private Label activeMessageLabel;

    @FXML
    private Button activateButton;

    @FXML
    private Button deactivateButton;

    @FXML
    void activateOnAction(ActionEvent event) {
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
    	String checkifAccountActive = "select count(*) from bankaccount where status = 'Active' and custid = (select custid from bankcustomer where username = '"+username+"')";
    	DatabaseConnection connectNow = new DatabaseConnection();
    	Connection connectDB = connectNow.getConnection();
    	String check = "";
    	try {
    		Statement st= connectDB.createStatement();
            ResultSet rs = st.executeQuery(checkifAccountActive);
            while(rs.next()) {
            	check = rs.getString(1);
            }
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	if(check.equals("1")) {
    		activeMessageLabel.setText("Account Already Active");
    	}
    	else {
    		String activationQuery = "update bankaccount set status = 'Active' where custid = (select custid from bankcustomer where username = '"+username+"')";
    		DatabaseConnection connectNow2 = new DatabaseConnection();
        	Connection connectDB2 = connectNow2.getConnection();
        	try {
        		Statement st2 = connectDB2.createStatement();
        		int rs2 = st2.executeUpdate(activationQuery);
        		activeMessageLabel.setText("Account Activated");
        	}catch(Exception e) {
        		e.printStackTrace();
        		activeMessageLabel.setText("Unable to Activate Account");
        	}
    	}
    	
    }

    @FXML
    void closeButtonOnAction(ActionEvent event) {
    	Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
    }

    @FXML
    void deactivateOnAction(ActionEvent event) {
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
    	String checkifAccountActive = "select count(*) from bankaccount where status = 'DeActive' and custid = (select custid from bankcustomer where username = '"+username+"')";
    	DatabaseConnection connectNow = new DatabaseConnection();
    	Connection connectDB = connectNow.getConnection();
    	String check = "";
    	try {
    		Statement st= connectDB.createStatement();
            ResultSet rs = st.executeQuery(checkifAccountActive);
            while(rs.next()) {
            	check = rs.getString(1);
            }
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	if(check.equals("1")) {
    		activeMessageLabel.setText("Account Already DeActive");
    	}
    	else {
    		String activationQuery = "update bankaccount set status = 'DeActive' where custid = (select custid from bankcustomer where username = '"+username+"')";
    		DatabaseConnection connectNow2 = new DatabaseConnection();
        	Connection connectDB2 = connectNow2.getConnection();
        	try {
        		Statement st2 = connectDB2.createStatement();
        		int rs2 = st2.executeUpdate(activationQuery);
        		activeMessageLabel.setText("Account DeActivated");
        	}catch(Exception e) {
        		e.printStackTrace();
        		activeMessageLabel.setText("Unable to DeActivate Account");
        	}
    	}
    }

}
