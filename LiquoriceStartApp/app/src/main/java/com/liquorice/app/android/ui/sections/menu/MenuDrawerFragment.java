package com.liquorice.app.android.ui.sections.menu;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.liquorice.app.android.R;
import com.liquorice.app.android.ui.fragments.abs.BaseFragment;
import com.liquorice.app.android.ui.sections.menu.NavigationDrawerAdapter;

import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by eyablonskaya on 9/27/2016.
 */

public class MenuDrawerFragment extends BaseFragment {

    @Bind(R.id.drawerList)
    RecyclerView drawerList;

    private DrawerLayout mDrawerLayout;
    private View containerView;
    private FragmentDrawerListener drawerListener;


    public void setDrawerListener(FragmentDrawerListener drawerListener) {
        this.drawerListener = drawerListener;
    }

    public void toogleMenu() {
        mDrawerLayout.closeDrawer(containerView);
    }

    public void setUp(int fragmentId, DrawerLayout drawerlayout, final Toolbar toolbar) {
        containerView =  getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerlayout;
        mDrawerLayout.bringToFront();
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(getActivity(),
                mDrawerLayout, toolbar, R.string.menu_drawer_open, R.string.menu_drawer_close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                toolbar.setAlpha(1 - slideOffset / 2);
            }
        };

    }

    public interface FragmentDrawerListener {
        void onDrawerItemSelected(View view, int position);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        ButterKnife.bind(this, view);

        NavigationDrawerAdapter drawerAdapter = new NavigationDrawerAdapter(getContext());
        drawerList.setAdapter(drawerAdapter);
        drawerList.setLayoutManager(new LinearLayoutManager(getContext()));
        String[] menuLabels = getActivity().getResources().getStringArray(R.array.menu_items);
        drawerAdapter.setMenuItems(Arrays.asList(menuLabels));
        drawerList.addOnItemTouchListener(new MenuRecyclerTouchListener(getContext(), drawerList, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                drawerListener.onDrawerItemSelected(view, position);

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        return view;
    }

    static class MenuRecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ClickListener menuItemClickListener;

        public MenuRecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener menuItemClickListener) {
            this.menuItemClickListener = menuItemClickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && menuItemClickListener != null) {
                        menuItemClickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && menuItemClickListener != null && gestureDetector.onTouchEvent(e)) {
                menuItemClickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }

    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }


}
