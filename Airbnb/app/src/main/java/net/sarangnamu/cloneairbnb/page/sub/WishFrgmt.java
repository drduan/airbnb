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

package net.sarangnamu.cloneairbnb.page.sub;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;

import net.sarangnamu.cloneairbnb.DataManager;
import net.sarangnamu.cloneairbnb.R;
import net.sarangnamu.cloneairbnb.models.MessageData;
import net.sarangnamu.cloneairbnb.models.WishData;
import net.sarangnamu.cloneairbnb.page.EmptyFrgmtBase;
import net.sarangnamu.cloneairbnb.page.sub.wish.ViewHolderWish;
import net.sarangnamu.common.v7.adapter.BkAdapter;
import net.sarangnamu.common.v7.adapter.IBkAdapterData;

import java.util.List;

import butterknife.Bind;

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2016. 3. 21.. <p/>
 */
public class WishFrgmt extends EmptyFrgmtBase {
    private static final org.slf4j.Logger mLog = org.slf4j.LoggerFactory.getLogger(WishFrgmt.class);

    @Bind(R.id.wish_list) RecyclerView mRecycler;

    @Override
    protected void initLayout() {
        super.initLayout();

        initEmptyLayout();
    }

    private void initEmptyLayout() {
        DataManager.getInstance().initWishData();

        int size = DataManager.getInstance().getItemCount("ViewHolderWish");
        if (size == 0) {
            showEmptyLayout(getResources().getStringArray(R.array.wish_empty), R.drawable.test2);
            return ;
        } else {
            initRecyclerView();
        }
    }

    private void initRecyclerView() {
        mRecycler.setVisibility(View.VISIBLE);
        mRecycler.setAdapter(new BkAdapter<ViewHolderWish>(R.layout.wish_list_row) {
            @Override
            protected IBkAdapterData getAdapterData() {
                return DataManager.getInstance();
            }
        });
    }
}
