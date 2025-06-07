package GUI;

import javafx.geometry.Pos;
import javafx.scene.control.TextField; // Corrected import
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.awt.*;

public class UserControls extends VBox {
    public UserControls(Runnable test) {
        super(10);
        this.setAlignment(Pos.CENTER);

        this.m_animalControls = new AnimalControls();
        this.getChildren().add(m_animalControls);
        this.getChildren().add(new SimulationControls());
    }

    private AnimalControls m_animalControls;
}