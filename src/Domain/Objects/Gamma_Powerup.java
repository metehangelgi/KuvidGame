package Domain.Objects;

import Domain.Utils.FinalValues;
import Domain.Utils.Position;

public class Gamma_Powerup extends Powerup {


    public Gamma_Powerup(Position position,boolean isFallable) {
        super(FinalValues.GAMMA, position,isFallable);
    }


}
