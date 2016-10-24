package com.liquoriceutils.helpers;

import android.util.Base64;

import com.liquoriceutils.helpers.log.Logger;

/**
 * LiquoriceSecretHidingUtils is a simple utility to manage the XOR hiding routines.
 */
public class LiquoriceSecretHidingUtils {
    private static Logger logger = Logger.getLogger(LiquoriceSecretHidingUtils.class);

    /**
     * 'Hide' a message using a password using XOR operations.
     * <p>
     * Note: Please do not depend on XOR for real encryption. We're using it here as a very
     * simple hiding scheme.
     *
     * @param msg The message that we want to hide/unhide- The XOR is done-in place.
     * @param pwd The password to use as our OTP
     * @return the number of bytes that were processed
     */
    public static int xorValues(byte[] msg, byte[] pwd) {
        int i;
        for (i = 0; i < msg.length; i++) {
            int keyOffset = i % pwd.length;
            msg[i] = (byte) (msg[i] ^ pwd[keyOffset]);
        }
        return i;
    }

    /**
     * A reusable method to make our simple XOR hiding method. Since the interesting part is
     * how we get the hiding key, we've moved everything else into this reusable method.
     *
     * @param msg      The message to hide/unhide
     * @param pwd      Our password key to use in the XOR process
     * @param isHidden whether we're encrypting or unencrypting (relevant only for logging)
     */
    public static void doHiding(byte[] msg, byte[] pwd, boolean isHidden) {
        xorValues(msg, pwd);

        if (!isHidden) {
            String hiddenMessage = Base64.encodeToString(msg, 0);
            logger.info(String.format("Hidden Message: %s", hiddenMessage));
            doHiding(msg, pwd, true);
        } else {
            logger.info(String.format("Unhidden Message: %s", new String(msg)));
        }
    }
}
