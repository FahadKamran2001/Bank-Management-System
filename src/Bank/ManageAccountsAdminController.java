package Bank;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ManageAccountsAdminController implements Initializable{

    @FXML
    private Button closeButton;

    @FXML
    private Button onoffButton;

    @FXML
    private TableView<CustomerTableModel> CustomerAccountsTable;

    @FXML
    private TableColumn<CustomerTableModel, String> custidCol;

    @FXML
    private TableColumn<CustomerTableModel, String> nameCol;

    @FXML
    private TableColumn<CustomerTableModel, String> phonenumCol;

    @FXML
    private TableColumn<CustomerTableModel, String> emailCol;

    @FXML
    private TableColumn<CustomerTableModel, String> usernameCol;

    @FXML
    private TableColumn<CustomerTableModel, String> passwordCol;

    @FXML
    private TextField idField;

    @FXML
    private Button deleteButton;

    @FXML
    private Label manageMessageLabel;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showCustomers();
 
    }

    public ObservableList<CustomerTableModel> getCustomerList(){
        ObservableList<CustomerTableModel> List = FXCollections.observableArrayList();
        DatabaseConnection connectNow = new DatabaseConnection();
    	Connection connectDB = connectNow.getConnection();
        String query = "select * from BankCustomer";
        Statement st;
        ResultSet rs;
        try{
            st = connectDB.createStatement();
            rs = st.executeQuery(query);
            CustomerTableModel row ;
            while(rs.next()){
	                row = new CustomerTableModel(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
	                List.add(row);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return List;
    }
 
    public void showCustomers(){
        ObservableList<CustomerTableModel> list = getCustomerList();
        custidCol.setCellValueFactory(new PropertyValueFactory<CustomerTableModel, String>("custidCol"));
	   	nameCol.setCellValueFactory(new PropertyValueFactory<CustomerTableModel, String>("nameCol"));
	   	phonenumCol.setCellValueFactory(new PropertyValueFactory<CustomerTableModel, String>("phonenumCol"));
	   	emailCol.setCellValueFactory(new PropertyValueFactory<CustomerTableModel, String>("emailCol"));
	   	usernameCol.setCellValueFactory(new PropertyValueFactory<CustomerTableModel, String>("usernameCol"));
	   	passwordCol.setCellValueFactory(new PropertyValueFactory<CustomerTableModel, String>("passwordCol"));
 
        CustomerAccountsTable.setItems(list)  ;
    }

    @FXML
    void closeButtonOnAction(ActionEvent event) {
    	Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
    }


    @FXML
    void deleteButtonOnAction(ActionEvent event) {
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
    	if(idField.getText().isBlank() == false) {
    		String custid = idField.getText();
    		String deletetransQuery = "delete from BankTransaction where acc# = (select acc# from bankaccount where custid = '"+custid+"')";
    		DatabaseConnection connectNow = new DatabaseConnection();
        	Connection connectDB = connectNow.getConnection();
            try{
            	Statement st= connectDB.createStatement();
                int rs = st.executeUpdate(deletetransQuery);
                String deleteloanappQuery = "delete from Bankloanapplication where acc# = (select acc# from bankaccount where custid = '"+custid+"')";
        		DatabaseConnection connectNow2 = new DatabaseConnection();
            	Connection connectDB2 = connectNow2.getConnection();
                try{
                	Statement st2= connectDB2.createStatement();
                    int rs2 = st2.executeUpdate(deleteloanappQuery);
                    String deleteloanQuery = "delete from Bankloan where acc# = (select acc# from bankaccount where custid = '"+custid+"')";
            		DatabaseConnection connectNow3 = new DatabaseConnection();
                	Connection connectDB3 = connectNow3.getConnection();
                    try{
                    	Statement st3= connectDB3.createStatement();
                        int rs3 = st3.executeUpdate(deleteloanQuery);
                        String deleteaccountQuery = "delete  from bankaccount where custid = '"+custid+"'";
                		DatabaseConnection connectNow4 = new DatabaseConnection();
                    	Connection connectDB4 = connectNow4.getConnection();
                        try{
                        	Statement st4= connectDB4.createStatement();
                            int rs4 = st4.executeUpdate(deleteaccountQuery);
                            String deletecustomerQuery = "delete from Bankcustomer where custid = '"+custid+"'";
                    		DatabaseConnection connectNow5 = new DatabaseConnection();
                        	Connection connectDB5 = connectNow5.getConnection();
                            try{
                            	Statement st5= connectDB5.createStatement();
                                int rs5 = st5.executeUpdate(deletecustomerQuery);
                                manageMessageLabel.setText("Chosen Customer Data Deleted");
                            }
                            catch(Exception e2) {
                            	e2.printStackTrace();
                            }
                        }
                        catch(Exception e2) {
                        	e2.printStackTrace();
                        }
                    }
                    catch(Exception e2) {
                    	e2.printStackTrace();
                    }
                }
                catch(Exception e2) {
                	e2.printStackTrace();
                }
            }
            catch(Exception e2) {
            	e2.printStackTrace();
            }
            
    	}
    	else {
    		
    	}
    	
    	
    	
    }


}
