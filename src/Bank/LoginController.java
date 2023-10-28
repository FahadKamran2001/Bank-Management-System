package Bank;
import java.io.FileWriter;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.AnchorPane;
import javafx.event.*;
import javafx.stage.*;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class LoginController{
	
	@FXML
	private Button cancelButton;
	
	@FXML Button loginButton;
	
	@FXML
	private Label loginMessageLabel;
	
	@FXML
	private TextField usernameField;
	@FXML
	private PasswordField passwordField;
	
	public void loginButtonOnAction(ActionEvent e) {
		if(usernameField.getText().isBlank() == false && passwordField.getText().isBlank() == false) {
			validateLogin();
		}
		else {
			loginMessageLabel.setText("Please Enter Username and Password!");
		}
	}
	
	public void cancelButtonOnAction(ActionEvent e) {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
		
	}
	
	public void registerButtonOnAction(ActionEvent e) {
		createAccountForm();
	}
	
	public void validateLogin() {
		DatabaseConnection connectNow = new DatabaseConnection();
		Connection connectDB = connectNow.getConnection();
		String verifyLoginCust = "SELECT count(1) from BankCustomer WHERE username = '" + usernameField.getText() + "' and password = '" + passwordField.getText() + "'";
		String verifyLoginAdm = "SELECT count(1) from BankAdmin WHERE username = '" + usernameField.getText() + "' and password = '" + passwordField.getText() + "'";
		try {
			Statement statement = connectDB.createStatement();
			ResultSet queryResult = statement.executeQuery(verifyLoginCust);
			int columnindex = 1;
			while(queryResult.next()) {
				if(queryResult.getInt(columnindex)==1) {
					loginMessageLabel.setText("Welcome Customer!");
					 try {
					      FileWriter myWriter = new FileWriter("username.txt");
					      myWriter.write(usernameField.getText());
					      myWriter.close();
					    } catch (IOException e) {
					      e.printStackTrace();
					    }
					 try {
					      FileWriter myWriter = new FileWriter("type.txt");
					      myWriter.write("Customer");
					      myWriter.close();
					    } catch (IOException e) {
					      e.printStackTrace();
					    }
					Stage stage = (Stage) loginButton.getScene().getWindow();
		    		stage.close();
		    		createCustomerMenu();
		    		
				}
				else {
					ResultSet queryResult2 = statement.executeQuery(verifyLoginAdm);
					while(queryResult2.next()) {
						if(queryResult2.getInt(columnindex)==1) {
							loginMessageLabel.setText("Welcome Admin!");
							try {
							      FileWriter myWriter = new FileWriter("username.txt");
							      myWriter.write(usernameField.getText());
							      myWriter.close();
							    } catch (IOException e) {
							      e.printStackTrace();
							    }
							try {
							      FileWriter myWriter = new FileWriter("type.txt");
							      myWriter.write("Admin");
							      myWriter.close();
							    } catch (IOException e) {
							      e.printStackTrace();
							    }
							Stage stage = (Stage) loginButton.getScene().getWindow();
				    		stage.close();
				    		createAdminMenu();
						}
						else {
							loginMessageLabel.setText("Invalid Login. Please Try Again.");
						}
					}
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createAccountForm() {
		try {
			AnchorPane register = (AnchorPane)FXMLLoader.load(getClass().getResource("Register.fxml"));
			Stage registerStage = new Stage();
			registerStage.initStyle(StageStyle.UNDECORATED);
			Scene scene = new Scene(register,750,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			registerStage.setScene(scene);
			registerStage.show();
		}catch(Exception e){
			e.printStackTrace();
			e.getCause();
		}
		
	}
	
	public void createCustomerMenu() {
		try {
			AnchorPane menu = (AnchorPane)FXMLLoader.load(getClass().getResource("CustomerMenu.fxml"));
			Stage customerMenuStage = new Stage();
			customerMenuStage.initStyle(StageStyle.UNDECORATED);
			Scene scene = new Scene(menu,750,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			customerMenuStage.setScene(scene);
			customerMenuStage.show();
		}catch(Exception e){
			e.printStackTrace();
			e.getCause();
		}
	}
	
	public void createAdminMenu() {
		try {
			AnchorPane menu = (AnchorPane)FXMLLoader.load(getClass().getResource("AdminMenu.fxml"));
			Stage adminMenuStage = new Stage();
			adminMenuStage.initStyle(StageStyle.UNDECORATED);
			Scene scene = new Scene(menu,750,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			adminMenuStage.setScene(scene);
			adminMenuStage.show();
		}catch(Exception e){
			e.printStackTrace();
			e.getCause();
		}
	}
	
	
}













