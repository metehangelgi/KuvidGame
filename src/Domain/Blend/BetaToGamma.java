package Domain.Blend;

import Domain.Utils.FinalValues;

import java.util.HashMap;

public class BetaToGamma implements TransformStrategy {
    private final int BETA=2;
    private final int GAMMA=1;

    @Override
    public boolean Transform(HashMap<String, Integer> atomMap) {
        int amount=atomMap.get(FinalValues.BETA);
        if(amount<BETA) return false;
        atomMap.put(FinalValues.BETA,atomMap.get(FinalValues.BETA)-BETA);
        atomMap.put(FinalValues.GAMMA,atomMap.get(FinalValues.GAMMA)+GAMMA);
        return true;
    }
}
