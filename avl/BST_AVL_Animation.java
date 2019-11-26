import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.util.HashSet;
import java.util.Optional;

//Begin Class BST_AVL_Animation
public class BST_AVL_Animation extends Application {

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        Avl<Integer> AVLTree = new Avl<>(); // Create an AVL tree

        BorderPane pane = new BorderPane();
        AVLView view1 = new AVLView(AVLTree);
        pane.setRight(view1);
        view1.setPrefWidth(250);
        pane.setPrefWidth(250);
        BorderPane.setMargin(view1, new Insets(10, 20, 10, 20));

        TextField tfKey = new TextField();
        tfKey.setPrefColumnCount(3);
        tfKey.setAlignment(Pos.BASELINE_RIGHT);
        Button btInsert = new Button("Insert");
        Button btDelete = new Button("Delete");
        Button btClear = new Button("Clear");
        Button btExit = new Button("Exit");
        HBox hBox = new HBox(20);
        hBox.getChildren().addAll(new Label("Enter a number: "),
                tfKey, btInsert, btDelete,  btClear, btExit);
        hBox.setAlignment(Pos.CENTER);
        pane.setBottom(hBox);
        hBox.setAlignment(Pos.CENTER);
        BorderPane.setMargin(hBox, new Insets(10, 10, 10, 10));


        btInsert.setOnAction(e -> {
                try {
                    int key = Integer.parseInt(tfKey.getText());
                        AVLTree.insertarElemento(key); // Insert a new key
                        view1.displayAVLTree();
                    
                    tfKey.setText("");
                    tfKey.requestFocus();
                } catch (NumberFormatException ex) {
                    invalidKey(tfKey, "Key must be an integer!");
                }
            }
        );

        btDelete.setOnAction(e -> {
                try {
                    int key = Integer.parseInt(tfKey.getText());
                        AVLTree.borrar(key);
                        view1.displayAVLTree();
                    
                    tfKey.setText("");
                } catch (NumberFormatException ex) {
                    invalidKey(tfKey, "Key must be an integer!");
                }
        });

        btClear.setOnAction(e -> {
            tfKey.clear();
            AVLTree.clear();
            view1.displayAVLTree();
            view1.setStatus("AVL Tree deleted");
        });

        btExit.setOnAction(e -> {
            Alert exit = new Alert(Alert.AlertType.CONFIRMATION);
            exit.setTitle("Goodbye!");
            exit.setContentText("Really quit?");
            Optional<ButtonType> result = exit.showAndWait();
            if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
                System.exit(0);
            }
        });

        // Create a scene and place the pane in the stage
        Scene scene = new Scene(pane, 650, 350);
        primaryStage.setTitle("BST/AVL Animation"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    /**
     * Warns user if an invalid key is entered
     *
     * @param key
     * @param alertHeader
     */
    private void invalidKey(TextField key, String alertHeader) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(alertHeader);
        alert.setContentText("Please enter an integer in the key box and try again");
        key.requestFocus();
        alert.showAndWait();
    }

    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }
}