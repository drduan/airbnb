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

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import net.sarangnamu.cloneairbnb.net.sarangnamu.common.ui.tab.BkTab;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private static final Logger mLog = LoggerFactory.getLogger(MainActivity.class);

    @Bind(R.id.tab) BkTab mTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initTab();
    }

    private void initTab() {
        ArrayList<Integer> data = new ArrayList<>();
        data.add(R.drawable.ic_filter_1_24dp);
        data.add(R.drawable.ic_favorite_outline_24dp);
        mTab.setData(data);data.add(R.drawable.ic_filter_1_24dp);
        mTab.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                mLog.error("checked id : " + checkedId);
            }
        });
    }
}
