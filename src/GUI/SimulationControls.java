package GUI;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class SimulationControls extends HBox {
    public SimulationControls(Runnable simulatorCreator) {
        super(10);
        this.setAlignment(Pos.CENTER_LEFT);


        this.m_dayIndicator = new Label("Day: 0");
        this.getChildren().add(m_dayIndicator);

        Button btnNextDay = new Button("Next Day");
        btnNextDay.setOnAction(e -> nextDay());
        this.getChildren().add(btnNextDay);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        this.getChildren().add(spacer);

        Button btnNewSimulation = new Button("New Simulation");
        btnNewSimulation.setOnAction(e -> simulatorCreator.run());
        this.getChildren().add(btnNewSimulation);
    }

    public void nextDay() {

    }
    private Label m_dayIndicator;
}
