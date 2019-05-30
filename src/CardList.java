import javax.xml.bind.annotation.*;
import java.util.*;
import java.io.*;

public class CardList implements Serializable{

    @XmlElement
    private ArrayList<QuizCard> listOfCards;

    public CardList(){
        listOfCards = new ArrayList<>();
    }

    public CardList(ArrayList<QuizCard>listOfCards){
        this.listOfCards = listOfCards;
    }

    public ArrayList<QuizCard> getCardList(){
        return listOfCards;
    }
}