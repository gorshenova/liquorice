package com.liquorice.app.android.ui.sections.feedback;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.liquorice.app.android.R;
import com.liquorice.app.android.core.LiquoriceStartApplication;
import com.liquorice.app.android.ui.activities.abs.BaseActivity;
import com.liquorice.app.android.ui.fragments.abs.BaseFragment;
import com.liquoriceutils.helpers.LiquoriceIntentHelper;
import com.liquoriceutils.helpers.LiquoriceStringHelper;
import com.liquoriceutils.helpers.log.FeedbackHelper;
import com.liquoriceutils.helpers.log.Logger;
import com.liquoriceutils.helpers.ui.dialog.LiquoriceDialogHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by eyablonskaya on 9/27/2016.
 */

public class FeedbackFragment extends BaseFragment implements View.OnClickListener{

    @Bind(R.id.tv_feedback_instruction)
    TextView feedbackTextView;

    @Bind(R.id.btn_send_feedback)
    Button sendFeedbackButton;

    public static final String FEEDBACK_TOPIC_DIVIDER = "/";

    private BaseActivity activity;
    public static FeedbackFragment getInstance(Bundle args) {
        FeedbackFragment fragment = new FeedbackFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (BaseActivity) getActivity();
        Logger.error(FeedbackFragment.class, "FeedbackFragment onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feeback, container, false);
        ButterKnife.bind(this, view);
        initInstructions();
        sendFeedbackButton.setOnClickListener(this);

        Logger.error(FeedbackFragment.class, "FeedbackFragment onCreateView ++++++++++++++");
        return view;
    }

    private void initInstructions(){
        feedbackTextView.setText(LiquoriceStringHelper.getHtmlString(getString(R.string.text_feedback_instructions)));
    }

    @Override
    public void onClick(View v) {
        if(v == sendFeedbackButton){
            openFeedback();
        }
    }

    private void openFeedback(){
        List<String> emailsToSend = new ArrayList<>();
        List<String> emailsToSendCopy = new ArrayList<>();
        FeedbackHelper.getInstance().fillFeedbackSupportInfo(getActivity(), emailsToSend, emailsToSendCopy,
                R.raw.feedback_emails, R.raw.feedback_copy_emails);

        String username = "Username";
        String body = "Feedback message...";
        boolean successfullySending = LiquoriceIntentHelper.openEmailActivityWithLogFileAttachment(activity,
                LiquoriceStartApplication.getContext().getPackageName(),
                emailsToSend, emailsToSendCopy,
                getFeedbackTopic(getActivity(), username),
                body,
                getString(R.string.text_choose_matching_email_client));
        if(!successfullySending){
            LiquoriceDialogHelper.showSingleButtonDialog(getActivity(), R.string.text_no_email_clients, R.string.btn_ok);
        }

    }

    public String getFeedbackTopic(Context context, String username) {
        StringBuilder builder = new StringBuilder();
        String formattedDate = new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(new Date());
        builder.append(context.getString(R.string.feedback_email_subject)).append(FEEDBACK_TOPIC_DIVIDER);
        if (username != null) {
            builder.append(username).append(FEEDBACK_TOPIC_DIVIDER);
        }
        builder.append(formattedDate);
        return builder.toString();
    }
}
