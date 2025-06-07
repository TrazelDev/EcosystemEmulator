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

        m_userControls = new UserControls(this::createSimulator, this::advanceDay);
        this.setBottom(m_userControls);
    }

    void createSimulator(int numCarnivores, int numHerbivores, int numPlants) {
        try {
            simulator = new Simulator(numCarnivores, numHerbivores, numPlants);
            m_board.updateBoard(simulator.getBoard());
        } catch (BoardNotBigEnoughException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();

            simulator = null;
            m_board.resetBoard();
        }
    }

    int advanceDay() {
        int daysPassed = this.simulator.runDay();
        m_board.updateBoard(simulator.getBoard());
        return daysPassed;
    }

    UserControls m_userControls;
    GuiBoard m_board;
    Simulator simulator = null;
}
