package Domain.Blend;

import java.util.HashMap;

public class BlendBreak {
    private TransformStrategy transformStrategy;

    public BlendBreak(TransformStrategy transformStrategy){
        this.transformStrategy = transformStrategy;
    }

    public boolean executeTransform(HashMap<String,Integer> map){
        return transformStrategy.Transform(map);
    }
}
