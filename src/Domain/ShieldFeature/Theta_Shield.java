package Domain.ShieldFeature;

import Domain.Objects.Atom;
import Domain.Utils.Position;

public class Theta_Shield extends Shield{

    Atom contextObj;
    private final double boost;
    private static final int speed_reduction_percentage = 9;
    public Theta_Shield(Atom contextObj) {
        super(contextObj);
        this.contextObj = contextObj;
        boost =0.5+ Math.random()*0.1;
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
