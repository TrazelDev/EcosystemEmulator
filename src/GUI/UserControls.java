package GUI;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.control.Alert;

import java.util.function.IntSupplier;
import java.util.function.Supplier;

public class UserControls extends VBox {
    public UserControls(SimulatorCreator simulatorCreator, IntSupplier simulationRunNextDay) {
        super(10);
        this.setAlignment(Pos.CENTER);

        this.m_simulatorCreator = simulatorCreator;
        this.m_animalControls = new AnimalControls();
        this.getChildren().add(m_animalControls);
        this.getChildren().add(new SimulationControls(this::createSimulation, simulationRunNextDay));
    }

    private void createSimulation() {
        try {
            int numCarnivores = m_animalControls.getNumCarnivores();
            int numHerbivores = m_animalControls.getNumHerbivores();
            int numPlants = m_animalControls.getNumPlants();
            m_simulatorCreator.createSimulator(numCarnivores, numHerbivores, numPlants);
        } catch (IlegalOrganismInput e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    private SimulatorCreator m_simulatorCreator;
    private AnimalControls m_animalControls;
}