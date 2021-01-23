package Domain.Blend;

import Domain.Utils.FinalValues;

import java.util.HashMap;

public class SigmaToGamma implements TransformStrategy {

    private final int GAMMA=2;
    private final int SIGMA=1;

    @Override
    public boolean Transform(HashMap<String, Integer> atomMap) {
        int amount=atomMap.get(FinalValues.SIGMA);
        if(amount<SIGMA) return false;
        atomMap.put(FinalValues.SIGMA,atomMap.get(FinalValues.SIGMA)-SIGMA);
        atomMap.put(FinalValues.GAMMA,atomMap.get(FinalValues.GAMMA)+GAMMA);
        return true;
    }
}
