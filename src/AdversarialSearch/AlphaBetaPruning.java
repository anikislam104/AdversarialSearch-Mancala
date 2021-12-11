package AdversarialSearch;

public class AlphaBetaPruning {
    Game game;

    public AlphaBetaPruning() {

    }

    int runGame(Game mancala,int alpha,int beta,boolean maxPlayer){
        if(mancala.isGameOver()==true && mancala.max.mancala.gems>mancala.min.mancala.gems ){
            return mancala.heuristicONE(1);
        }
        if(maxPlayer==true){
            int maxEval=Integer.MIN_VALUE;
            game=mancala;
            for(int i=0;i<6;i++){
                    if(game.checkZeroAtPosition(1,i)==false){
                    Game child=game.getChildAtPosition(game,i,1);
                    int eval=this.runGame(child,alpha,beta,false);
                    maxEval=Math.max(maxEval,eval);
                    alpha=Math.max(alpha,eval);
                    if (beta<=alpha){
                        break;
                    }
                }
            }
            return maxEval;

        }

        else {
            int minEval=Integer.MIN_VALUE;
            game=mancala;
            for (int i=0;i<6;i++){
                if (game.checkZeroAtPosition(2,i)) {
                    Game child = game.getChildAtPosition(game, i, 2);
                    int eval = this.runGame(child, alpha, beta, true);
                    minEval = Math.min(minEval, eval);
                    beta = Math.min(beta, eval);
                    if (beta <= alpha) {
                        break;
                    }
                }
            }
            return minEval;
        }
        //return 0;
    }
}
