import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Game {

    private Rules rules;

    public Game(){
        rules = new Rules();
    }

    public List<String> play(int initialNumber, int endNumber){
        return IntStream
                .rangeClosed(initialNumber, endNumber)
                .mapToObj(i -> getValue(i))
                .collect(Collectors.toList());
    }


    private String getValue(int number){
        return  rules.isFizzBuzz(number) ? "FizzBuzz" :
                rules.isFizz(number) ? "Fizz" :
                rules.isBuzz(number) ? "Buzz" :
                String.valueOf(number);
    }
}
