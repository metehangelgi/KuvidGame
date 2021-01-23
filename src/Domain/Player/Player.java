package Domain.Player;

import Domain.UserFunctionalities.GameStatueControl;
import Domain.Statistics.GameConfiguration;

public class Player {
    private static Player player;
    GameConfiguration gameConfiguration =GameConfiguration.getInstance();
    private Health health=new Health(gameConfiguration.getData().getHealth());
    private Score score=new Score(gameConfiguration.getData().getScore());

    private Player(){}

    public static Player getInstance(){
        if(player==null){
            player=new Player();
        }
        return player;
    }

    public double getHealth() {
        return health.getHealthLevel();
    }

    public void setHealth(double healthLevel) {
        this.health.setHealthLevel(healthLevel);
        gameConfiguration.setHealth(healthLevel);
    }

    public double getScore() {
        return score.getTotalScore();
    }

    public void setScore(double score) {
        this.score.setTotalScore(score);
        gameConfiguration.setScore(score);
    }

    public void hit(int distance){
        health.updateHealthLevel(distance);
        gameConfiguration.setHealth(health.getHealthLevel());
        if(health.getHealthLevel()<=0){
            GameStatueControl.getInstance().setGameEnded(true);
        }
    }

    public void increaseScore(double efficiency, double remaining_time_on_frame){
        score.updateTotalScore(efficiency,remaining_time_on_frame);
        gameConfiguration.setScore(score.getTotalScore());
    }
}
