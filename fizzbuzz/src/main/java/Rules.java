public class Rules {

    public boolean isFizz(int number){
        return number % 3 == 0;
    }

    public boolean isBuzz(int number){
        return number % 5 == 0;
    }

    public boolean isFizzBuzz(int number){
        return isFizz(number) && isBuzz(number);
    }
}
