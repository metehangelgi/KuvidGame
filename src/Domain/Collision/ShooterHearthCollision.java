package Domain.Collision;

import Domain.Objects.GameObject;
import Domain.Player.Player;
import Domain.Statistics.GameConfiguration;
import Domain.Statistics.GameData;

public class ShooterHearthCollision implements CollisionStrategy{
    private final double healthIncrease=10;
    @Override
    public void doCollision(GameObject object1, GameObject object2) {
        Player player=Player.getInstance();

        double remainingHealth= player.getHealth();

        player.setHealth(Math.min(remainingHealth + healthIncrease, 100));
        object2.destroy();
    }
}
