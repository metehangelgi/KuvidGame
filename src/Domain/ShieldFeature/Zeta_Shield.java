package Domain.ShieldFeature;

import Domain.Objects.Atom;
import Domain.Utils.Position;

public class Zeta_Shield extends Shield{

    Atom contextObj;
    private final double boost = 0.2;
    private static final int speed_reduction_percentage = 11;
    public Zeta_Shield(Atom contextObj) {
        super(contextObj);
        this.contextObj = contextObj;
        this.setVelocity(getVelocity());
        this.setEfficiency(getEfficiency());
    }
    @Override
    public double getEfficiency() {
        if(contextObj.getNum_of_protons() == contextObj.getNum_of_neutrons()){
            return (1- contextObj.getEfficiency())*boost;
        }
        return contextObj.getEfficiency();
    }
    public Position getVelocity() {
        return new Position(contextObj.getVelocity().getX()*(100-speed_reduction_percentage)/100,
                contextObj.getVelocity().getY()*(100-speed_reduction_percentage)/100);
    }
}
