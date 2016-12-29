package FlyWeight;

import java.util.HashMap;

/**
 * Created by Ty on 11/2/2016 at 7:27 PM.
 */
public class LogoFactory {

    private static final HashMap<Integer, LogoIntrinsicState> objectsByType = new HashMap<>();

    public static LogoBothStates getImage(final int key, LogoExtrinsicState logoExtrinsicState) {

        LogoIntrinsicState logoIntrinsicState;
        if (objectsByType.containsKey(key)) {
            logoIntrinsicState = objectsByType.get(key);
        } else {
            logoIntrinsicState = new LogoIntrinsicState(key);
            objectsByType.put(key, logoIntrinsicState);
        }
        return new LogoBothStates(logoIntrinsicState, logoExtrinsicState);
    }
}
