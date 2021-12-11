package AdversarialSearch;

public class Node {
    int gems;
    int position;

    public Node() {
        gems=4;
        position=-1;
    }

    public Node(int position) {
        this.position = position;
        gems=4;
    }
}
