package com.liquorice.app.android.ui.sections;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liquorice.app.android.R;
import com.liquorice.app.android.ui.fragments.abs.BaseFragment;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * OtherViewsFragment
 */
public class OtherViewsFragment extends BaseFragment {

    @Bind(R.id.iv_rounded_squared_pic)
    RoundedImageView roundedImageView;
    @Bind(R.id.iv_rounded_rectangular_pic)
    RoundedImageView rectangularImageView;
    @Bind(R.id.iv_rounded_placeholder_pic)
    RoundedImageView placeHolderImageView;

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

        roundedImageView.setImageResource(R.drawable.profile_placeholder);
        rectangularImageView.setImageResource(R.drawable.koala);
        Picasso.with(getContext()).load(R.drawable.koala).centerCrop().resize(50, 50).into(placeHolderImageView);
        return view;
    }
}
