package com.liquoriceutils.helpers.ui.dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;

import com.liquoriceutils.utils.R;


public class LiquoriceProgressDialog extends ProgressDialog { //TODO rename file
	private static final String PROGRESS_BACKGROUND_COLOR = "ProgressBackgroundColor";
	private int bgColor;

	public LiquoriceProgressDialog(Context context) {
		super(context, R.style.W2W_Theme_Dialog);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		super.onCreate(savedInstanceState);
        View root = View.inflate(getContext(), R.layout.dialog_progress, null);
		ProgressBar progress = (ProgressBar) root.findViewById(R.id.progress);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			if (progress != null) {
				progress.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark), PorterDuff.Mode.SRC_ATOP);
			}
		} else {
			progress.setIndeterminateDrawable(ContextCompat.getDrawable(getContext(), R.drawable.rotating_pb_bg));
		}
		if(bgColor == 0) {
			bgColor = savedInstanceState.getInt(PROGRESS_BACKGROUND_COLOR, 0);
		}
		root.setBackgroundColor(bgColor == 0 ? getContext().getResources().getColor(android.R.color.transparent) : bgColor);
		setContentView(root);
	}

	@Override
	public Bundle onSaveInstanceState() {
		Bundle bundle = super.onSaveInstanceState();
		bundle.putInt(PROGRESS_BACKGROUND_COLOR, bgColor);
		return bundle;
	}

	public void setBackgroundColor(int color) {
		this.bgColor = color;
	}
}
