package Domain.Objects;

import Domain.Utils.FinalValues;
import Domain.Utils.Position;

public class Hearth extends GameObject{

    public Hearth(String subType, Position position, int angle){
        super(FinalValues.HEARTH, subType, position,angle,true);
        setHeight((int) (getL()/2));
        setWidth((int) (getL()/2));
    }
}
