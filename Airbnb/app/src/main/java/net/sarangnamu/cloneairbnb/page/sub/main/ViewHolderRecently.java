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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import net.sarangnamu.cloneairbnb.R;
import net.sarangnamu.cloneairbnb.models.RecentlyData;
import net.sarangnamu.common.v7.BkViewHolder;
import net.sarangnamu.common.v7.IBkAdapterData;

import java.util.Objects;

import butterknife.Bind;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2016. 4. 19.. <p/>
 */
public class ViewHolderRecently extends BkViewHolder<RecentlyData> {
    @Bind(R.id.title) TextView mTitle;
    @Bind(R.id.description) TextView mDescription;
    @Bind(R.id.price) TextView mPrice;
    @Bind(R.id.unit) TextView mUnit;
    @Bind(R.id.favorite) ToggleButton mFavorite;
    @Bind(R.id.image) ImageView mImage;
    @Bind(R.id.seller_image) CircleImageView mCeller;

    public ViewHolderRecently(View parent, @LayoutRes int layoutId, int viewType, IBkAdapterData data) {
        super(parent, layoutId, viewType, data);

        itemView.setOnClickListener(v -> {
            RecentlyData posdata = getAdapterData(getLayoutPosition());
            Toast.makeText(v.getContext(), "recently: " + getLayoutPosition() + ", title : " + posdata.title, Toast.LENGTH_SHORT).show();
        });
    }

    public void setData(RecentlyData data) {
        if (data == null) {
            return ;
        }

        mTitle.setText(data.title);
        mDescription.setText(data.description);
        mPrice.setText(data.price);
        mUnit.setText(data.unit);
    }
}
