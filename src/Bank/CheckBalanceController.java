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

public class CheckBalanceController {

	
	@FXML
    private Button closeButton;

    @FXML
    private Label balanceShow;
    
    @FXML
    private Button showBalanceButton;
    
    public void showBalanceButtonOnAction(ActionEvent e) {
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
	    			String s = queryResult.getString(1);
	    			balanceShow.setText(s);
	    			
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
