package Domain.Collision;

import Domain.Objects.GameObject;
import Domain.Statistics.GameConfiguration;
import Domain.Utils.FinalValues;

import java.util.HashMap;

public class ShooterPowerupCollision implements CollisionStrategy{
    @Override
    public void doCollision(GameObject object1, GameObject object2) {
        GameConfiguration configuration=GameConfiguration.getInstance();
        HashMap<String,HashMap<String,Integer>> ammo=configuration.getData().getAmmunition();
        HashMap<String,Integer> map=ammo.get(FinalValues.POWERUP);
        map.replace(object2.getSubType(),map.get(object2.getSubType())+1);

        configuration.setAmmunition(ammo);
        object2.destroy();
    }
}
