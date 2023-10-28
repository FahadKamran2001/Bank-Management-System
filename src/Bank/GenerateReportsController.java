package Bank;

import java.io.File;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class GenerateReportsController {

    @FXML
    private Button closeButton;

    @FXML
    private Label promptMessageLabel;

    @FXML
    private Button allTransactionsButton;

    @FXML
    void allTransactionsButtonOnAction(ActionEvent event) {
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
    	try {
    		FileWriter myWriter = new FileWriter("report.txt");
    		DatabaseConnection connectNow = new DatabaseConnection();
        	Connection connectDB = connectNow.getConnection();
        	String query = "select * from BankTransaction";
        	try {
        		Statement statement = connectDB.createStatement();
        		ResultSet rs = statement.executeQuery(query);
        		myWriter.write("TransID\tAcc#\tTransactionDate\t\tDueDate\tAmount\t\tReason\n");
        			while(rs.next()) {
        				String transID = rs.getString(1);
        				String accountNum = rs.getString(2);
        				String transDate = rs.getString(3);
        				String amount = rs.getString(4);
        				String dueDate = rs.getString(5);
        				String reason = rs.getString(6);
        				myWriter.write(transID+"\t"+accountNum+"\t"+transDate+"\t"+dueDate+"\t"+amount+"\t"+reason+"\n");
        				
        			}
        	}catch(Exception e2) {
        		e2.printStackTrace();
        	}
    	    myWriter.close();
    	    promptMessageLabel.setText("*Report Generated*");
    	}catch(Exception e) {
    		promptMessageLabel.setText("*Failed to Generate Report*");
    	}
    }

    @FXML
    void closeButtonOnAction(ActionEvent event) {
    	Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
    }

}
