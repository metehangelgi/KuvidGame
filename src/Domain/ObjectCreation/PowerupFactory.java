package Domain.ObjectCreation;

import Domain.Objects.*;
import Domain.Utils.FinalValues;
import Domain.Utils.Position;

public class PowerupFactory {
    private static PowerupFactory powerupFactory;

    private PowerupFactory(){}

    public static PowerupFactory getInstance(){
        if(powerupFactory ==null){
            powerupFactory =new PowerupFactory();
        }
        return powerupFactory;
    }


    public GameObject createPowerup(String subtype, Position position,boolean isFallable) {

        switch (subtype) {
            case FinalValues.ALPHA:
                return new Alpha_Powerup(position,isFallable);

            case FinalValues.BETA:
                return new Beta_Powerup(position,isFallable);

            case FinalValues.GAMMA:
                return new Gamma_Powerup(position,isFallable);

            default:
                return new Sigma_Powerup(position,isFallable);

        }

    }
}
