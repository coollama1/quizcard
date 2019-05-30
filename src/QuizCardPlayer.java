import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.layout.*;
import java.util.*;

public class QuizCardPlayer extends Stage{

    private VBox mainPanel;
    private Scene mainScene;
    private Label questionLabel;
    private Label answerLabel;
    private TextArea questionTextArea;
    private TextArea answerTextArea;
    private Button showAnswerButton;
    private Button nextCardButton;

    private HBox questionLabelBox;
    private HBox answerLabelBox;
    private HBox showAnswerButtonBox;
    private HBox nextCardButtonBox;

    private ArrayList<QuizCard> listOfCards;
    private ListIterator<QuizCard> listIterator;

    public QuizCardPlayer(ArrayList<QuizCard> listOfCards){
        this.listOfCards = listOfCards;
        initilizeMembers();
        formatComponents();
        layoutComponents();
        setActionListeners();
        updateFields();
        setScene(mainScene);
    }

    private void initilizeMembers(){
        mainPanel = new VBox(10);
        mainScene = new Scene(mainPanel,300,350);
        questionLabel = new Label("Question");
        answerLabel = new Label("Answer");
        questionTextArea = new TextArea();
        answerTextArea = new TextArea();
        showAnswerButton = new Button("Show Answer");
        nextCardButton = new Button("Next Card");
        questionLabelBox = new HBox();
        answerLabelBox = new HBox();
        showAnswerButtonBox = new HBox(); 
        nextCardButtonBox = new HBox();
        listIterator = listOfCards.listIterator();
    }

    private void formatComponents(){
        questionLabelBox.setAlignment(Pos.BASELINE_CENTER);
        answerLabelBox.setAlignment(Pos.BASELINE_CENTER);
        showAnswerButtonBox.setAlignment(Pos.BASELINE_CENTER);
        nextCardButtonBox.setAlignment(Pos.BASELINE_CENTER);
        questionTextArea.setWrapText(true);
        questionTextArea.setEditable(false);
        questionTextArea.setPrefRowCount(4);
        answerTextArea.setWrapText(true);
        answerTextArea.setEditable(false);
        answerTextArea.setPrefRowCount(4);
        mainPanel.setPadding(new Insets(10,10,10,10));

    }

    private void layoutComponents(){
        questionLabelBox.getChildren().add(questionLabel);
        answerLabelBox.getChildren().add(answerLabel);
        showAnswerButtonBox.getChildren().add(showAnswerButton);
        nextCardButtonBox.getChildren().add(nextCardButton);
        mainPanel.getChildren().addAll(questionLabelBox,questionTextArea,showAnswerButtonBox);

    }

    public void setActionListeners(){
        showAnswerButton.setOnAction(e->{
            mainPanel.getChildren().addAll(answerLabelBox,answerTextArea,nextCardButtonBox);
        });

        nextCardButton.setOnAction(e->{
            mainPanel.getChildren().removeAll(answerLabelBox,answerTextArea,nextCardButtonBox);
            updateFields();
        });
    }

    public void updateFields(){
        if(listIterator.hasNext()){
            QuizCard currentCard = listIterator.next();
            questionTextArea.setText(currentCard.getQuestion());
            answerTextArea.setText(currentCard.getAnswer());
        }

    }
}