package Domain.ShieldFeature;

import Domain.Objects.Atom;
import Domain.Utils.Position;

public class Lota_Shield extends Shield{

    Atom contextObj;
    private static final int speed_reduction_percentage = 7;
    public final double boost = 0.1;
    public Lota_Shield(Atom contextObj){
        super(contextObj);
        this.contextObj=contextObj;
        this.setVelocity(getVelocity());
        this.setEfficiency(getEfficiency());
    }

    @Override
    public double getEfficiency() {
        return (1-contextObj.getEfficiency())*boost;
    }
    public Position getVelocity() {
        return new Position(contextObj.getVelocity().getX()*(100-speed_reduction_percentage)/100,
                contextObj.getVelocity().getY()*(100-speed_reduction_percentage)/100);
    }
}
