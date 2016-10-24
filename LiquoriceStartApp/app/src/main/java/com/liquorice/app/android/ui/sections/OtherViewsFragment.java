package com.liquorice.app.android.ui.sections;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.liquorice.app.android.R;
import com.liquorice.app.android.ui.fragments.abs.BaseFragment;
import com.liquoriceutils.views.RoundedImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * OtherViewsFragment
 */
public class OtherViewsFragment extends BaseFragment {

    @Bind(R.id.iv_rounded)
    RoundedImageView roundedImageView;

    public static OtherViewsFragment getInstance(Bundle args) {
        OtherViewsFragment f = new OtherViewsFragment();
        f.setArguments(args);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_other_views, container, false);
        ButterKnife.bind(this, view);

        //roundedImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        roundedImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        roundedImageView.setOval(true);
        roundedImageView.setImageResource(R.drawable.icon_pic);
        return view;
    }
}
