package Domain.Objects;

import Domain.Utils.FinalValues;
import Domain.Utils.Position;

public class Beta_Blocker extends Blocker {

    public Beta_Blocker(Position position) {
        super(FinalValues.BETA, position);
    }
}
