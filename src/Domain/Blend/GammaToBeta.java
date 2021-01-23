package Domain.Blend;

import Domain.Utils.FinalValues;

import java.util.HashMap;

public class GammaToBeta implements TransformStrategy {

    private final int BETA=2;
    private final int GAMMA=1;

    @Override
    public boolean Transform(HashMap<String, Integer> atomMap) {
        int amount=atomMap.get(FinalValues.GAMMA);
        if(amount<GAMMA) return false;
        atomMap.put(FinalValues.GAMMA,atomMap.get(FinalValues.GAMMA)-GAMMA);
        atomMap.put(FinalValues.BETA,atomMap.get(FinalValues.BETA)+BETA);
        return true;
    }
}
