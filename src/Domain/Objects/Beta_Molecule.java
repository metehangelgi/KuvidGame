package Domain.Objects;

import Domain.Statistics.GameConfiguration;
import Domain.Utils.FinalValues;
import Domain.Utils.Position;

public class Beta_Molecule extends Molecule {


    public Beta_Molecule(Position position) {
        super(FinalValues.BETA, position,90);
        setShape(GameConfiguration.getInstance().getData().getAlphaBetaType());
        setRotationType(GameConfiguration.getInstance().getData().getMovementType());
        setHeightWidth();
    }

    private void setHeightWidth() {
        if(getShape().equals("LINEAR")){
            setWidth(2*getL()/3);
            setHeight(getL()/4);
        }
    }


}
