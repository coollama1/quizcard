import java.io.*;
import javax.xml.bind.annotation.*;

public class QuizCard implements Serializable{

    @XmlElement
    private String question;
    @XmlElement
    private String answer;

    public QuizCard(){
        this("","");
    }

    public QuizCard(String question, String answer){
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion(){
        return question;
    }

    public String getAnswer(){
        return answer;
    }
}