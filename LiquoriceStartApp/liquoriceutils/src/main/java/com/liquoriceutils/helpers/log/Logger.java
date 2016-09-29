package com.liquoriceutils.helpers.log;

import android.content.Context;
import android.util.Log;

import com.liquoriceutils.BuildConfig;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Locale;

/**
 * Used to output logs
 */
public class Logger {

    private static final String LOG_FILE_NAME = "log.txt";
    private static final String LOG_FILE_FORMAT = " -v time ";
    private static final String LOG_FILE_MAX_SIZE = " -r " + (10 * 1024); // 10 Mb (size must be in kb)
    private static final String FILTER_BODY_NAME = "<body>";
    private static final String FILTER_MSG_NAME = " <msg> ";
    private static final String TAG_NAME = "APP_NAME";
    private static final String CHUNK_FORMAT = "CHUNK %d OF %d:  %s";
    private static final int CONSOLE_LOG_LINE_LENGTH = 4000;

    private Class<?> cls;

    private Logger() {

    }

    private Logger(Class<?> cls) {
        this.cls = cls;
    }

    public static Logger getLogger(Class<?> cls) {
        return new Logger(cls);
    }


    /**
     * Method stores log in the file and use command format:
     * logcat -v time -r 100 -f <filename> [TAG]:I [MyApp]:D *:S
     * -v -> Sets the output format for log messages.
     * -r -> for specifying the size of file.
     * -f -> file to which you want to write the logs.
     * [TAG] -> Tag of your application's log.
     * [MyApp] -> Your application name.
     */
    public static String saveLogcatToFile(Context context) {
        File outputFile = new File(context.getFilesDir(), LOG_FILE_NAME);
        if (outputFile.exists()) {
            outputFile.delete();
        }
        try {
            String cmd = "logcat " + LOG_FILE_FORMAT + " -d -f " + outputFile.getAbsolutePath()+ LOG_FILE_MAX_SIZE
                    + " " + TAG_NAME + ":V " + getApplicationName(context) + ":V AndroidRuntime:E *:S";
            Process logcat = Runtime.getRuntime().exec(cmd);
            BufferedReader br = new BufferedReader(new InputStreamReader(logcat.getInputStream()), 4*1024);
            StringBuilder log = new StringBuilder();
            String line;
            String separator = System.getProperty("line.separator");
            while ((line = br.readLine()) != null) {
                log.append(line);
                log.append(separator);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outputFile.getAbsolutePath();
    }

    /**********************************************************************************************
     * DEBUG
     *********************************************************************************************/
    public void debug(String message) {
        debug(cls, message);
    }

    private static void debugOutput(String message) {
        print(Log.DEBUG, message);
    }

    public static void debug(Class<?> cls, String message) {
        String text = cls.getSimpleName() + FILTER_MSG_NAME + message;
        if (BuildConfig.DEBUG) {
            debugOutput(text);
        } else {
            try {
                String encryptedMsg = LoggerEncryptionManager.getEncodedBase64Key(text);
                debugOutput(encryptedMsg);
            } catch (UnsupportedEncodingException e) {
                getLogger(java.util.logging.Logger.class).error(e.getMessage());
            } catch (GeneralSecurityException e) {
                getLogger(java.util.logging.Logger.class).error(e.getMessage());
            }
        }
    }


    /**********************************************************************************************
     * INFO
     *********************************************************************************************/
    public void info(String message) {
        info(cls, message);
    }

    public static void info(Class<?> cls, String message) {
        String text = cls.getSimpleName() + FILTER_MSG_NAME + message;
        if (BuildConfig.DEBUG) {
            infoOutput(text);
        } else {
            try {
                String encryptedMsg = LoggerEncryptionManager.getEncodedBase64Key(text);
                infoOutput(encryptedMsg);
            } catch (UnsupportedEncodingException e) {
                getLogger(java.util.logging.Logger.class).error(e.getMessage());
            } catch (GeneralSecurityException e) {
                getLogger(java.util.logging.Logger.class).error(e.getMessage());
            }
        }
    }

    private static void infoOutput(String message) {
        print(Log.INFO, message);
    }


    /**********************************************************************************************
     * WARN
     *********************************************************************************************/
    public void warn(String message) {
        warn(cls, message);
    }

    public static void warn(Class<?> cls, String message) {
        String text = cls.getSimpleName() + FILTER_MSG_NAME + message;
        if (BuildConfig.DEBUG) {
            warnOutput(text);
        } else {
            try {
                String encryptedMsg = LoggerEncryptionManager.getEncodedBase64Key(text);
                warnOutput(encryptedMsg);
            } catch (UnsupportedEncodingException e) {
                getLogger(java.util.logging.Logger.class).error(e.getMessage());
            } catch (GeneralSecurityException e) {
                getLogger(java.util.logging.Logger.class).error(e.getMessage());
            }
        }
    }

    private static void warnOutput(String message) {
        print(Log.WARN, message);
    }


    /**********************************************************************************************
     * ERROR
     *********************************************************************************************/
    public void error(String message, Exception e) {
        error(cls, message, e);
    }

    public void error(String message) {
        error(cls, message);
    }

    public static void error(Class<?> cls, String message, Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String text = cls.getSimpleName() + FILTER_MSG_NAME + message + "\n" + sw.toString();
        if (BuildConfig.DEBUG) {
            errorOutput(text);
        } else {
            try {
                String encryptedMsg = LoggerEncryptionManager.getEncodedBase64Key(text);
                errorOutput(encryptedMsg);
            } catch (UnsupportedEncodingException ex) {
                getLogger(java.util.logging.Logger.class).error(ex.getMessage());
            } catch (GeneralSecurityException ex) {
                getLogger(java.util.logging.Logger.class).error(ex.getMessage());
            }
        }
    }

    public static void error(Class<?> cls, String message) {
        String text = cls.getSimpleName() + FILTER_MSG_NAME + message;
        if (BuildConfig.DEBUG) {
            errorOutput(text);
        } else {
            try {
                String encryptedMsg = LoggerEncryptionManager.getEncodedBase64Key(text);
                errorOutput(encryptedMsg);
            } catch (UnsupportedEncodingException e) {
                getLogger(java.util.logging.Logger.class).error(e.getMessage());
            } catch (GeneralSecurityException e) {
                getLogger(java.util.logging.Logger.class).error(e.getMessage());
            }
        }
    }

    private static void errorOutput(String message) {
        print(Log.ERROR, message);
    }

    /*********************************************************************************************/

    private static void print(int priority, String message) {
        String fullMsg = FILTER_BODY_NAME + message;
        if (fullMsg.length() > CONSOLE_LOG_LINE_LENGTH) {
            int chunkCount = fullMsg.length() / CONSOLE_LOG_LINE_LENGTH;     // integer division
            for (int i = 0; i <= chunkCount; i++) {
                int max = CONSOLE_LOG_LINE_LENGTH * (i + 1);
                String textToPrint;
                if (max >= fullMsg.length()) {
                    textToPrint = String.format(Locale.ENGLISH, CHUNK_FORMAT, i, chunkCount, fullMsg.substring(CONSOLE_LOG_LINE_LENGTH * i));
                } else {
                    textToPrint = String.format(Locale.ENGLISH, CHUNK_FORMAT, i, chunkCount, fullMsg.substring(CONSOLE_LOG_LINE_LENGTH * i, max));
                }
                Log.println(priority, TAG_NAME, textToPrint);
            }
        } else {
            Log.println(priority, TAG_NAME, FILTER_BODY_NAME + message);
        }
    }

    public static String getApplicationName(Context context) {
        int stringId = context.getApplicationInfo().labelRes;
        return context.getString(stringId);
    }

}