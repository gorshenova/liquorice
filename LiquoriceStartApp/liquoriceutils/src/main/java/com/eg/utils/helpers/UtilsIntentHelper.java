package com.eg.utils.helpers;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;

import com.eg.utils.R;
import com.eg.utils.UtilsApplication;
import com.eg.utils.helpers.log.Logger;

import java.io.File;
import java.util.List;

/**
 * Intent helper
 */
public class UtilsIntentHelper {

    private static Logger logger = Logger.getLogger(UtilsIntentHelper.class);

    public static boolean openEmailActivityWithLogFileAttachment(AppCompatActivity activity,
                                                                 String packageName,
                                                                 List<String> destEmail,
                                                                 List<String> ccDestEmail,
                                                                 String subject,
                                                                 String msgBody,
                                                                 String chooserTitle) {
        Intent emailIntent = buildIntentToSendEmail(destEmail, ccDestEmail, subject, msgBody);
        Uri uri = FileProvider.getUriForFile(
                activity.getBaseContext(),
                packageName,
                new File(Logger.saveLogcatToFile(activity)));
        emailIntent.putExtra(Intent.EXTRA_STREAM, uri);
        emailIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        try {
            activity.startActivity(Intent.createChooser(emailIntent, chooserTitle));
            return true;
        } catch (ActivityNotFoundException ex) {
            logger.error(UtilsApplication.getContext().getString(R.string.error_override_dir));
            return false;
        }
    }

    private static Intent buildIntentToSendEmail(List<String> destEmail, List<String> ccDestEmail, String subject, String msgBody) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("text/html");
        emailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, destEmail.toArray(new String[destEmail.size()]));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, msgBody);
        if (ccDestEmail != null) {
            emailIntent.putExtra(Intent.EXTRA_CC, ccDestEmail.toArray(new String[ccDestEmail.size()]));
        }
        return emailIntent;
    }
}
