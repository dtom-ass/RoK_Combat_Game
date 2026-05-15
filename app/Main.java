import controller.PlayerPanel;
import controller.SelectionController;
import javafx.application.Application;
import javafx.stage.Stage;
import view.scenes.WelcomeScene;

public class Main extends Application {

    PlayerPanel panel = new PlayerPanel();
    SelectionController controller = new SelectionController(panel);

    @Override
    public void start(Stage stage) {

        stage.setTitle("RoK Combat Game");

        stage.setScene(
            WelcomeScene.create(stage, panel, controller)
        );

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}