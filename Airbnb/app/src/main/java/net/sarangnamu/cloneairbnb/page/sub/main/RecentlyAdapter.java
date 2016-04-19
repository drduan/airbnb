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

package net.sarangnamu.cloneairbnb.page.sub.main;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.sarangnamu.cloneairbnb.BkApp;
import net.sarangnamu.cloneairbnb.R;
import net.sarangnamu.cloneairbnb.model.RecentlyData;

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2016. 4. 19.. <p/>
 */
public class RecentlyAdapter extends RecyclerView.Adapter {
    private static final org.slf4j.Logger mLog = org.slf4j.LoggerFactory.getLogger(RecentlyAdapter.class);

    public RecentlyAdapter(Activity activity) {

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_list_item, parent, false);

        return new RecentlyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RecentlyViewHolder vh = (RecentlyViewHolder) holder;
        RecentlyData data = new RecentlyData();

        // DEMO code
        data.title = "TITLE TITLE";
        data.description = "test test test";
        data.price = "\\10,000";
        data.unit = "KRW\n박";

        vh.setData(data);
    }

    @Override
    public int getItemCount() {
        return 1; // db count
    }
}
