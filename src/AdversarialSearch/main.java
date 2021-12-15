package AdversarialSearch;

public class main {
    public static void main(String[] args) {
        Player max=new Player();
        Player min=new Player();
        Game game=new Game(max,min);
        int a = game.alphaBeta(game, Integer.MIN_VALUE, Integer.MAX_VALUE,1,2);
     System.out.println(Game.games.size());
        System.out.println(a);
//        for(int j=0;j<Game.games.size();j++){
//            Game.games.get(j).printGameBoard();
//            System.out.println(Game.games.get(j).heuristicValue);
//        }
    }
}
