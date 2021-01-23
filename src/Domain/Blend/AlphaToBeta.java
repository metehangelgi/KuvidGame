package Domain.Blend;

import Domain.Utils.FinalValues;

import java.util.HashMap;

public class AlphaToBeta implements TransformStrategy {

    private final int ALPHA=2;
    private final int BETA=1;

    @Override
    public boolean Transform(HashMap<String, Integer> atomMap) {
        int amount=atomMap.get(FinalValues.ALPHA);
        if(amount<ALPHA) return false;
        atomMap.put(FinalValues.ALPHA,atomMap.get(FinalValues.ALPHA)-ALPHA);
        atomMap.put(FinalValues.BETA,atomMap.get(FinalValues.BETA)+BETA);
        return true;
    }
}
