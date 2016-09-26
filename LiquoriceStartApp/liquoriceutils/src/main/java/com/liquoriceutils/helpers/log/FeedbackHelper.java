package com.liquoriceutils.helpers.log;

import android.content.Context;
import android.text.TextUtils;

import com.liquoriceutils.utils.R;
import com.liquoriceutils.helpers.io.FileUtils;

import java.util.List;

/**
 * FeedbackHelper is used to send feedback to support team.
 */
public class FeedbackHelper {
    private static FeedbackHelper feedbackHelper;

    private FeedbackHelper() {
    }

    public static FeedbackHelper getInstance() {
        if (feedbackHelper == null) {
            feedbackHelper = new FeedbackHelper();
        }
        return feedbackHelper;
    }

    /**
     * Fills feedback info
     * @param context
     * @param emailsToSend is the list of email addresses, which will be input in the field 'To:'
     * @param emailsToSendCopy is the list of email addresses, which will be input in the field 'CC:'
     * @return true if success else false
     */
    public boolean fillFeedbackSupportInfo(Context context, List<String> emailsToSend, List<String> emailsToSendCopy) {
        if (emailsToSend != null && emailsToSendCopy != null) {
            List<String> linesFromRawFile = FileUtils.getFileLinesFromRawFile(context, R.raw.feedback_copy_emails);
            for (String line : linesFromRawFile) {
                if (FeedbackHelper.isValidEmail(line)) {
                    emailsToSendCopy.add(line);
                }
            }
            linesFromRawFile = FileUtils.getFileLinesFromRawFile(context, R.raw.feedback_emails);
            for (String line : linesFromRawFile) {
                if (FeedbackHelper.isValidEmail(line)) {
                    emailsToSend.add(line);
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Checks if email has valid format.
     * @param email is email address
     * @return true if email is valid otherwise false
     */
    public static boolean isValidEmail(CharSequence email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

}
