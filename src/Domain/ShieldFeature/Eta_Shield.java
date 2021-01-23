package Domain.ShieldFeature;

import Domain.Objects.Atom;
import Domain.Utils.Position;

public class Eta_Shield extends Shield{

    Atom contextObj;
    public final double boost = 0.05;
    private static final int speed_reduction_percentage = 5;
    public Eta_Shield(Atom contextObj) {
        super(contextObj);
        this.contextObj = contextObj;
        this.setVelocity(getVelocity());
        this.setEfficiency(getEfficiency());
    }


    @Override
    public double getEfficiency() {
        if(contextObj.getNum_of_neutrons() != contextObj.getNum_of_protons()){
            return 1- contextObj.getEfficiency()*
                    Math.abs(contextObj.getNum_of_neutrons() - contextObj.getNum_of_protons())/
                    contextObj.getNum_of_protons();
        }
        return (1- contextObj.getEfficiency())*boost;
    }

    @Override
    public Position getVelocity() {
        return new Position(contextObj.getVelocity().getX()*(100-speed_reduction_percentage)/100,
                contextObj.getVelocity().getY()*(100-speed_reduction_percentage)/100);
    }
}
