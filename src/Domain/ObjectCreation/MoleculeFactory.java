package Domain.ObjectCreation;

import Domain.Objects.*;
import Domain.Utils.Position;

public class MoleculeFactory {
    private static MoleculeFactory moleculeFactory;

    private MoleculeFactory(){}

    public static MoleculeFactory getInstance(){
        if(moleculeFactory ==null){
            moleculeFactory =new MoleculeFactory();
        }
        return moleculeFactory;
    }


    public GameObject createMolecule(String subtype, Position position) {

        switch (subtype) {
            case "Alpha":
                return new Alpha_Molecule(position);

            case "Beta":
                return new Beta_Molecule(position);

            case "Gamma":
                return new Gamma_Molecule(position);

            default:
                return new Sigma_Molecule(position);

        }


    }
}
