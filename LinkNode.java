public class LinkNode<G> {
    public G object;
    public LinkNode<G> next;

    public LinkNode(G object, LinkNode<G> next){
        this.object = object;
        this.next = next;
    }
}
