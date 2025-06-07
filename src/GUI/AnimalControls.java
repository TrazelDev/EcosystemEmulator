package GUI;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class AnimalControls extends HBox {
    public AnimalControls() {
        super(10);
        this.setAlignment(Pos.CENTER);

        TextField numCarnivores = new TextField("0");
        TextField numHerbivores = new TextField("0");
        TextField numPlants = new TextField("0");
        Label label = new Label("Number of");
        makeFieldIntegerOnly(numCarnivores);
        makeFieldIntegerOnly(numHerbivores);
        makeFieldIntegerOnly(numPlants);

        this.getChildren().addAll(
                new Label("Carnivores"), numCarnivores,
                new Label("Herbivores"), numHerbivores,
                new Label("Plants"), numPlants
        );
    }

    public void makeFieldIntegerOnly(TextField field) {
        field.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                field.setText(oldValue);
            }
        });
    }
}