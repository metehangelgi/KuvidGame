package Domain.Objects;

import Domain.Utils.FinalValues;
import Domain.Utils.Position;

public class Blocker extends GameObject {

    public Blocker(String subType, Position position) {
        super(FinalValues.BLOCKER, subType, position,90,true);
        setWidth(getL()/2);
        setHeight(getL());
    }

}
