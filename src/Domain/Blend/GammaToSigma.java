package Domain.Blend;

import Domain.Utils.FinalValues;

import java.util.HashMap;

public class GammaToSigma implements TransformStrategy {
    private final int GAMMA=2;
    private final int SIGMA=1;

    @Override
    public boolean Transform(HashMap<String, Integer> atomMap) {
        int amount=atomMap.get(FinalValues.GAMMA);
        if(amount<GAMMA) return false;
        atomMap.put(FinalValues.GAMMA,atomMap.get(FinalValues.GAMMA)-GAMMA);
        atomMap.put(FinalValues.SIGMA,atomMap.get(FinalValues.SIGMA)+SIGMA);
        return true;
    }
}
