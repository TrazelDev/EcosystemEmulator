package GUI;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GuiBoard extends GridPane {
    public GuiBoard() {
        super();
        this.setPadding(new Insets(10));
        this.setHgap(2);
        this.setVgap(2);
        this.setGridLinesVisible(true);

        Image cellImage = null;


        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                ImageView imageView = new ImageView();
                imageView.setFitWidth(60);
                imageView.setFitHeight(60);
                imageView.setPreserveRatio(false); // Set to false if you want them to perfectly fill the square
                // true maintains original aspect ratio within the cell
                imageView.setSmooth(true); // Smoothens the image if scaled

                // Add the ImageView to the grid at the specified column and row
                this.add(imageView, col, row);
            }
        }

    }
}
