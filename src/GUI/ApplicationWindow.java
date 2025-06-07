package GUI;

import javafx.scene.layout.BorderPane;

public class ApplicationWindow extends BorderPane {
    public ApplicationWindow() {
        super();

        m_userControls = new UserControls(() -> {});
        this.setBottom(m_userControls);
    }

    UserControls m_userControls;
}
