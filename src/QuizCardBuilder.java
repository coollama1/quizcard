import javafx.stage.*;
import javafx.stage.FileChooser.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import java.util.*;
import javax.xml.bind.*;
import java.nio.*;
import java.nio.file.*;
import java.io.*;
import javax.xml.bind.annotation.*;

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
    private MenuItem saveItem;
    private MenuItem openItem;

    private FileChooser fileOpener;

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
        saveItem = new MenuItem("Save");
        openItem = new MenuItem("Open");
        fileOpener = new FileChooser();
        listOfCards = new ArrayList<>();
    }

    public void layoutComponents(){
        fileMenu.getItems().addAll(saveItem,openItem);
        menuBar.getMenus().addAll(fileMenu);
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
        nextCardButton.setOnAction(e->{
            String question = questionTextArea.getText();
            String answer = answerTextArea.getText();
            QuizCard currentCard = new QuizCard(question,answer);
            listOfCards.add(currentCard);
            questionTextArea.clear();
            answerTextArea.clear();
        });

        saveItem.setOnAction(e->{
            fileOpener.setTitle("Save Quiz Card XML file");
            fileOpener.getExtensionFilters().add(new ExtensionFilter("XML files","*.xml"));
            try{
                File selectedDirectory = fileOpener.showSaveDialog(window);
                if(selectedDirectory!=null){
                    Path selectedPath = selectedDirectory.toPath();
                    BufferedWriter bw = Files.newBufferedWriter(selectedPath);
                    JAXB.marshal(new CardList(listOfCards),bw);
                    bw.close();
                }

            }catch(Exception expt){
                expt.printStackTrace();
            }
        });

        openItem.setOnAction(e->{
            fileOpener.setTitle("Open Quiz Card XML file");
            fileOpener.getExtensionFilters().add(new ExtensionFilter("XML files","*.xml"));
            try{
                File selectedFile = fileOpener.showOpenDialog(window);
                if(selectedFile!=null){
                    Path selectedPath = selectedFile.toPath();
                    BufferedReader br = Files.newBufferedReader(selectedPath);
                    listOfCards = (JAXB.unmarshal(br,CardList.class)).getCardList();
                    br.close();
                }
            }catch(Exception expt){
                expt.printStackTrace();
            }
            QuizCardPlayer newPlayer = new QuizCardPlayer(listOfCards);
            newPlayer.setTitle("Quiz Card Player");
            newPlayer.show();
        });

    }

}