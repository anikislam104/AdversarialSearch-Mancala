package AdversarialSearch;

import java.util.ArrayList;
import java.util.Hashtable;

public class Player {
    ArrayList<Node> nodes=new ArrayList<>();
    Mancala mancala=new Mancala();
    Player opponent;

    public Player() {
        for (int i=0;i<6;i++){
            Node node=new Node(i);
            nodes.add(node);
        }
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }
}
