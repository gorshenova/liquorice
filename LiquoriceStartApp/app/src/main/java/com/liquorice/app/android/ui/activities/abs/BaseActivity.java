package com.liquorice.app.android.ui.activities.abs;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.liquorice.app.android.ui.fragments.abs.BaseFragment;

/**
 * Created by eyablonskaya on 9/27/2016.
 */

public abstract class BaseActivity extends AppCompatActivity {

    public FragmentTransaction getFragmentTransaction() {
        return getSupportFragmentManager().beginTransaction();
    }
    public abstract int getContentHolderId();

    public void add(BaseFragment fragment, int container) {
        getFragmentTransaction()
                .add(container, fragment, fragment.getClass().getSimpleName())
                .commitAllowingStateLoss();
    }

    public void replaceInBackStack(BaseFragment fragment, int container, String tag) {
        getFragmentTransaction()
                .replace(container, fragment,
                        tag)
                .addToBackStack(tag)
                .commitAllowingStateLoss();
    }


    protected void openFragment(BaseFragment fragment, String fragmentTag) {
        if (fragment.isAdded()) {
            replaceInBackStack(fragment, getContentHolderId(), fragmentTag);
        } else {
            add(fragment, getContentHolderId());
        }
    }


}
