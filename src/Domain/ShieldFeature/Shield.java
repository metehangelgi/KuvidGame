package Domain.ShieldFeature;

import Domain.Objects.Atom;

public abstract class Shield extends Atom {
    private Atom contextObj;

    public Shield(Atom contextObj) {
        super(contextObj.getSubType(), contextObj.getCurrentPosition(), contextObj.getAngle());
        this.setNum_of_neutrons(contextObj.getNum_of_neutrons());
        this.setNum_of_protons(contextObj.getNum_of_protons());
        this.contextObj = contextObj;
        this.setShielded(true);
    }


}
