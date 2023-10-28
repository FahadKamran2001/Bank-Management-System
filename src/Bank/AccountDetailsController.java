package Bank;

import java.io.File;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.ResultSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class AccountDetailsController {

    @FXML
    private Button closeButton;

    @FXML
    private Label infoShow;

    @FXML
    private Button showdetailsButton;

    @FXML
    void closeButtonOnAction(ActionEvent event) {
    	Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
    }

    @FXML
    void showInfoOnAction(ActionEvent event) {
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
    	String accountdetailsQuery = "Select * from bankAccount where custid = (select custid from bankcustomer where username = '"+username+"')";
    	String account = "";
    	String balance = "";
    	String createD = "";
    	String custid = "";
    	String status = "";
    	DatabaseConnection connectNow = new DatabaseConnection();
    	Connection connectDB = connectNow.getConnection();
        try{
        	Statement st= connectDB.createStatement();
            ResultSet rs = st.executeQuery(accountdetailsQuery);
            while(rs.next()) {
            	account = rs.getString(1);
            	balance = rs.getString(2);
            	createD = rs.getString(3);
            	custid = rs.getString(4);
            	status = rs.getString(5);
            }
            infoShow.setText("Account# : \t"+account+"\nBalance(PKR.) : "+balance+"\nCreation Date : "+createD+"\nCustID : \t"+custid+"\nStatus : \t"+status);

        }catch (Exception e){
            e.printStackTrace();
        }
    	
    }

}