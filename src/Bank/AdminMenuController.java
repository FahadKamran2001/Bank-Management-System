package Bank;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.io.FileWriter;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.AnchorPane;
import javafx.event.*;
import javafx.stage.*;
import javafx.fxml.Initializable;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.net.URL;
import java.util.ResourceBundle;
import java.io.File;
import javafx.scene.image.Image;

public class AdminMenuController implements Initializable {

    @FXML
    private Button manageAccountsButton;

    @FXML
    private Button generateReportsButton;

    @FXML
    private Button manageLoanButton;

    @FXML
    private Button logoutButton;
   
    
    @FXML
    private ImageView adminImg;
    
    
    
    @FXML Button closeButton;

    @Override
    public void initialize(URL url, ResourceBundle rs) {
    	File adminlogo = new File("linear-administrator-icon-human-resources-outline-collection-thin-line-isolated-white-background-trendy-illustration-140059931.jpg");
    	Image adminimage = new Image(adminlogo.toURI().toString());
    	adminImg.setImage(adminimage);    	
    }
    
    
    
    public void logoutButtonOnAction(ActionEvent e) {
    	Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
		try {
		      FileWriter myWriter = new FileWriter("username.txt");
		      myWriter.close();
		    } catch (IOException e11) {
		      e11.printStackTrace();
		    }
		try {
		      FileWriter myWriter = new FileWriter("type.txt");
		      myWriter.close();
		    } catch (IOException e12) {
		      e12.printStackTrace();
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
    
    public void MLAOnAction(ActionEvent e) {
    	try {
			AnchorPane loanApplications = (AnchorPane)FXMLLoader.load(getClass().getResource("MLA.fxml"));
			Stage loanapplicationStage = new Stage();
			loanapplicationStage.initStyle(StageStyle.UNDECORATED);
			Scene scene = new Scene(loanApplications,750,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			loanapplicationStage.setScene(scene);
			loanapplicationStage.show();
		}catch(Exception e4){
			e4.printStackTrace();
			e4.getCause();
		}
    	
    }
    
    public void ManageAccountsAdminOnAction(ActionEvent e) {
    	try {
			AnchorPane ManageAccountsAdmin = (AnchorPane)FXMLLoader.load(getClass().getResource("ManageAccountsAdmin.fxml"));
			Stage ManageAccountsAdminStage = new Stage();
			ManageAccountsAdminStage.initStyle(StageStyle.UNDECORATED);
			Scene scene = new Scene(ManageAccountsAdmin,750,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			ManageAccountsAdminStage.setScene(scene);
			ManageAccountsAdminStage.show();
		}catch(Exception e4){
			e4.printStackTrace();
			e4.getCause();
		}
    	
    }
    
    public void GenerateReportOnAction(ActionEvent e) {
    	try {
			AnchorPane GenerateReports = (AnchorPane)FXMLLoader.load(getClass().getResource("GenerateReports.fxml"));
			Stage GenerateReportsStage = new Stage();
			GenerateReportsStage.initStyle(StageStyle.UNDECORATED);
			Scene scene = new Scene(GenerateReports,750,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			GenerateReportsStage.setScene(scene);
			GenerateReportsStage.show();
		}catch(Exception e4){
			e4.printStackTrace();
			e4.getCause();
		}
    	
    }

}