package Domain.ObjectCreation;

import Domain.Objects.*;
import Domain.Utils.FinalValues;
import Domain.Utils.Position;

public class BlockerFactory {
    private static BlockerFactory blockerFactory;

    private BlockerFactory(){}

    public static BlockerFactory getInstance(){
        if(blockerFactory ==null){
            blockerFactory =new BlockerFactory();
        }
        return blockerFactory;
    }


    public GameObject createBlocker(String subtype, Position position ) {

        switch (subtype) {
            case FinalValues.ALPHA:
                return new Alpha_Blocker(position);

            case FinalValues.BETA:
                return new Beta_Blocker(position);

            case FinalValues.GAMMA:
                return new Gamma_Blocker(position);

            default:
                return new Sigma_Blocker(position);

        }


    }
}
