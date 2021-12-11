package AdversarialSearch;

public class main {
    public static void main(String[] args) {
        Player max=new Player();
        Player min=new Player();
        Game game=new Game(max,min);
        game.printGameBoard();
        AlphaBetaPruning alphaBetaPruning=new AlphaBetaPruning();
        int a=alphaBetaPruning.runGame(game,Integer.MIN_VALUE,Integer.MAX_VALUE,true);
        System.out.println(a);
    }
}
