package Domain.Objects;

import Domain.Utils.FinalValues;
import Domain.Utils.Position;

public class Sigma_Blocker extends Blocker{


    public Sigma_Blocker(Position position) {
        super(FinalValues.SIGMA, position);
    }
}
