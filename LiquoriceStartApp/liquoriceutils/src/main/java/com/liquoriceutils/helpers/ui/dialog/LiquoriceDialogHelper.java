package com.liquoriceutils.helpers.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.liquoriceutils.helpers.log.Logger;
import com.liquoriceutils.R;

public class LiquoriceDialogHelper {

    private static Logger logger = Logger.getLogger(LiquoriceDialogHelper.class);

    public static void showToast(Context context, int message) {
        showToast(context, context.getString(message));
    }

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static void showDoubleButtonDialog(Context context, String message, int positiveButtonResId, int negativeButtonResId, int colorPrimaryId,
                                              DialogInterface.OnClickListener clickListener) {
        showDoubleButtonDialog(context, null, message,
                positiveButtonResId,
                negativeButtonResId, clickListener);
    }

    public static void showDoubleButtonDialog(Context context, String title, String message, int positiveButtonResId,
                                              int negativeButtonResId, DialogInterface.OnClickListener clickListener) {
        AlertDialog.Builder builder = getAlertDialogBuilder(context);
        builder.setMessage(message).setTitle(title)
                .setNegativeButton(negativeButtonResId, clickListener)
                .setPositiveButton(positiveButtonResId, clickListener);
        showDialog(builder, context);
    }

    public static AlertDialog createDoubleButtonDialog(final Context context, String title, String message, int positiveButtonResId,
                                                       int negativeButtonResId, DialogInterface.OnClickListener clickListener) {
        AlertDialog.Builder builder = getAlertDialogBuilder(context);
        builder.setMessage(message).setTitle(title)
                .setNegativeButton(negativeButtonResId, clickListener)
                .setPositiveButton(positiveButtonResId, clickListener);
        builder.setCancelable(false);
        return builder.create();
    }

    public static void showSingleButtonDialog(Context context, int messageId, int positiveButtonResId) {
        AlertDialog.Builder builder = getAlertDialogBuilder(context);
        builder.setMessage(messageId).setPositiveButton(positiveButtonResId, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        showDialog(builder, context);
    }

    public static void showSingleButtonDialog(Context context, int messageId, int positiveButtonResId, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = getAlertDialogBuilder(context);
        builder.setMessage(messageId).setPositiveButton(positiveButtonResId, listener);
        showDialog(builder, context);
    }

    public static void showPermissionDialog(Context context, boolean shouldShowRationale,
                                            @StringRes int contentResId, int positiveButtonResId,
                                            int negativeButtonResId, DialogInterface.OnClickListener clickListener) {
        android.support.v7.app.AlertDialog.Builder builder = (shouldShowRationale) ? getSupportAlertDialogBuilder(context) : getWarningAlertDialogBuilder(context);
        builder.setMessage(contentResId)
                .setNegativeButton(negativeButtonResId, clickListener)
                .setPositiveButton(positiveButtonResId, clickListener);
        showSupportDialog(builder);
    }

    private static void showCustomDialog(AppCompatActivity context, String message) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View customDialogView = inflater.inflate(R.layout.custom_dialog, new RelativeLayout(context), false);
        TextView title = (TextView) customDialogView.findViewById(R.id.txtDiaMsg);
        title.setText(message);
        dialog.setContentView(customDialogView, customDialogView.getLayoutParams());
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button dialogButton = (Button) dialog.findViewById(R.id.btnOk);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if (!context.isFinishing() || !context.isDestroyed()) {
                dialog.show();
            }
        }
    }

    public static void showErrorButtonDialog(AppCompatActivity context, int stringID) {
        showCustomDialog(context, context.getResources().getString(stringID));
    }

    public static void showErrorButtonDialog(AppCompatActivity context, String message) {
        showCustomDialog(context, message);
    }

    private static void showDialog(AlertDialog.Builder builder, final Context context) {
        final AlertDialog alertDialog = builder.create();
        builder.setCancelable(false);
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
                alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
            }
        });
        alertDialog.show();
    }

    private static void showSupportDialog(android.support.v7.app.AlertDialog.Builder builder) {
        builder.create();
        builder.setCancelable(false);
        builder.show();
    }

    private static AlertDialog.Builder getAlertDialogBuilder(Context context) {
        return new AlertDialog.Builder(context);
    }

    private static android.support.v7.app.AlertDialog.Builder getSupportAlertDialogBuilder(Context context) {
        return new android.support.v7.app.AlertDialog.Builder(context);
    }

    private static android.support.v7.app.AlertDialog.Builder getWarningAlertDialogBuilder(Context context) {
        return new android.support.v7.app.AlertDialog.Builder(context, R.style.Liquorice_Theme_Dialog_Dark);
    }

    public static void safeClose(Dialog dialog) {
        try {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Dialog getProgressDialog(Context context) {
        LiquoriceProgressDialog dialog = new LiquoriceProgressDialog(context);
        dialog.setCancelable(false);
        return dialog;
    }

    public static ProgressDialog createAndShowProgressDialogWithMessage(Context context, String message) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(message);
        progressDialog.setCancelable(false);
        progressDialog.show();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ProgressBar progressBar = (ProgressBar) progressDialog.findViewById(android.R.id.progress);
            if (progressBar != null) {
                progressBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(context, R.color.colorPrimaryDark), PorterDuff.Mode.SRC_ATOP);
            }
        } else {
            progressDialog.setIndeterminateDrawable(ContextCompat.getDrawable(context, R.drawable.rotating_pb_bg));
        }
        return progressDialog;
    }

}