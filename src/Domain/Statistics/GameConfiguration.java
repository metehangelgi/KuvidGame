package Domain.Statistics;

import java.text.DecimalFormat;
import java.util.HashMap;

public class GameConfiguration {
    private static GameConfiguration gameConfiguration;
    private GameData data;
    private StaticWindowListener staticWindowListener;

    private GameConfiguration(){}

    public static GameConfiguration getInstance(){
        if(gameConfiguration==null){
            gameConfiguration=new GameConfiguration();
        }
        return gameConfiguration;
    }

    public void setStaticWindowListener(StaticWindowListener staticWindowListener){
        this.staticWindowListener=staticWindowListener;
    }

    public GameData getData() {
        return this.data;
    }

    public void setData(GameData data) {
        this.data = data;
    }

    public void setTime(double time){
        data.setRemainingTime(time);
        int min=(int)time/60;
        int sec=(int)((time-min*60)%60);
        StringBuilder clock=new StringBuilder();
        clock.append(min);
        clock.append(":");
        clock.append(sec);
        staticWindowListener.onTimeChange(clock.toString());
    }

    public void setScore(double score){
        DecimalFormat df = new DecimalFormat("#.00");
        data.setScore(score);
        staticWindowListener.onScoreChange(df.format(score));
    }

    public void setHealth(double health){
        DecimalFormat df = new DecimalFormat("#.00");
        data.setHealth(health);
        staticWindowListener.onHealthChange(df.format(health));
    }

    public void setAmmunition(HashMap<String,HashMap<String,Integer>> ammo){
        data.setAmmunition(ammo);
        staticWindowListener.onAmmunitionChange(ammo);
    }

    public void setRemainingShield(HashMap<String,Integer> remainingShield){
        data.setRemainingShield(remainingShield);
        staticWindowListener.onShieldChange(remainingShield);
    }
}
