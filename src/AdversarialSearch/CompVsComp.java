package AdversarialSearch;

import java.util.Scanner;

public class CompVsComp {
    public static void main(String[] args) {
        int maxWin = 0, minWin = 0, draw = 0;
        System.out.println("Input depth");
//        Scanner scanner = new Scanner(System.in);
//        int depth = scanner.nextInt();
        System.out.println("1.Difference between stones in storage");
        System.out.println("2.Weighted difference in bins + weighted difference in storage");
        System.out.println("3.Weighted difference in bins + weighted difference in storage + weight*additional moves earned so far");
        System.out.println("4.additional moves possible in this turn");
        System.out.println("5.Difference between number of stolen stones");
        System.out.println("6.Difference between stones close to storage");
        //System.out.println("Heuristic:");
        int totalMax = 0, totalMin = 0;
//        int h = scanner.nextInt();
        for (int hmax = 1; hmax < 7; hmax++) {
            for (int hmin = 1; hmin < 7; hmin++) {

                System.out.println("Heuristic "+hmax+" vs "+hmin);
                System.out.println();
                for (int depth = 2; depth < 7; depth++) {

                    System.out.println("Depth= "+depth);
                    //System.out.println();
                    for (int x = 0; x < 100; x++) {
                        Player max = new Player();
                        Player min = new Player();
                        Game game = new Game(max, min);
                        int currentPlayer;
                        if (x % 2 == 0) {
                            currentPlayer = 1;
                        } else {
                            currentPlayer = 2;
                        }
                        int loop = 0;
                        while (true) {
                            //game.printGameBoard();
                            if (game.isGameOver()) {
                                if (game.max.mancala.gems > game.min.mancala.gems) {
                                    //System.out.println("Max won");
                                    maxWin++;
                                } else if (game.max.mancala.gems == game.min.mancala.gems) {
                                    draw++;
                                } else {
                                    //System.out.println("Min won");
                                    minWin++;
                                }
                                break;
                            }
                            //System.out.println("game:" + loop + "  " + currentPlayer);
                            int a;
                            if (currentPlayer==1)
                                a= game.alphaBeta(game, Integer.MIN_VALUE, Integer.MAX_VALUE, currentPlayer, depth, hmax);
                            else
                                a= game.alphaBeta(game, Integer.MIN_VALUE, Integer.MAX_VALUE, currentPlayer, depth, hmin);
                            //System.out.println("heuristic value "+a);
                            Game.maxAdditionalMove += game.hasFreeMoves(game);
                            for (int i = 0; i < Game.games.size(); i++) {
                                if (Game.games.get(i).parent == game && Game.games.get(i).heuristicValue == a) {
                                    //Game.games.get(i).printGameBoard();
                                    game = Game.games.get(i);
                                    currentPlayer = game.currentPlayer;
                                    Game.games.clear();
                                    break;
                                }
                            }
                            loop++;
                        }

                    }
                System.out.println("maxWin= " + maxWin);
                System.out.println("minWIn= " + minWin);
                System.out.println("Draw= " + draw);
                    System.out.println();
                    totalMax += maxWin;
                    totalMin += minWin;
                    maxWin = 0;
                    minWin = 0;
                    draw = 0;
                }
                //System.out.println(totalMax + "  " + totalMin);
                totalMax = 0;
                totalMin = 0;
            }

        }
    }

}
