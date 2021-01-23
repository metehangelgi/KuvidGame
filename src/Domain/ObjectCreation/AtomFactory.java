package Domain.ObjectCreation;

import Domain.Objects.*;
import Domain.Utils.FinalValues;
import Domain.Utils.Position;

public class AtomFactory {
    private static AtomFactory atomFactory;

    private AtomFactory(){}

    public static AtomFactory getInstance(){
        if(atomFactory ==null) atomFactory =new AtomFactory();

        return atomFactory;
    }


    public GameObject createAtom(String subtype,Position position,int angle) {

        switch (subtype) {
            case FinalValues.ALPHA:
                return new Alpha_Atom(position,angle);

            case FinalValues.BETA:
                return new Beta_Atom(position,angle);

            case FinalValues.GAMMA:
                return new Gamma_Atom(position,angle);

            default:
                return new Sigma_Atom(position,angle);

        }


    }
}
