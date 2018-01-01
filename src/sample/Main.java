package sample;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application {

    private static String[] list;
    private ObservableList<String> lst;
    private String[] values = null;

    static {
        list = new String[] {
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,",
                "when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                "String4",
                "String5"
        };
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Hello World");
        GridPane pane = new GridPane();

        Button button = new Button(">");
        Label label = new Label("");
        TableView<String> table = new TableView<>();

        table.setMinWidth(300);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        table.getItems().addAll(list);
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        TableColumn<String, String> column = new TableColumn<>();
        column.setCellValueFactory(c -> new SimpleStringProperty(c.getValue()));


        TableView<String> table2 = new TableView<>();
        table2.setMinWidth(300);
        table2.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<String, String> col = new TableColumn<>();
        col.setCellValueFactory(c -> new SimpleStringProperty(c.getValue()));


        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                lst = table.getSelectionModel().getSelectedItems();
                values = new String[lst.size()];
                lst.toArray(values);
                table2.getItems().clear();
                table2.getItems().addAll(values);


                String labelText = "";
                for (String elem : lst) {
                    labelText += (elem + " ");
                }

                if (labelText.length() > 100) {
                    Stage stage = new Stage();

                    stage.setTitle("New");
                    stage.initOwner(primaryStage);
                    stage.initModality(Modality.APPLICATION_MODAL);

                    VBox vbox = new VBox();

                    Label modalLabel = new Label(labelText);

                    vbox.getChildren().addAll(modalLabel);

                    Scene scene = new Scene(vbox, 800, 270);

                    stage.setScene(scene);
                    stage.showAndWait();
                } else {
                    label.setText(labelText);
                }
            }
        });


        table2.getColumns().add(col);

        table.getColumns().add(column);

        pane.add(table, 0, 0, 2, 3);
        pane.add(button, 2, 2);
        pane.add(table2, 3, 0, 2, 3);
        pane.add(label, 0, 3);

        primaryStage.setScene(new Scene(pane, 640, 480));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
