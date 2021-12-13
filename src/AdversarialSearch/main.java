package AdversarialSearch;

public class main {
    public static void main(String[] args) {
        Player max=new Player();
        Player min=new Player();
        Game game=new Game(max,min);
        game.printGameBoard();
        int a=game.alphaBeta(game,Integer.MIN_VALUE,Integer.MAX_VALUE);
        System.out.println(a);
    }
}
