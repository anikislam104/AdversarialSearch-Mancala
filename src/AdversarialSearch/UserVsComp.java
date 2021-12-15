package AdversarialSearch;

import java.util.Scanner;

public class UserVsComp {
    public static void main(String[] args) {
        int maxWin=0,minWin=0;
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
                if(currentPlayer==1) {
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
                }
                else{
                    Scanner scanner=new Scanner(System.in);
                    int input=scanner.nextInt();
                    if(game.min.nodes.get(input).gems==0){
                        System.out.println("Invalid move");
                    }
                    else {
                        game.moveGems(2, input, game);
                        game.printGameBoard();
                        currentPlayer = game.currentPlayer;
                    }
                }
                loop++;
            }
        game.printGameBoard();
        if(maxWin==0){
            System.out.println("You win");
        }
        else{
            System.out.println("You lose");
        }
    }
}
