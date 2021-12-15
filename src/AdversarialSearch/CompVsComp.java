package AdversarialSearch;

public class CompVsComp {
    public static void main(String[] args) {
        int maxWin=0,minWin=0;
        for (int x=0;x<100;x++) {
            Player max=new Player();
            Player min=new Player();
            Game game=new Game(max,min);
            int currentPlayer=1;
            int loop=0;
            while (true) {
                //game.printGameBoard();
                if (game.isGameOver()) {
                    if (game.max.mancala.gems > game.min.mancala.gems) {
                        maxWin++;
                    } else {
                        minWin++;
                    }
                    break;
                }
                System.out.println("game:" + loop + "  " + currentPlayer);
                int a = game.alphaBeta(game, Integer.MIN_VALUE, Integer.MAX_VALUE, currentPlayer, 4);
                for (int i = 0; i < Game.games.size(); i++) {
                    if (Game.games.get(i).parent == game && Game.games.get(i).heuristicValue == a) {
                        Game.games.get(i).printGameBoard();
                        game = Game.games.get(i);
                        currentPlayer = game.currentPlayer;
                        Game.games.clear();
                        break;
                    }
                }
                loop++;
            }
        }
        System.out.println("maxWin= "+maxWin);
        System.out.println("minWIn= "+minWin);
    }

}
