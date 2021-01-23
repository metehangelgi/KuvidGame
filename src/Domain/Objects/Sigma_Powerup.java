package Domain.Objects;

import Domain.Utils.FinalValues;
import Domain.Utils.Position;

public class Sigma_Powerup extends Powerup{


    public Sigma_Powerup(Position position,boolean isFallable) {
        super(FinalValues.SIGMA, position,isFallable);
    }

}
