package Bank;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner;

public class LoanStatusController {

    @FXML
    private Button closeButton;

    @FXML
    private Label statusShow;

    @FXML
    private Button showStatusButton;

    @FXML
    void closeButtonOnAction(ActionEvent event) {
    	Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
    }

    @FXML
    void showStatusButtonOnAction(ActionEvent event) {
    	String username = "";
    	String status = "";
    	try {
    		File myObj2 = new File("username.txt");
    	    Scanner myReader2 = new Scanner(myObj2);
    	    while (myReader2.hasNextLine()) {
    	        username = myReader2.nextLine();    	    }
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	String statusQuery = "select status from BankLoanApplication where acc# = (select acc# from BankAccount where custid = (select custid from BankCustomer where username = '"+username+"'))";
    	DatabaseConnection connectNow = new DatabaseConnection();
    	Connection connectDB = connectNow.getConnection();
    	try {
    		Statement statement = connectDB.createStatement();
    		ResultSet queryResult = statement.executeQuery(statusQuery);
    			while(queryResult.next()) {
	    			status = queryResult.getString(1);
	    			statusShow.setText(status);
    			}
    			statusShow.setText(status);
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		statusShow.setText("*No Application Sent*");
    	}
    }

}
