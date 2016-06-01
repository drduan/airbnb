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
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;

import net.sarangnamu.cloneairbnb.menu.MenuManager;
import net.sarangnamu.cloneairbnb.net.domain.MainResponse;
import net.sarangnamu.cloneairbnb.page.sub.MainFrgmt;
import net.sarangnamu.cloneairbnb.page.sub.MessageFrgmt;
import net.sarangnamu.cloneairbnb.page.sub.SplashFrgmt;
import net.sarangnamu.cloneairbnb.page.sub.TravelFrgmt;
import net.sarangnamu.cloneairbnb.page.sub.WishFrgmt;
import net.sarangnamu.common.ui.tab.BkTab;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 헐 -_ - 따라만들다 보니 앱 디자인이 변경됨 ㅋㅋㅋㅋㅋ
 * 스샷이라도 나둘 걸 그랬나 -_ -;
 */
public class MainActivity extends AppCompatActivity {
    private static final Logger mLog = LoggerFactory.getLogger(MainActivity.class);

    @Bind(R.id.tab) BkTab mTab;
    @Bind(R.id.drawer_layout) DrawerLayout mDrawerLayout;
    @Bind(R.id.navi_view) NavigationView mNavigationView;

    private boolean mShowSplash = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        if (savedInstanceState == null) {
            initSplash();
            loadData();
        }
    }

    private void initSplash() {
        TabPageManager.getInstance(MainActivity.this).replace(R.id.drawer_layout, SplashFrgmt.class);
    }

    private void initTab() {
        ArrayList<BkTab.BkData> data = new ArrayList<>();

        data.add(new BkTab.BkImageData(R.drawable.selector_tab, android.R.color.white, MainFrgmt.class));
        data.add(new BkTab.BkImageData(R.drawable.selector_tab, android.R.color.white, WishFrgmt.class));
        data.add(new BkTab.BkImageData(R.drawable.selector_tab, android.R.color.white, MessageFrgmt.class));
        data.add(new BkTab.BkImageData(R.drawable.selector_tab, android.R.color.white, TravelFrgmt.class));
        data.add(new BkTab.BkImageData(R.drawable.selector_tab, android.R.color.white, v -> {
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
        if (mShowSplash) {
            return ;
        }

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

    private void loadData() {
        new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... data) {
                MainResponse maindata = NetHelper.getInstance().get(NetHelper.MAIN, MainResponse.class);
                if (maindata == null) {
                    return false;
                }

                if (mLog.isDebugEnabled()) {
                    mLog.debug("SIZE: " + maindata.getRecommandationList().size() + ", " + maindata.getFamousList().size());
                }

                DataManager.getInstance().setMainResponse(maindata);

                return true;
            }

            @Override
            protected void onPostExecute(Boolean result) {
                if (!result) {
                    // TODO RETRY
                    finish();
                    return ;
                }

                final SplashFrgmt splash = (SplashFrgmt) TabPageManager.getInstance(MainActivity.this).getCurrentFragment();
                if (splash == null) {
                    mLog.error("ERROR, NOT FOUND SPLASH FRAGMENT");
                    finish();
                    return ;
                }

                splash.setChangedIcon(true);

                mDrawerLayout.postDelayed(() -> {
                    mShowSplash = false;

                    Fragment frgmt = TabPageManager.getInstance(MainActivity.this).getCurrentFragment();
                    if (frgmt instanceof SplashFrgmt) {
                        try {
                            TabPageManager.getInstance(MainActivity.this).popBack();
                        } catch (Exception e) {

                        }

                        ((SplashFrgmt) frgmt).setChangedIcon(false);
                    }

                    initTab();
                    initLayout();
                    initNavigationMenu();
                }, Cfg.SPLASH_DELAY);
            }
        }.execute();
    }
}
