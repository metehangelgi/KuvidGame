package Domain.Blend;

public class TransformStrategyFactory {

    private static TransformStrategyFactory transformStrategyFactory;

    private TransformStrategyFactory(){}

    public static TransformStrategyFactory getInstance(){
        if(transformStrategyFactory ==null){
            transformStrategyFactory =new TransformStrategyFactory();
        }
        return transformStrategyFactory;
    }

    public TransformStrategy getStrategy(String from, String to){
        TransformStrategy transformStrategy =null;
        switch (from){
            case "Alpha":
                switch (to) {
                    case "Beta":
                        transformStrategy = new AlphaToBeta();
                        break;
                    case "Gamma":
                        transformStrategy = new AlphaToGamma();
                        break;
                    case "Sigma":
                        transformStrategy = new AlphaToSigma();
                        break;
                }
                break;
            case "Beta":
                switch (to) {
                    case "Alpha":
                        transformStrategy =new BetaToAlpha();
                        break;
                    case "Gamma":
                        transformStrategy = new BetaToGamma();
                        break;
                    case "Sigma":
                        transformStrategy = new BetaToSigma();
                        break;
                }
                break;
            case "Gamma":
                switch (to) {
                    case "Alpha":
                        transformStrategy =new GammaToAlpha();
                        break;
                    case "Beta":
                        transformStrategy = new GammaToBeta();
                        break;
                    case "Sigma":
                        transformStrategy = new GammaToSigma();
                        break;
                }
                break;
            case "Sigma":
                switch (to) {
                    case "Alpha":
                        transformStrategy =new SigmaToAlpha();
                        break;
                    case "Beta":
                        transformStrategy = new SigmaToBeta();
                        break;
                    case "Gamma":
                        transformStrategy = new SigmaToGamma();
                        break;
                }
                break;
        }
        return transformStrategy;
    }

}
