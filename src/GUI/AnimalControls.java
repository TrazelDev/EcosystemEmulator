package GUI;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class AnimalControls extends HBox {
    TextField m_numCarnivores;
    TextField m_numHerbivores;
    TextField m_numPlants;

    public AnimalControls() {
        super(10);
        this.setAlignment(Pos.CENTER);

        m_numCarnivores = new TextField("0");
        m_numHerbivores = new TextField("0");
        m_numPlants = new TextField("0");
        Label label = new Label("Number of");
        makeFieldIntegerOnly(m_numCarnivores);
        makeFieldIntegerOnly(m_numHerbivores);
        makeFieldIntegerOnly(m_numPlants);

        this.getChildren().addAll(
                new Label("Carnivores"), m_numCarnivores,
                new Label("Herbivores"), m_numHerbivores,
                new Label("Plants"), m_numPlants
        );
    }

    public void makeFieldIntegerOnly(TextField field) {
        field.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                field.setText(oldValue);
            }
        });
    }
     
    public int getNumCarnivores() {
        if (m_numCarnivores.getText().isEmpty()) {
            throw new IlegalOrganismInput();
        }
        return Integer.parseInt(m_numCarnivores.getText());
    }

    public int getNumHerbivores() {
        if (m_numHerbivores.getText().isEmpty()) {
            throw new IlegalOrganismInput();
        }
        return Integer.parseInt(m_numHerbivores.getText());
    }

    public int getNumPlants() {
        if (m_numPlants.getText().isEmpty()) {
            throw new IlegalOrganismInput();
        }
        return Integer.parseInt(m_numPlants.getText());
    }

}