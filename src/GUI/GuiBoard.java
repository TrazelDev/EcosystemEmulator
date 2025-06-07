package GUI;

import Simulator.Board;
import Simulator.Organism;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert;

import java.util.HashMap;
import java.util.Map;

public class GuiBoard extends GridPane {
    private static final int GRID_SIZE = 10;
    private static final int CELL_SIZE = 60;

    public GuiBoard() {
        super();
        this.setPadding(new Insets(10));
        this.setHgap(2);
        this.setVgap(2);
        this.setGridLinesVisible(true);

        initBoard();
    }

    void updateBoard(Board board) {
        resetBoard();
        board.iterateOrganisms(this::updateCell);
    }


    void resetBoard() {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                Node node = getNodeAt(row, col);
                if (node instanceof ImageView) {
                    ((ImageView)node).setImage(null);
                }
            }
        }
    }

    private void updateCell(Organism organism) {
        int row = organism.getLocation().y;
        int col = organism.getLocation().x;
        Node node = getNodeAt(row, col);

        if (node == null) return;
        if (!(node instanceof ImageView)) return;


        ((ImageView)node).setImage(s_animalToPic.get(organism.getName()));
    }


    private Node getNodeAt(int targetRow, int targetCol) {
        for (Node node : this.getChildren()) {
            Integer nodeCol = GridPane.getColumnIndex(node);
            if (nodeCol == null || nodeCol != targetCol) continue;

            Integer nodeRow = GridPane.getRowIndex(node);
            if (nodeRow == null || nodeRow != targetRow) continue;

            return node;
        }

        return null;
    }

    private void initBoard() {
        Image cellImage = s_animalToPic.get(' ');

        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                ImageView imageView = new ImageView();
                imageView.setFitWidth(CELL_SIZE);
                imageView.setFitHeight(CELL_SIZE);
                imageView.setPreserveRatio(false);
                imageView.setSmooth(true);
                imageView.setOnMouseClicked(event -> {
                    showPopupMessage(imageView);
                });

                this.add(imageView, col, row);
            }
        }
    }


    private void showPopupMessage(ImageView imageView) {
        // if (imageView.getImage() == null) {
        //     // Alert alert = new Alert(Alert.AlertType.INFORMATION);
        //     // alert.setTitle("Empty Cell");
        //     // alert.setHeaderText("Position: [" + row + ", " + col + "]");
        //     // alert.setContentText("This cell is empty.");
        //     // alert.showAndWait();
        //     return;
        // }

        // Create the information popup
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message");
        alert.setHeaderText("Message");

        alert.setContentText("Or");
        alert.showAndWait();
    }

    private static Map<Character, Image> s_animalToPic = new HashMap<Character, Image>() {{
        put(' ', new Image("/Resources/empty.jpg"));
        put('C', new Image("/Resources/carnivore.jpg"));
        put('H', new Image("/Resources/herbivore.jpg"));
        put('P', new Image("/Resources/plant.jpg"));
    }};
}
