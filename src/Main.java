import Simulator.Simulator;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(2)); // Add some padding around the grid
        gridPane.setHgap(2); // Horizontal gap between cells (creates lines)
        gridPane.setVgap(2); // Vertical gap between cells (creates lines)
        gridPane.setGridLinesVisible(true); // Makes the grid lines visible
        gridPane.setStyle("-fx-background-color: black;");

        Image cellImage = null;
        try {
            // Attempt to load from resources (common for project-internal images)
            // Make sure "images.jpg" is in your project's classpath (e.g., in src or resources folder)
            cellImage = new Image(getClass().getResourceAsStream("/Resources/carnivore.jpg"));
        } catch (NullPointerException e) {
            System.err.println("Error: Image 'images.jpg' not found! Make sure it's in your classpath (e.g., src folder).");
            // Fallback for demonstration if image is missing: create a tiny dummy image
            cellImage = new Image("file:./default_missing_image.png", 60, 60, false, false); // Dummy image, size 60x60
        }


        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                ImageView imageView = new ImageView(cellImage);
                imageView.setFitWidth(60);
                imageView.setFitHeight(60);
                imageView.setPreserveRatio(false); // Set to false if you want them to perfectly fill the square
                // true maintains original aspect ratio within the cell
                imageView.setSmooth(true); // Smoothens the image if scaled

                final int finalRow = row;
                final int finalCol = col;
                imageView.setOnMouseClicked(event -> {
                    Stage popupStage = new Stage();
                    popupStage.initOwner(primaryStage);
                    popupStage.initModality(Modality.APPLICATION_MODAL);
                    popupStage.setTitle("Popup window");

                    Label hiLabel = new Label("row: " + finalRow + ", column: " + finalCol);
                    StackPane popupRoot = new StackPane(hiLabel); // StackPane centers its child
                    popupRoot.setPrefSize(200, 100); // Smallest possible pop-up

                    popupStage.setScene(new Scene(popupRoot));
                    popupStage.showAndWait();
                });

                // Add the ImageView to the grid at the specified column and row
                gridPane.add(imageView, col, row);
            }
        }

        primaryStage.setTitle("Virtual Eco Simulator");
        primaryStage.setScene(new Scene(gridPane, 650, 650));
        primaryStage.show();
    }
}