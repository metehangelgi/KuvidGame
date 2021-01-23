package Domain.Player;

import Domain.Statistics.GameConfiguration;

public class Health{
    
    //We start from 100 health.

    private double healthLevel;


    public Health(double healthLevel){
        this.healthLevel=healthLevel;
    }

    //formula for calculating health. Each time a blocker explodes we subtract
    //(game width / distance between blocker and shooter) from the current total health.

    public double updateHealthLevel(double distance){
        int game_width = GameConfiguration.getInstance().getData().getGameScreenWidth();
        healthLevel = healthLevel - game_width/distance;
        return healthLevel;
    }

    public void setHealthLevel(double healthLevel) {
        this.healthLevel = healthLevel;
    }

    public double getHealthLevel(){
        return this.healthLevel;
    }
}
