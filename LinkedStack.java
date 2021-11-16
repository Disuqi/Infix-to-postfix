import java.util.EmptyStackException;

public class LinkedStack<G> implements Stack<G>{

    protected LinkNode<G> top;

    //creates an empty stack
    public LinkedStack(){
        top = null;
    }

    //@return true if list is empty
    @Override
    public boolean isEmpty() {
        return top == null;
    }
    /**@return top element of stack 
     * @throws EmptyStackException if stack is empty
    */
    @Override
    public G peek() {
        if(isEmpty()) throw new EmptyStackException();
        return top.object;
    }

    //adds new element to the top of the stack
    @Override
    public void push(G object) {
        top = new LinkNode<G>(object, top);
    }

    /**@removes top element of stack and returns it
    * @throws EmptyStackException if stack is empty
    */
    @Override
    public G pop() {
        if (isEmpty()) throw new EmptyStackException();
        G topObject = top.object;
        top = top.next;
        return topObject;
    }

    //Empties the stack
    @Override
    public void clear(){
        this.top = null;
    }

    /**@return String converts the stack into a string*/
    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        LinkNode<G> node = this.top;
        while(node != null){
            result.append(this.top.object + ", ");
            node = this.top.next;
        }
        return result.toString();
    }
    
}
