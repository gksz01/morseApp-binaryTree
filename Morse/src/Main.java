import MorseApp.MorseApp;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        MorseApp morseApp = new MorseApp();
        morseApp.start(primaryStage);
    }
}