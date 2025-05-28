module Main {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.desktop;
	requires jdk.compiler;
	requires java.sql;
	requires javafx.swing;
	requires itextpdf;
	requires org.apache.logging.log4j.core;


	opens Main to javafx.fxml;
    exports Main;
	exports App.Stages;
	opens App.Stages to javafx.fxml;
}