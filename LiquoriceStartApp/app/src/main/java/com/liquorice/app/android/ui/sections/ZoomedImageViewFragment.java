package com.liquorice.app.android.ui.sections;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liquorice.app.android.R;
import com.liquorice.app.android.ui.fragments.abs.BaseFragment;
import com.liquoriceutils.views.ZoomedImageView;

/**
 * Created by eyablonskaya on 11-Jan-17.
 */

public class ZoomedImageViewFragment extends BaseFragment {

    private static int[] images = { R.drawable.nature_1, R.drawable.nature_2, R.drawable.nature_3, R.drawable.nature_4 };
    private ZoomedImageView zoomedImageView;
    private int imageIndex;

    public static ZoomedImageViewFragment getInstance(Bundle args) {
        ZoomedImageViewFragment fragment = new ZoomedImageViewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zoomed_imageview, container, false);
        zoomedImageView = (ZoomedImageView) view.findViewById(R.id.touch_iv);
//
        // Set first image
        //
        setCurrentImage();
        //
        // Set next image with each button click
        //
        zoomedImageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setCurrentImage();
            }
        });
        return view;
    }

    private void setCurrentImage() {
        zoomedImageView.setImageResource(images[imageIndex]);
        imageIndex = (++imageIndex % images.length);
    }

}
