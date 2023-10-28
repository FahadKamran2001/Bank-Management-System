package Bank;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.net.URL;
import java.util.ResourceBundle;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.image.*;
import javafx.fxml.Initializable;


public class CustomerMenuController{

    @FXML
    private Button checkBalanceButton;

    @FXML
    private Button withdrawCashButton;

    @FXML
    private Button depositAmountButton;

    @FXML
    private Button applyForLoanButton;

    @FXML
    private Button checkLoanStatusButton;

    @FXML
    private Button manageAccountButton;

    @FXML
    private Button accountDetailsButton;

    @FXML
    private Button transactionHistoryButton;

    @FXML
    private Button LoanPayementButton;

    @FXML
    private Button loanHistoryButton;
    
    @FXML
    private Button closeButton;
    
    @FXML
    private Button logoutButton;
    
    
    
    public void logoutButtonOnAction(ActionEvent e) {
    	Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
    	try {
		      FileWriter myWriter = new FileWriter("username.txt");
		      myWriter.close();
		    } catch (IOException e7) {
		      e7.printStackTrace();
		    }
		try {
		      FileWriter myWriter = new FileWriter("type.txt");
		      myWriter.close();
		    } catch (IOException e8) {
		      e8.printStackTrace();
		    }
    	try {
			BorderPane login = (BorderPane)FXMLLoader.load(getClass().getResource("Login.fxml"));
			Stage returntoLoginStage = new Stage();
			returntoLoginStage.initStyle(StageStyle.UNDECORATED);
			Scene scene = new Scene(login,750,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			returntoLoginStage.setScene(scene);
			returntoLoginStage.show();
		}catch(Exception e6){
			e6.printStackTrace();
			e6.getCause();
		}
    }
    
    
    public void closeButtonOnAction(ActionEvent e) {
    	Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
    }
    
    public void checkBalanceOnAction(ActionEvent e) {
    	try {
			AnchorPane cbalance = (AnchorPane)FXMLLoader.load(getClass().getResource("CheckBalance.fxml"));
			Stage checkbalanceStage = new Stage();
			checkbalanceStage.initStyle(StageStyle.UNDECORATED);
			Scene scene = new Scene(cbalance,750,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			checkbalanceStage.setScene(scene);
			checkbalanceStage.show();
		}catch(Exception e4){
			e4.printStackTrace();
			e4.getCause();
		}
    }
    
    public void withdrawCashOnAction(ActionEvent e) {
    	try {
			AnchorPane withdraw = (AnchorPane)FXMLLoader.load(getClass().getResource("WithdrawCash.fxml"));
			Stage withdrawCashStage = new Stage();
			withdrawCashStage.initStyle(StageStyle.UNDECORATED);
			Scene scene = new Scene(withdraw,750,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			withdrawCashStage.setScene(scene);
			withdrawCashStage.show();
		}catch(Exception e4){
			e4.printStackTrace();
			e4.getCause();
		}
    	
    }
    
    public void depositAmountOnAction(ActionEvent e) {
    	try {
			AnchorPane deposit = (AnchorPane)FXMLLoader.load(getClass().getResource("DepositAmount.fxml"));
			Stage depositAmountStage = new Stage();
			depositAmountStage.initStyle(StageStyle.UNDECORATED);
			Scene scene = new Scene(deposit,750,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			depositAmountStage.setScene(scene);
			depositAmountStage.show();
		}catch(Exception e4){
			e4.printStackTrace();
			e4.getCause();
		}
    	
    }
    
    public void applyLoanOnAction(ActionEvent e) {
    	try {
			AnchorPane applyloan = (AnchorPane)FXMLLoader.load(getClass().getResource("ApplyLoan.fxml"));
			Stage loanapplicationStage = new Stage();
			loanapplicationStage.initStyle(StageStyle.UNDECORATED);
			Scene scene = new Scene(applyloan,750,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			loanapplicationStage.setScene(scene);
			loanapplicationStage.show();
		}catch(Exception e4){
			e4.printStackTrace();
			e4.getCause();
		}
    	
    }
    
    public void loanStatusOnAction(ActionEvent e) {
    	try {
			AnchorPane loanstatus = (AnchorPane)FXMLLoader.load(getClass().getResource("LoanStatus.fxml"));
			Stage loanstatusStage = new Stage();
			loanstatusStage.initStyle(StageStyle.UNDECORATED);
			Scene scene = new Scene(loanstatus,750,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			loanstatusStage.setScene(scene);
			loanstatusStage.show();
		}catch(Exception e4){
			e4.printStackTrace();
			e4.getCause();
		}
    	
    }
    
    public void loanInformationOnAction(ActionEvent e) {
    	try {
			AnchorPane loanInformation = (AnchorPane)FXMLLoader.load(getClass().getResource("LoanInformation.fxml"));
			Stage loaninformationStage = new Stage();
			loaninformationStage.initStyle(StageStyle.UNDECORATED);
			Scene scene = new Scene(loanInformation,750,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			loaninformationStage.setScene(scene);
			loaninformationStage.show();
		}catch(Exception e4){
			e4.printStackTrace();
			e4.getCause();
		}
    	
    }
    
    public void accountDetailsOnAction(ActionEvent e) {
    	try {
			AnchorPane accountdetails = (AnchorPane)FXMLLoader.load(getClass().getResource("AccountDetails.fxml"));
			Stage accountdetailsStage = new Stage();
			accountdetailsStage.initStyle(StageStyle.UNDECORATED);
			Scene scene = new Scene(accountdetails,750,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			accountdetailsStage.setScene(scene);
			accountdetailsStage.show();
		}catch(Exception e4){
			e4.printStackTrace();
			e4.getCause();
		}
    	
    }
    
    public void manageAccountCustomerOnAction(ActionEvent e) {
    	try {
			AnchorPane accountmanagement = (AnchorPane)FXMLLoader.load(getClass().getResource("ManageAccountCustomer.fxml"));
			Stage accountmanagementStage = new Stage();
			accountmanagementStage.initStyle(StageStyle.UNDECORATED);
			Scene scene = new Scene(accountmanagement,750,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			accountmanagementStage.setScene(scene);
			accountmanagementStage.show();
		}catch(Exception e4){
			e4.printStackTrace();
			e4.getCause();
		}
    	
    }
    
    public void loanPaymentOnAction(ActionEvent e) {
    	try {
			AnchorPane loanPayment = (AnchorPane)FXMLLoader.load(getClass().getResource("LoanPayment.fxml"));
			Stage loanPaymentStage = new Stage();
			loanPaymentStage.initStyle(StageStyle.UNDECORATED);
			Scene scene = new Scene(loanPayment,750,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			loanPaymentStage.setScene(scene);
			loanPaymentStage.show();
		}catch(Exception e4){
			e4.printStackTrace();
			e4.getCause();
		}
    	
    }
    
    public void transactionHistoryOnAction(ActionEvent e) {
    	try {
			AnchorPane transactionHistory = (AnchorPane)FXMLLoader.load(getClass().getResource("TransactionHistory.fxml"));
			Stage transactionHistoryStage = new Stage();
			transactionHistoryStage.initStyle(StageStyle.UNDECORATED);
			Scene scene = new Scene(transactionHistory,750,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			transactionHistoryStage.setScene(scene);
			transactionHistoryStage.show();
		}catch(Exception e4){
			e4.printStackTrace();
			e4.getCause();
		}
    	
    }
    

}
