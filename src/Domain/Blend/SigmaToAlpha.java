package Domain.Blend;

import Domain.Utils.FinalValues;

import java.util.HashMap;

public class SigmaToAlpha implements TransformStrategy {

    private final int ALPHA=4;
    private final int SIGMA=1;

    @Override
    public boolean Transform(HashMap<String, Integer> atomMap) {
        int amount=atomMap.get(FinalValues.SIGMA);
        if(amount<SIGMA) return false;
        atomMap.put(FinalValues.SIGMA,atomMap.get(FinalValues.SIGMA)-SIGMA);
        atomMap.put(FinalValues.ALPHA,atomMap.get(FinalValues.ALPHA)+ALPHA);
        return true;
    }
}
