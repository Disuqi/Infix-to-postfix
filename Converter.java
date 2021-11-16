import java.security.InvalidParameterException;

public class Converter {
    private LinkedStack<Character> operators;
    private StringBuilder exp;

    public Converter(){
        operators = new LinkedStack<>();
        exp = null;
    }

    /**Converts from infix to postfix
     * @return postfix of the argument given
     * @param expression is the expression that needs to be converted
     * @throws InvalidParameterException if the given parameter does not match a mathematical expression
     */
    public String convert(String expression){
        expression = expression.replace(" ", "");
        //if the expression is invalid then it throws an exception
        if(!expression.matches("^([()]*[A-Za-z][()]*[+-/*][()]*[A-Za-z]?[()]*[+-/*]?)*")) throw new InvalidParameterException();
        //removes all spaces as they cause errors 
        exp = new StringBuilder(expression);
        //adds parenthesis 
        parenthesis();
        StringBuilder result = new StringBuilder("");
        //this for loop is used to go through every character in the expression
        for(int i=0; i < exp.length(); i++ ){
            //if the character is an operator it adds it to the stack
            switch(exp.charAt(i)){
                case '*':
                case '/':
                case '+':
                case '-':
                    operators.push(exp.charAt(i));
                    break;
                //if the character is a closed bracket it adds an operator from the stack to the result
                case ')':
                    if(!operators.isEmpty()) result.append(operators.pop());
                    break;
                //if it's an open bracket it doesnt do anything
                case '(':
                    break;
                //the default is to add it to result because if it is not any of the above then it must be a letter
                default:
                    result.append(exp.charAt(i));
                    break;
            }
        }
        //if the stack is empty then the remainnig operators need to be added to the end of the result
        if(!operators.isEmpty()){
            while(!operators.isEmpty()){
                result.append(operators.pop());
            }
        }
        return result.toString();
    }
    //it first adds the parenthesis to multiplicants and then addittions and subtractions, as this is the correct order
    private void parenthesis(){
        //the for loop goes through every character in the expression
        for(int i=0; i<exp.length(); i++){
            if(checkChar(i, '*') || checkChar(i, '/')){
                //if it finds * or / then it adds brackets and i increases by 2 because a bracket behind i was inserted
                if(insertBrackets(i)){
                    i+=1;
                }
            }
        }
        //Same as before only difference is that it is now adding brackets for + and - 
        for(int i=0; i<exp.length(); i++){
            if(checkChar(i, '+') || checkChar(i, '-')){
                if(insertBrackets(i)){
                    i+=1;
                }
            }
        }
    } 

    //checks if the character at index is the same as the character given
    /**@return true if it is the same or false if it isn't
     **@param index is the position of the character
     **@param check is the character it needs to be checked against
     */
    private boolean checkChar(int index,  char check){
        boolean result = false;
        if(!(index < 0) && index<exp.length()){
            if(exp.charAt(index) == check){
                result = true;
            }   
        }
        return  result;
    }

    //Checks if there are brackets around the character at position index
    /**@return char which is a code to make another methods understand where the brackets are
     **@param index is the postition of the character that needs to be checked
     */
    private char checkBrackets(int index){
        if(checkChar(index+2, ')') || checkChar(index-2, '(')){
            return ' ';//Brackets already there
        }
        else if((checkChar(index-1, ')') && checkChar(index+1, '('))){
            return 'B';//Special case X which is used to say that it is in the middle of two brackets
        }
        else if(checkChar(index-1, ')')){
            return 'C';//there brackets only on it's left side
        }else if(checkChar(index+1, '(')){
            return 'D';
        }
        else{
            return 'A'; //no brackets around it
        }
    }

    //insert brackets according to the position of the other brackets given from the previous method
    /**@return true if brackets were inserted and false if there were no brackets added
     * @param index is the position of the operator that needs brackets around it
     */
    private boolean insertBrackets(int index){
        switch(checkBrackets(index)){
            //add brackets normally right before and after the letter
            case 'A':
                exp.insert(index-1, '(');
                exp.insert(index+3, ')');
                return true;
            //add brackets around the two brackets before and after the operator
            case 'B':
                addLeftBracket(index);
                addRightBracket(index+1);
                return true;
            //there is a pair of brackets left next to the operator so it takes that into consideration and add brackets in the correct position
            case 'C':
                addLeftBracket(index);
                exp.insert(index+3, ')');
                return true;
            //there is a pair of brackets left next to the operator so it takes that into consideration and add brackets in the correct position
            case 'D':
                addRightBracket(index);
                exp.insert(index-1, '(');
            //no brackets added
            default:
                return false;
        }
    }

    //takes into consideration other brackets and then adds the left bracket
    /**@param index position of the operator */
    private void addLeftBracket(int index){
        int counter = 0;
        for(int i= index; i >= 0; i--){
            if(checkChar(i, ')')){
                counter++;
            }
            else if(checkChar(i, '(')){
                counter--;
                if(counter == 0){
                    exp.insert(i, '(');
                    index++;
                    break;
                }
            }
        }
    }

    //takes into consideration other brackets and then adds the right bracket
    /**@param index position of the operator */
    private void addRightBracket(int index){
        int counter = 0;
        for(int i= index; i < exp.length(); i++){
            if(checkChar(i, '(')){
                counter++;
            }
            else if(checkChar(i, ')')){
                counter--;
                if(counter == 0){
                    exp.insert(i+1, ')');
                    break;
                }
            }
        }
    }
    
    //getters&setters
    public LinkedStack<Character> getOperators(){
        return operators;
    }
    public void setOperators(LinkedStack<Character> operators){
        this.operators = operators;
    }
    public StringBuilder getExp(){
        return exp;
    }
    public void setExp(StringBuilder exp){
        this.exp = exp;
    }
}
