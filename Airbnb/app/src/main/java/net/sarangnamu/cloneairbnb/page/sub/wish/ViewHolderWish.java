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

package net.sarangnamu.cloneairbnb.page.sub.wish;

import android.support.annotation.LayoutRes;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import net.sarangnamu.cloneairbnb.R;
import net.sarangnamu.cloneairbnb.models.WishData;
import net.sarangnamu.common.v7.BkViewHolder;
import net.sarangnamu.common.v7.IBkAdapterData;

import butterknife.Bind;

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2016. 5. 11.. <p/>
 */
public class ViewHolderWish extends BkViewHolder<WishData> {
    private static final org.slf4j.Logger mLog = org.slf4j.LoggerFactory.getLogger(ViewHolderWish.class);

    @Bind(R.id.pager) ViewPager mPager;

    public ViewHolderWish(View parent, @LayoutRes int layoutId, int viewType, IBkAdapterData data) {
        super(parent, layoutId, viewType, data);
    }

    @Override
    public void setData(WishData data) {

    }

    ////////////////////////////////////////////////////////////////////////////////////
    //
    // WishPagerAdapter
    //
    ////////////////////////////////////////////////////////////////////////////////////

//    class WishPagerAdapter extends PagerAdapter {
//        @Override
//        public int getCount() {
//            return 2;
//        }
//
//        @Override
//        public Object instantiateItem(ViewGroup container, int position) {
//            switch (position) {
//                case 0:
//                    LayoutInflater.from(getContext()).inflate(R.layout., null);
//                    break;
//                case 1:
//                    break;
//            }
//
//            return super.instantiateItem(container, position);
//        }
//
//        @Override
//        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
//        }
//
//        @Override
//        public boolean isViewFromObject(View view, Object object) {
//            return false;
//        }
//    }
}
