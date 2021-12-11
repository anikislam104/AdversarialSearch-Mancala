package AdversarialSearch;

public class AlphaBetaPruning {
    Game game;

    public AlphaBetaPruning() {

    }

    int runGame(Game mancala,int alpha,int beta,boolean maxPlayer){
        if(game.isGameOver()==true && game.max.mancala.gems>game.min.mancala.gems ){
            return game.heuristicONE(1);
        }
        else if(game.currentPlayer==1){
            int maxEval=Integer.MIN_VALUE;
            game=mancala;

        }
        return 0;
    }
}
