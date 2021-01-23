package Domain.Objects;

import Domain.Utils.FinalValues;
import Domain.Utils.Position;

public class Clock extends GameObject{

    public Clock(String subType, Position position,int angle){
        super(FinalValues.CLOCK, subType, position,angle,true);
        setHeight((int) (getL()/2));
        setWidth((int) (getL()/2));
    }
}
