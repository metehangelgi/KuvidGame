package Domain.Objects;

import Domain.Utils.FinalValues;
import Domain.Utils.Position;

public class Alpha_Blocker extends Blocker{

    public Alpha_Blocker(Position position) {
        super(FinalValues.ALPHA, position);
    }
}
