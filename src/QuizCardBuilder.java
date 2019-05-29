import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import java.util.*;

public class QuizCardBuilder extends Scene{

    private Stage window;
    private BorderPane mainPane;
    private VBox centerPane;
    private Label questionLabel;
    private Label answerLabel;
    private TextArea questionTextArea;
    private TextArea answerTextArea;
    private Button nextCardButton;

    private HBox questionLabelBox;
    private HBox answerLabelBox;
    private HBox nextButtonBox;

    private MenuBar menuBar;
    private Menu fileMenu;
    private Menu modeMenu;

    private ArrayList<QuizCard> listOfCards;

    public QuizCardBuilder(Stage window){
        super(new BorderPane(),300,350);
        this.window = window;
        initializeMembers();
        formatComponents();
        setActionListeners();
        layoutComponents();

    }

    public void initializeMembers(){
        mainPane = (BorderPane)this.getRoot();
        centerPane = new VBox(10);
        questionLabel = new Label("Question");
        answerLabel = new Label("Answer");
        questionTextArea = new TextArea();
        answerTextArea = new TextArea();
        nextCardButton = new Button("Next Card");
        questionLabelBox = new HBox();
        answerLabelBox = new HBox();
        nextButtonBox = new HBox();
        menuBar = new MenuBar();
        fileMenu = new Menu("File");
        modeMenu = new Menu("Mode");
        listOfCards = new ArrayList<>();
    }

    public void layoutComponents(){
        menuBar.getMenus().addAll(fileMenu,modeMenu);
        questionLabelBox.getChildren().add(questionLabel);
        answerLabelBox.getChildren().add(answerLabel);
        nextButtonBox.getChildren().add(nextCardButton);
        centerPane.getChildren().addAll(questionLabelBox,questionTextArea,answerLabelBox,answerTextArea,nextButtonBox);
        mainPane.setTop(menuBar);
        mainPane.setCenter(centerPane);
    }

    public void formatComponents(){
        questionLabelBox.setAlignment(Pos.BASELINE_CENTER);
        answerLabelBox.setAlignment(Pos.BASELINE_CENTER);
        nextButtonBox.setAlignment(Pos.BASELINE_CENTER);
        answerTextArea.setWrapText(true);
        questionTextArea.setWrapText(true);
        centerPane.setPadding(new Insets(10,10,10,10));
    }

    public void setActionListeners(){

    }

}