package Domain.Blend;

import java.util.HashMap;

public interface TransformStrategy {
    boolean Transform(HashMap<String,Integer> atomMap);
}
