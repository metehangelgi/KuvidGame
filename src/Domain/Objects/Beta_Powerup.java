package Domain.Objects;

import Domain.Utils.FinalValues;
import Domain.Utils.Position;

public class Beta_Powerup extends Powerup {


    public Beta_Powerup(Position position,boolean isFallable) {
        super(FinalValues.BETA, position,isFallable);
    }

}
