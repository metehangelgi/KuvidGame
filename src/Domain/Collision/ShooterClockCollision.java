package Domain.Collision;

import Domain.Objects.GameObject;
import Domain.Statistics.GameConfiguration;
import Domain.Statistics.GameData;
import Domain.Utils.FinalValues;

import java.util.HashMap;

public class ShooterClockCollision implements CollisionStrategy{
    private final double timeIncrease=10;
    @Override
    public void doCollision(GameObject object1, GameObject object2) {
        GameConfiguration configuration=GameConfiguration.getInstance();
        GameData gameData=configuration.getData();

        double remainingTime= gameData.getRemainingTime();

        configuration.setTime(Math.min(remainingTime + timeIncrease, 3600.0));
        object2.destroy();
    }
}
