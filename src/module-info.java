module sdaProject {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires javafx.base;
	
	opens Bank to javafx.graphics, javafx.fxml, java.sql,javafx.base;
}
