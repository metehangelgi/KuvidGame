package Domain.Objects;

import Domain.Utils.FinalValues;
import Domain.Utils.Position;

public class Alpha_Powerup extends Powerup {


    public Alpha_Powerup(Position position,boolean isFallable) {
        super(FinalValues.ALPHA, position,isFallable);
    }


}
