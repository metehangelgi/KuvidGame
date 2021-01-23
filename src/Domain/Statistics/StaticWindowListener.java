package Domain.Statistics;

import java.util.HashMap;

public interface StaticWindowListener {
    void onTimeChange(String time);
    void onHealthChange(String health);
    void onScoreChange(String score);
    void onAmmunitionChange(HashMap<String,HashMap<String,Integer>> map);
    void onShieldChange(HashMap<String,Integer> remainingShields);
}
