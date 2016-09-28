package com.liquorice.app.android.ui.sections.generalsettings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liquorice.app.android.R;
import com.liquorice.app.android.ui.fragments.abs.BaseFragment;
import com.liquoriceutils.helpers.LiquoriceStringHelper;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by eyablonskaya on 9/28/2016.
 */

public class GeneralSettingsFragment extends BaseFragment {

    @Bind(R.id.tv_general_settings)
    TextView settingsTextView;

    public static GeneralSettingsFragment getInstance(Bundle args) {
        GeneralSettingsFragment frag = new GeneralSettingsFragment();
        frag.setArguments(args);
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_general_settings, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        setupSettingsText();
    }

    private void setupSettingsText() {
        settingsTextView.setText(LiquoriceStringHelper.getHtmlString(getString(R.string.general_settings_instructions)));
    }
}
