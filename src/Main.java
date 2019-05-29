import javafx.stage.Stage;
import javafx.application.Application;

public class Main extends Application{

    private QuizCardBuilder builder;
    private Stage window;

    public static void main(String [] args){
        launch(args);
    }

    @Override
    public void start(Stage window){
        builder = new QuizCardBuilder(window);

        window.setTitle("Quiz Card Builder");
        window.setScene(builder);
        window.show();
    }
}