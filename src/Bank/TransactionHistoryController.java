package Bank;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.control.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.fxml.Initializable;
import javafx.beans.property.StringProperty;
import java.lang.Boolean;
import java.net.URL;
import javafx.collections.ObservableList;

public class TransactionHistoryController implements Initializable{
	
	@FXML
    private Button closeButton;

    @FXML
    private TableView<TransactionHistoryTableModel> transHistoryTable;

    @FXML
    private TableColumn<TransactionHistoryTableModel, String> transidCol;

    @FXML
    private TableColumn<TransactionHistoryTableModel, String> accnumCol;

    @FXML
    private TableColumn<TransactionHistoryTableModel, String> amountCol;

    @FXML
    private TableColumn<TransactionHistoryTableModel, String> transDateCol;

    @FXML
    private TableColumn<TransactionHistoryTableModel, String> dueDateCol;
    
    @FXML
    private TableColumn<TransactionHistoryTableModel, String> reasonCol;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showTransHistory();
    }
    
    public ObservableList<TransactionHistoryTableModel> getTransactionHistoryList(){
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
        ObservableList<TransactionHistoryTableModel> List = FXCollections.observableArrayList();
        DatabaseConnection connectNow = new DatabaseConnection();
    	Connection connectDB = connectNow.getConnection();
        String query = "select * from BankTransaction where acc# = (select acc# from bankaccount where custid = (select custid from bankcustomer where username = '"+username+"'))";
        Statement st;
        ResultSet rs;
        try{
            st = connectDB.createStatement();
            rs = st.executeQuery(query);
            TransactionHistoryTableModel row ;
            while(rs.next()){
                row = new TransactionHistoryTableModel(rs.getString(1), rs.getString(2), rs.getString(4), rs.getString(3), rs.getString(5), rs.getString(6));
                List.add(row);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return List;
    }
 
    public void showTransHistory(){
        ObservableList<TransactionHistoryTableModel> list = getTransactionHistoryList();
        transidCol.setCellValueFactory(new PropertyValueFactory<TransactionHistoryTableModel, String>("transidCol"));
	   	accnumCol.setCellValueFactory(new PropertyValueFactory<TransactionHistoryTableModel, String>("accnumCol"));
	   	amountCol.setCellValueFactory(new PropertyValueFactory<TransactionHistoryTableModel, String>("amountCol"));
	   	transDateCol.setCellValueFactory(new PropertyValueFactory<TransactionHistoryTableModel, String>("transDateCol"));
		dueDateCol.setCellValueFactory(new PropertyValueFactory<TransactionHistoryTableModel, String>("dueDateCol"));
	   	reasonCol.setCellValueFactory(new PropertyValueFactory<TransactionHistoryTableModel, String>("reasonCol"));
 
        transHistoryTable.setItems(list)  ;
    }
    
    @FXML
    void closeButtonOnAction(ActionEvent event) {
    	Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
    }


}
