/*
 * Copyright 2016. Burke Choi All rights reserved.
 *             http://www.sarangnamu.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.sarangnamu.cloneairbnb;

import android.os.AsyncTask;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;

import net.sarangnamu.cloneairbnb.menu.MenuManager;
import net.sarangnamu.cloneairbnb.page.sub.MainFrgmt;
import net.sarangnamu.cloneairbnb.page.sub.MessageFrgmt;
import net.sarangnamu.cloneairbnb.page.sub.TravelFrgmt;
import net.sarangnamu.cloneairbnb.page.sub.WishFrgmt;
import net.sarangnamu.common.ui.tab.BkTab;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private static final Logger mLog = LoggerFactory.getLogger(MainActivity.class);

    @Bind(R.id.tab) BkTab mTab;
    @Bind(R.id.drawer_layout) DrawerLayout mDrawerLayout;
    @Bind(R.id.navi_view) NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initTab();
        initLayout();
        initNavigationMenu();
    }

    private void initTab() {
        ArrayList<BkTab.BkData> data = new ArrayList<>();

        data.add(new BkTab.BkImageData(R.drawable.selector_tab, MainFrgmt.class));
        data.add(new BkTab.BkImageData(R.drawable.selector_tab, WishFrgmt.class));
        data.add(new BkTab.BkImageData(R.drawable.selector_tab, MessageFrgmt.class));
        data.add(new BkTab.BkImageData(R.drawable.selector_tab, TravelFrgmt.class));
        data.add(new BkTab.BkImageData(R.drawable.selector_tab, v -> {
            mDrawerLayout.openDrawer(Gravity.RIGHT);
        }));

        mTab.setButtonPadding(20);
        mTab.setData(data);
        mTab.setFrgmtManager(R.id.main, TabPageManager.getInstance(MainActivity.this));
        mTab.setChecked(0);
    }

    private void initLayout() {
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    private void initNavigationMenu() {
        mNavigationView.setNavigationItemSelectedListener(item -> {
            MenuManager.getInstance().changeMenu(MainActivity.this, item);
            return false;
        });
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(mNavigationView)) {
            mDrawerLayout.closeDrawers();
            return ;
        }

        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        DataManager.getInstance().closeRealm();

        super.onDestroy();
    }

    public void setVisibleTabMenu(boolean visible) {
//        if (visible) {
//            mTab.animate().translationY(mTab.getHeight() * -1);
//        } else {
//            mTab.animate().translationY(mTab.getHeight());
//        }
    }
}
