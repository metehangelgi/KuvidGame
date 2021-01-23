package Domain.Blend;

import Domain.Utils.FinalValues;

import java.util.HashMap;

public class BetaToSigma implements TransformStrategy {
    private final int BETA=3;
    private final int SIGMA=1;

    @Override
    public boolean Transform(HashMap<String, Integer> atomMap) {
        int amount=atomMap.get(FinalValues.BETA);
        if(amount<BETA) return false;
        atomMap.put(FinalValues.BETA,atomMap.get(FinalValues.BETA)-BETA);
        atomMap.put(FinalValues.SIGMA,atomMap.get(FinalValues.SIGMA)+SIGMA);
        return true;
    }
}
