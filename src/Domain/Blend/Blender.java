package Domain.Blend;

import Domain.Statistics.GameConfiguration;
import Domain.Utils.FinalValues;
import java.util.HashMap;

public class Blender {

    private final HashMap<String, HashMap<String, Integer>> ammunition;

    public Blender(){
        ammunition = GameConfiguration.getInstance().getData().getAmmunition();
    }

    public void Transform(String fromType, String toType){
        //REQUIRES : ammunition != null
        //MODIFIES: ammunition
        /*EFFECTS:
        if fromType = toType:
            it does not affect ammunition
        else:
            if there is enough amount from fromType:
                let x fromType = y toType according to blend rules, this method sets the ammunition map as
                changing (value of fromType) = (value of fromType)-x
                and changing (value of toType) = (value of toType)+y
                Blend rules:
                       2 ALPHA = 1 BETA
                       3 ALPHA = 1 GAMMA
                       4 ALPHA = 1 SIGMA
                       2 BETA = 1 GAMMA
                       3 BETA = 1 SIGMA
                       2 GAMMA = 1 SIGMA
            else:
                it does not affect ammunition

         */
        if(fromType.equals(toType)) return;
        TransformStrategyFactory transformStrategyFactory = TransformStrategyFactory.getInstance();
        TransformStrategy transformStrategy = transformStrategyFactory.getStrategy(fromType,toType);
        BlendBreak blendBreak=new BlendBreak(transformStrategy);
        boolean isTransformed=blendBreak.executeTransform(ammunition.get(FinalValues.ATOM));
        if(isTransformed){
            GameConfiguration.getInstance().setAmmunition(ammunition);
        }else {

        }
    }
}
