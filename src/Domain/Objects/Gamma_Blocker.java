package Domain.Objects;

import Domain.Utils.FinalValues;
import Domain.Utils.Position;

public class Gamma_Blocker extends Blocker {


    public Gamma_Blocker(Position position) {
        super(FinalValues.GAMMA, position);
    }
}
