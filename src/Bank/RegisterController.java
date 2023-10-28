package Bank;

import javafx.fxml.FXML;
import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.event.*;
import javafx.stage.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import javafx.collections.transformation.*;
import javafx.scene.control.ComboBox;


public class RegisterController {
	
	@FXML
	private TextField registerBalance;

	@FXML
    private TextField registerName;

    @FXML
    private TextField registerPhoneNumber;

    @FXML
    private TextField registerEmail;

    @FXML
    private TextField registerPassword;

    @FXML
    private TextField registerRePassword;

    @FXML
    private TextField registerUsername;

    @FXML
    private TextField registerType;
    
    @FXML
    private Button registerRegisterButton;
    
    @FXML
    private Button registerCloseButton;
    
    @FXML
    private Label registerMessageLabel;
    
    @FXML
    private Label registerPasswordMessageLabel;
    
    
    public void closeButtonOnAction(ActionEvent e) {
    	Stage stage = (Stage) registerCloseButton.getScene().getWindow();
		stage.close();
    }
    
    public void registerButtonOnAction(ActionEvent e) {
    	
    	if(registerPassword.getText().equals(registerRePassword.getText())) {
    		registerPasswordMessageLabel.setText("User Registered Successfully");
    		registerPasswordMessageLabel.setText("Password Matched");
    		registerUser();
    	}
    	else {
    		registerPasswordMessageLabel.setText("Password Does Not Match");
    	}
    }
    
    public void registerUser() {
    	DatabaseConnection connectNow = new DatabaseConnection();
    	Connection connectDB = connectNow.getConnection();
    	String name = registerName.getText();
    	String username = registerUsername.getText();
    	String password = registerPassword.getText();
    	String email = registerEmail.getText();
    	String phoneNumber = registerPhoneNumber.getText();
    	String Type = registerType.getText();
    	String balance = registerBalance.getText();
    	int bal = Integer.parseInt(balance);
    	
    	String insertTable = "";
    	if(Type.equals("Admin")) {
    		insertTable = "BankAdmin";
    	}
    	else {
    		insertTable = "BankCustomer";
    	}
    	String countCheck = "select count(*) from "+insertTable;
    	int count = 0;
    	String counter ="";
    	try {
    		Statement statement = connectDB.createStatement();
    		ResultSet queryResult = statement.executeQuery(countCheck);
    			while(queryResult.next()) {
	    			String s = queryResult.getString(1);
	    			count = Integer.parseInt(s);
	    			count++;
	    			counter = Integer.toString(count);
    			}
    		}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	String countCheck2 = "select count(*) from bankaccount ";
    	int count2 = 0;
    	String counter2 ="";
    	try {
    		Statement statement = connectDB.createStatement();
    		ResultSet query2Result = statement.executeQuery(countCheck2);
    			while(query2Result.next()) {
	    			String s = query2Result.getString(1);
	    			count2 = Integer.parseInt(s);
	    			count2++;
	    			counter2 = Integer.toString(count);
    			}
    		}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	
    	String insertRegister = "Insert into "+insertTable+ " values('"+counter+"','"+name+"','"+phoneNumber+"','"+email+"','"+username+"','"+password+"')";
    	try {
    		Statement statement = connectDB.createStatement();
    		ResultSet registerQueryResult = statement.executeQuery(insertRegister);
    		registerPasswordMessageLabel.setText("User Registered Successfully");
    		String giveAccount = "insert into  BankAccount values('"+counter2+"',"+bal+",TO_DATE(sysdate, 'yyyy/mm/dd hh24:mi:ss'),'"+counter+"','Active')";
        	try {
        		Statement statement2 = connectDB.createStatement();
        		ResultSet query3Result = statement2.executeQuery(giveAccount);
        			
        		}catch(Exception e) {
        		e.printStackTrace();
        	}
    		Stage stage = (Stage) registerCloseButton.getScene().getWindow();
    		stage.close();
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
   
	
}
