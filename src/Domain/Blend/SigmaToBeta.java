package Domain.Blend;

import Domain.Utils.FinalValues;

import java.util.HashMap;

public class SigmaToBeta implements TransformStrategy {

    private final int BETA=3;
    private final int SIGMA=1;

    @Override
    public boolean Transform(HashMap<String, Integer> atomMap) {
        int amount=atomMap.get(FinalValues.SIGMA);
        if(amount<SIGMA) return false;
        atomMap.put(FinalValues.SIGMA,atomMap.get(FinalValues.SIGMA)-SIGMA);
        atomMap.put(FinalValues.BETA,atomMap.get(FinalValues.BETA)+BETA);
        return true;
    }
}
