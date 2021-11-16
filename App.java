import java.security.InvalidParameterException;
import java.util.EmptyStackException;
import java.util.Scanner;

public class App{    
    
    public static void main(String[] args){
        //new scanner to read the message entered by user
        Scanner scanner = new Scanner(System.in);
        //initializing the boolean that decides if the program keep running or not
        boolean on = true;
        //creating a converter
        Converter converter = new Converter();
        //some instructions
        System.out.println("To leave just type: q or quit or exit");
        //keep on until it boolean is set to false
        while(on){
            System.out.println("Enter an expression:");
            //scans for the message inputted by user
            String exp = scanner.nextLine();
            //stops the program if q or quit or exit is typed by the user
            if(exp.equals("q") || exp.equals("quit") || exp.equals("exit")){
                System.out.println("Bye!");
                on = false;
                break;
            //if the expression is smaller than 20 characters then it is converted
            //if there is any kind of probelm then the message something went wrong try again is sent
            }else if(exp.length() <= 20){
                try{
                    System.out.println("The expression \"" + exp + "\" in post fix format is " + converter.convert(exp));
                }catch(EmptyStackException e){
                    System.err.println("Something went wrong try again");
                }catch(InvalidParameterException e){
                    System.err.println("You did not enter a valid expression");
                }
            }else{
                System.out.println("Something went wrong try again");
            }
        }
        scanner.close();
    }
}