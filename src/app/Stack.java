package src.app;
public interface Stack<G> {
    public boolean isEmpty();
    public G peek();
    public void push(G object);
    public G pop();
    public void clear();
    public String toString();
}
