package Domain.Blend;

import Domain.Utils.FinalValues;

import java.util.HashMap;

public class AlphaToGamma implements TransformStrategy {
    private final int ALPHA=3;
    private final int GAMMA=1;

    @Override
    public boolean Transform(HashMap<String, Integer> atomMap) {
        int amount=atomMap.get(FinalValues.ALPHA);
        if(amount<ALPHA) return false;
        atomMap.put(FinalValues.ALPHA,atomMap.get(FinalValues.ALPHA)-ALPHA);
        atomMap.put(FinalValues.GAMMA,atomMap.get(FinalValues.GAMMA)+GAMMA);
        return true;
    }
}
