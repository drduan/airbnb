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

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import net.sarangnamu.cloneairbnb.R;
import net.sarangnamu.cloneairbnb.model.RecentlyData;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2016. 4. 19.. <p/>
 */
public class RecentlyViewHolder extends RecyclerView.ViewHolder {
    private static final org.slf4j.Logger mLog = org.slf4j.LoggerFactory.getLogger(RecentlyViewHolder.class);

    @Bind(R.id.title) TextView mTitle;
    @Bind(R.id.description) TextView mDescription;
    @Bind(R.id.price) TextView mPrice;
    @Bind(R.id.unit) TextView mUnit;
    @Bind(R.id.favorite) ToggleButton mFavorite;
    @Bind(R.id.image) ImageView mImage;
    @Bind(R.id.seller_image) CircleImageView mCeller;

    public RecentlyViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(itemView);
    }

    public void setData(RecentlyData data) {
        // TODO

        mTitle.setText(data.title);
        mDescription.setText(data.description);
        mPrice.setText(data.price);
        mUnit.setText(data.unit);

        // set images
    }
}
