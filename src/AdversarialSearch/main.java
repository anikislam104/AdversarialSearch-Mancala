package AdversarialSearch;

public class main {
    public static void main(String[] args) {
        Player max=new Player();
        Player min=new Player();
        Game game=new Game(max,min);
        int currentPlayer=1;
        int loop=0;
        while (true) {
            //game.printGameBoard();
            if(game.isGameOver()){
//
                break;
            }
            System.out.println("game:"+loop+"  "+currentPlayer);
            int a = game.alphaBeta(game, Integer.MIN_VALUE, Integer.MAX_VALUE,currentPlayer,2);
            for (int i = 0; i < Game.games.size(); i++) {
                if (Game.games.get(i).parent == game && Game.games.get(i).heuristicValue == a) {
                    Game.games.get(i).printGameBoard();
                    game = Game.games.get(i);
                    currentPlayer= game.currentPlayer;
                    Game.games.clear();
                    break;
                }
            }
            loop++;
        }
//        game.printGameBoard();
//        a = game.alphaBeta(game, Integer.MIN_VALUE, Integer.MAX_VALUE,1,2);
//        for (int i=0;i<Game.games.size();i++){
//            if(Game.games.get(i).parent==game && Game.games.get(i).heuristicValue==a){
//                Game.games.get(i).printGameBoard();
//                break;
//            }
//        }
    }
}
