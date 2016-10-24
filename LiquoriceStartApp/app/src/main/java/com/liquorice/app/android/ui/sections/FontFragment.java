package com.liquorice.app.android.ui.sections;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.liquorice.app.android.R;
import com.liquorice.app.android.helpers.FontsUtils;
import com.liquorice.app.android.ui.fragments.abs.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * FontFragment
 */
public class FontFragment extends BaseFragment implements View.OnClickListener {

    @Bind(R.id.btnFontOne)
    Button btnFontOne;

    @Bind(R.id.btnFontTwo)
    Button btnFontTwo;
    @Bind(R.id.btnFontThree)
    Button btnFontThree;
    @Bind(R.id.btnFontFour)
    Button btnFontFour;
    @Bind(R.id.container)
    ViewGroup container;

    public static FontFragment getInstance(Bundle args) {
        FontFragment fragment = new FontFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fonts, container, false);
        ButterKnife.bind(this, view);

        FontsUtils.setFonts(btnFontOne, FontsUtils.FONT_LANEHUM);
        FontsUtils.setFonts(btnFontTwo, FontsUtils.FONT_ORANGE_JUICE);
        FontsUtils.setFonts(btnFontThree, FontsUtils.FONT_ORMONT_LIGHT);
        FontsUtils.setFonts(btnFontFour, FontsUtils.FONT_WEDGIE_REGULAR);

        btnFontOne.setOnClickListener(this);
        btnFontTwo.setOnClickListener(this);
        btnFontThree.setOnClickListener(this);
        btnFontFour.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnFontOne:
                FontsUtils.setFonts(container, FontsUtils.FONT_LANEHUM);
                break;
            case R.id.btnFontTwo:
                FontsUtils.setFonts(container, FontsUtils.FONT_ORANGE_JUICE);
                break;
            case R.id.btnFontThree:
                FontsUtils.setFonts(container, FontsUtils.FONT_ORMONT_LIGHT);
                break;
            case R.id.btnFontFour:
                FontsUtils.setFonts(container, FontsUtils.FONT_WEDGIE_REGULAR);
                break;

        }
    }
}
