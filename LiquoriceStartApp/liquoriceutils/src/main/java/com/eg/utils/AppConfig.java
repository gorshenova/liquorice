package com.eg.utils;

/**
 * Util's constants
 */

public class AppConfig {

    /**
     * Setup 'false' value for DEBUG constant when release mode and 'true' when debug mode
     * This constants was added because for module project BuildConfig.DEBUG always has true value.
     * It does not appropriate for encryprion.decryprion logs
     */
    public static final boolean DEBUG = true;
}
