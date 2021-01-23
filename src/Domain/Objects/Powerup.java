package Domain.Objects;

import Domain.Utils.FinalValues;
import Domain.Utils.Position;

public class Powerup extends GameObject{


    public Powerup(String subType, Position position,boolean isFallable) {
        super(FinalValues.POWERUP, subType, position,90,isFallable);
        setWidth(getL()/2);
        setHeight(getL());

        if(!isFallable){
            setVelocityY(-getVelocityY());
        }
    }

}
