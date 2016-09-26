package com.rocketstart.app.android;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.liquoriceutils.helpers.LiquoriceIntentHelper;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Logger logger = Logger.getLogger(MainActivity.class);

    public static final String FEEDBACK_TOPIC_DIVIDER = "/";

    @Bind(R.id.button_feedback)
    Button buttonFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logger.debug("onCreate");
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        buttonFeedback.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        logger.debug("onCreateOptionsMenu");
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        logger.debug("onOptionsItemSelected");
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.button_feedback){
            openFeedback();
        }
    }

    /**
     * Don't forget to modify manifest file. It needs to add provider,
     * see comment 'Provider to attach log file to feedback email'
     * Add a 'res/xml' folder with a file 'filepaths'
     */
    private void openFeedback(){
        List<String> emailsToSend = new ArrayList<>();
        List<String> emailsToSendCopy = new ArrayList<>();
        FeedbackHelper.getInstance().fillFeedbackSupportInfo(this, emailsToSend, emailsToSendCopy);

        String username = "Jon Mayer";
        String body = "User Jon Mayer see this application and click on button send feedback...";
        boolean successfullySending = LiquoriceIntentHelper.openEmailActivityWithLogFileAttachment(this,
                LiquoriceStartApplication.getContext().getPackageName(),
                emailsToSend, emailsToSendCopy,
                getFeedbackTopic(this, username),
                body,
                getString(R.string.text_choose_matching_email_client));
        if(!successfullySending){
            LiquoriceDialogHelper.showSingleButtonDialog(this, R.string.text_no_email_clients, R.string.btn_ok, R.color.colorPrimaryDark);
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
