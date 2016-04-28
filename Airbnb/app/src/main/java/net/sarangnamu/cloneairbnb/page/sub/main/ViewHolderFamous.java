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

import android.support.annotation.LayoutRes;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import net.sarangnamu.cloneairbnb.R;
import net.sarangnamu.cloneairbnb.models.FamousData;
import net.sarangnamu.cloneairbnb.models.RecentlyData;
import net.sarangnamu.common.v7.BkViewHolder;
import net.sarangnamu.common.v7.IBkAdapterData;

import butterknife.Bind;

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2016. 4. 25.. <p/>
 */
public class ViewHolderFamous extends BkViewHolder<FamousData> {
    @Bind(R.id.title) TextView mTitle;
    @Bind(R.id.description) TextView mDescription;

    public ViewHolderFamous(View parent, @LayoutRes int layoutId, int viewType, IBkAdapterData data) {
        super(parent, layoutId, viewType, data);

        itemView.setOnClickListener(v -> {
            FamousData posdata = getAdapterData(getLayoutPosition());
            Toast.makeText(v.getContext(), "recently: " + getLayoutPosition() + ", title : " + posdata.title, Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void setData(FamousData data) {
        if (data == null) {
            return ;
        }

        mTitle.setText(data.title);
        mDescription.setText(data.description);
    }
}
