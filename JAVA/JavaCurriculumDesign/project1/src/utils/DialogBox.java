package utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;

public class DialogBox  {

    public static void warning(String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("warning!");
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.getDialogPane().setStyle("" +
                "-fx-alignment: CENTER;" +
                "-fx-font-size: 16px;" +
                "-fx-font-family: '微软雅黑';" +
                "-fx-font-weight: bold;");
        alert.showAndWait();
    }

    public static boolean confirm(String text) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("confirm");
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.getDialogPane().setStyle("" +
                "-fx-alignment: CENTER;" +
                "-fx-font-size: 16px;" +
                "-fx-font-family: '微软雅黑';" +
                "-fx-font-weight: bold;");
        Optional<ButtonType> result = alert.showAndWait();
        return result.orElse(null) == ButtonType.OK;
    }

    public static void error(Exception ex) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Exception Dialog");
        alert.setHeaderText(null);
        alert.setContentText(ex.getClass().toString());

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label("The exception stacktrace was:");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

        alert.getDialogPane().setExpandableContent(expContent);
        alert.showAndWait();
    }
}
