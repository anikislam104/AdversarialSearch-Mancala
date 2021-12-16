package AdversarialSearch;

import java.util.Scanner;

public class CompVsComp {
    public static void main(String[] args) {
        int maxWin=0,minWin=0;
        System.out.println("Input depth");
        Scanner scanner=new Scanner(System.in);
        int depth=scanner.nextInt();
        System.out.println("Which Heuristic:");
        int h=scanner.nextInt();
        for (int x=0;x<100;x++) {
            Player max=new Player();
            Player min=new Player();
            Game game=new Game(max,min);
            int currentPlayer;
            if(x%2==0){
                currentPlayer=1;
            }
            else {
                currentPlayer=2;
            }
            int loop=0;
            while (true) {
                //game.printGameBoard();
                if (game.isGameOver()) {
                    if (game.max.mancala.gems > game.min.mancala.gems) {
                        System.out.println("Max won");
                        maxWin++;
                    } else {
                        System.out.println("Min won");
                        minWin++;
                    }
                    break;
                }
                //System.out.println("game:" + loop + "  " + currentPlayer);
                int a = game.alphaBeta(game, Integer.MIN_VALUE, Integer.MAX_VALUE, currentPlayer, depth,h);
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
            Game.maxAdditionalMove=0;
            Game.minAdditionalMove=0;
        }
        System.out.println("maxWin= "+maxWin);
        System.out.println("minWIn= "+minWin);
    }

}
