package com.liquorice.app.android.ui.activities.abs;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.liquorice.app.android.R;
import com.liquorice.app.android.ui.sections.FontFragment;
import com.liquorice.app.android.ui.sections.OtherViewsFragment;
import com.liquorice.app.android.ui.sections.adapteredrecyclerview.ReportFragment;
import com.liquorice.app.android.ui.sections.feedback.FeedbackFragment;
import com.liquorice.app.android.ui.sections.generalsettings.GeneralSettingsFragment;
import com.liquorice.app.android.ui.sections.menu.MenuDrawerFragment;
import com.liquorice.app.android.ui.sections.menu.MenuItems;
import com.liquoriceutils.helpers.log.Logger;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by eyablonskaya on 9/27/2016.
 */

public class BaseSlidingMenuActivity extends BaseActivity implements MenuDrawerFragment.FragmentDrawerListener {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.drawer_layout)
    DrawerLayout drawerlayout;

    private Logger logger = Logger.getLogger(BaseSlidingMenuActivity.class);
    private MenuDrawerFragment drawerFragment;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slidingmenu);
        ButterKnife.bind(this);

        initializeToolbar();

        drawerFragment = (MenuDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, drawerlayout, toolbar);
        drawerFragment.setDrawerListener(this);

        displayView(MenuItems.MENU_GENERAL_SETTINGS);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void initializeToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    private void displayView(int position) {
        switch (position) {
            case MenuItems.MENU_FEEDBACK:
                openFeedback();
                break;
            case MenuItems.MENU_GENERAL_SETTINGS:
                openSettings();
                break;
            case MenuItems.MENU_ADAPTEREDRECYCLERVIEW:
                openAdapteredRecyclerView();
                break;
            case MenuItems.MENU_FONTS:
                openFontsScreen();
                break;
            case MenuItems.MENU_OTHER_VIEWS:
                openOtherViews();
                break;
            default:
                logger.error("Menu isn't identified for position: " + position);
                break;
        }
    }

    private void openOtherViews() {
        openFragment(OtherViewsFragment.getInstance(getIntent().getExtras()), getString(R.string.menu_item_other_views));
        setMenuTitle(R.string.menu_item_other_views);
        drawerFragment.toogleMenu();
    }

    private void openAdapteredRecyclerView() {
        openFragment(ReportFragment.getInstance(getIntent().getExtras()), getString(R.string.menu_item_adapteredrecyclerview));
        setMenuTitle(R.string.menu_item_adapteredrecyclerview);
        drawerFragment.toogleMenu();
    }

    private void openSettings() {
        openFragment(GeneralSettingsFragment.getInstance(getIntent().getExtras()), getString(R.string.menu_item_general_settings));
        setMenuTitle(R.string.menu_item_general_settings);
        drawerFragment.toogleMenu();
    }

    private void openFeedback() {
        openFragment(FeedbackFragment.getInstance(getIntent().getExtras()), getString(R.string.menu_item_feedback));
        setMenuTitle(R.string.text_feedback_instructions_title);
        drawerFragment.toogleMenu();
    }

    private void openFontsScreen() {
        openFragment(FontFragment.getInstance(getIntent().getExtras()), getString(R.string.menu_item_fonts));
        setMenuTitle(R.string.menu_item_fonts);
        drawerFragment.toogleMenu();
    }

    private void setMenuTitle(int titleId) {
        getSupportActionBar().setTitle(titleId);
    }

    @Override
    public int getContentHolderId() {
        return R.id.content_frame;
    }

}
