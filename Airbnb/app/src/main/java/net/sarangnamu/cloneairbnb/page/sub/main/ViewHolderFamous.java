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

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.widget.TextView;

import net.sarangnamu.cloneairbnb.R;
import net.sarangnamu.cloneairbnb.net.resource.Famous;
import net.sarangnamu.common.v7.BkViewHolder;
import net.sarangnamu.common.v7.IBkAdapterData;

import butterknife.Bind;

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2016. 4. 25.. <p/>
 */
public class ViewHolderFamous extends BkViewHolder<Famous> {
    @Bind(R.id.title) TextView mTitle;
    @Bind(R.id.description) TextView mDescription;

    public ViewHolderFamous(View parent, @LayoutRes int layoutId, int viewType, IBkAdapterData data) {
        super(parent, layoutId, viewType, data);

        itemView.setOnClickListener(v -> {
            Famous posdata = getAdapterData(getLayoutPosition());

            Bundle bundle = new Bundle();

            MainPageManager.getInstance().replace(R.id.content_main, MainListDetailFrgmt.class, bundle);
        });
    }

    @Override
    public void setData(Famous data) {
        if (data == null) {
            return ;
        }

        mTitle.setText(data.getTitle());
        mDescription.setText(data.getDescription());
    }
}
