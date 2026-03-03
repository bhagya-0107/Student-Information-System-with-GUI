import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private TableView<Student> table;
    private ObservableList<Student> studentList;

    @Override
    public void start(Stage stage) {

        // Input Fields
        TextField idField = new TextField();
        idField.setPromptText("Student ID");

        TextField nameField = new TextField();
        nameField.setPromptText("Student Name");

        TextField courseField = new TextField();
        courseField.setPromptText("Course");

        Button addButton = new Button("Add Student");
        Button deleteButton = new Button("Delete Selected");

        // Table
        table = new TableView<>();
        studentList = FXCollections.observableArrayList();

        TableColumn<Student, String> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getId()));

        TableColumn<Student, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));

        TableColumn<Student, String> courseCol = new TableColumn<>("Course");
        courseCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCourse()));

        table.getColumns().addAll(idCol, nameCol, courseCol);
        table.setItems(studentList);

        // Add Button Action
        addButton.setOnAction(e -> {
            if (!idField.getText().isEmpty() &&
                !nameField.getText().isEmpty() &&
                !courseField.getText().isEmpty()) {

                Student student = new Student(
                        idField.getText(),
                        nameField.getText(),
                        courseField.getText()
                );

                studentList.add(student);

                idField.clear();
                nameField.clear();
                courseField.clear();
            }
        });

        // Delete Button Action
        deleteButton.setOnAction(e -> {
            Student selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                studentList.remove(selected);
            }
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(idField, nameField, courseField, addButton, deleteButton, table);

        Scene scene = new Scene(layout, 500, 500);

        stage.setTitle("Student Management System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}