package GUI;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

import java.util.function.IntSupplier;
import java.util.function.Supplier;

public class SimulationControls extends HBox {
    public SimulationControls(Runnable simulatorCreator, IntSupplier nextDay) {
        super(10);
        this.setAlignment(Pos.CENTER_LEFT);

        this.m_nextDay = nextDay;
        this.m_dayIndicator = new Label("Day: 0");
        this.getChildren().add(m_dayIndicator);

        Button btnNextDay = new Button("Next Day");
        btnNextDay.setOnAction(e -> nextDay());
        this.getChildren().add(btnNextDay);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        this.getChildren().add(spacer);

        Button btnNewSimulation = new Button("New Simulation");
        btnNewSimulation.setOnAction(
                e -> {
                    m_dayIndicator.setText("Day: 0");
                    simulatorCreator.run();
                }
        );

        this.getChildren().add(btnNewSimulation);
    }

    public void nextDay() {
        int newDay = m_nextDay.getAsInt();
        m_dayIndicator.setText("Day: " + newDay);
    }

    private IntSupplier m_nextDay;
    private Label m_dayIndicator;
}