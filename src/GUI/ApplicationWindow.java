package GUI;

import Simulator.Simulator;
import Simulator.BoardNotBigEnoughException;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;

public class ApplicationWindow extends BorderPane {
    public ApplicationWindow() {
        super();

        m_board = new GuiBoard();
        this.setCenter(m_board);

        m_userControls = new UserControls(this::createSimulator);
        this.setBottom(m_userControls);
    }

    void createSimulator(int numCarnivores, int numHerbivores, int numPlants) {
        try {
            simulator = new Simulator(numCarnivores, numHerbivores, numPlants);
        } catch (BoardNotBigEnoughException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    UserControls m_userControls;
    GuiBoard m_board;
    Simulator simulator = null;
}
