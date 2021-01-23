package Domain.Objects;

import Domain.Utils.FinalValues;
import Domain.Utils.Position;

public class Molecule extends GameObject{

    public Molecule(String subType, Position position,int angle) {
        super(FinalValues.MOLECULE, subType, position,angle,true);
        setVelocityY(getVelocityY()*getL()/50);
        setVelocityX(getVelocityX()*getL()/50);
        setHeight(getL()/2);
        setWidth(getL()/2);
    }

}
