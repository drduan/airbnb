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

import android.content.Context;

import net.sarangnamu.cloneairbnb.models.MessageData;
import net.sarangnamu.cloneairbnb.models.RecentlyData;
import net.sarangnamu.cloneairbnb.models.WishData;
import net.sarangnamu.cloneairbnb.net.domain.MainResponse;
import net.sarangnamu.common.BkApp;
import net.sarangnamu.common.v7.adapter.IBkAdapterData;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2016. 3. 25.. <p/>
 */
public class DataManager implements IBkAdapterData {
    private static final org.slf4j.Logger mLog = org.slf4j.LoggerFactory.getLogger(DataManager.class);

    private static final String VH_FAMOUS         = "ViewHolderFamous";
    private static final String VH_RECENTLY       = "ViewHolderRecently";
    private static final String VH_RECOMMANDATION = "ViewHolderRecommandation";
    private static final String VH_WISH           = "ViewHolderWish";

    private static final int MAIN_MAX_FAMOUS_COUNT = 4;

    private static DataManager mInst;

    private RealmConfiguration mRealmConfig;
    private List<RecentlyData> mRecentlyDataList;
    private List<WishData> mWishDataList;
    private List<MessageData> mMessageDataList;

    private MainResponse mMainResponse;

    private Context mContext;

    public static DataManager getInstance() {
        if (mInst == null) {
            mInst = new DataManager();
        }

        return mInst;
    }

    private DataManager() {
        if (mRealmConfig != null) {
            Realm.deleteRealm(mRealmConfig);
            mRealmConfig = null;
        }
    }

    // for testcase
    public void setContext(Context context) {
        mContext = context;
    }

    public Realm get() {
        if (mContext == null) {
            mContext = BkApp.context();
        }

        if (mRealmConfig == null) {
            mRealmConfig = new RealmConfiguration.Builder(BkApp.context()).build();
        }

        return Realm.getInstance(mRealmConfig);
    }

    public void putData(RealmObject data) {
        Realm db = get();

        db.beginTransaction();
        db.copyFromRealm(data);
        db.commitTransaction();
    }

    public void closeRealm() {
        get().close();
    }

    ////////////////////////////////////////////////////////////////////////////////////
    //
    // RECENTLY
    //
    ////////////////////////////////////////////////////////////////////////////////////

    public List<RecentlyData> getRecentlyList() {
        return get().where(RecentlyData.class).findAll();
    }

    public void initRecentlyData() {
        mRecentlyDataList = getRecentlyList();
    }

    public RecentlyData getRecentlyDumyData() {
        RecentlyData data = new RecentlyData();
        data.id          = 0; //RealmUtil.newId(DataManager.getInstance().get(), RecentlyData.class);
        data.title       = "dumy title";
        data.description = "dumy description";
        data.unit        = "$\nday";
        data.price       = "100";

        return data;
    }

    ////////////////////////////////////////////////////////////////////////////////////
    //
    // MESSAGE
    //
    ////////////////////////////////////////////////////////////////////////////////////

    public List<MessageData> getMessageList() {
        return get().where(MessageData.class).findAll();
    }

    public void initMessageData() {
        mMessageDataList = getMessageList();
    }

    ////////////////////////////////////////////////////////////////////////////////////
    //
    // WISH
    //
    ////////////////////////////////////////////////////////////////////////////////////

    public List<WishData> getWishList() {
        return get().where(WishData.class).findAll();
    }

    public void initWishData() {
        mWishDataList = getWishList();
    }

    ////////////////////////////////////////////////////////////////////////////////////
    //
    // ADAPTER
    //
    ////////////////////////////////////////////////////////////////////////////////////

    @Override
    public int getItemCount(String name) {
        if (mLog.isDebugEnabled()) {
            mLog.debug("view holder name : " + name);
        }

        if (VH_RECENTLY.equals(name)) {
            if (mRecentlyDataList == null) {
                return 0;
            }

            int size = mRecentlyDataList.size();
            if (size == 0) {
                return 1;
            }

            return size;
        } else if (VH_RECOMMANDATION.equals(name)) {
            if (mMainResponse == null) {
                return 0;
            }

            return mMainResponse.getRecommandationList().size();
        } else if (VH_FAMOUS.equals(name)) {
            if (mMainResponse == null) {
                return 0;
            }

            int size = mMainResponse.getFamousList().size();
            if (size > MAIN_MAX_FAMOUS_COUNT) {
                return MAIN_MAX_FAMOUS_COUNT;
            }

            return size;
        } else if (VH_WISH.equals(name)) {
            if (mWishDataList == null) {
                return 0;
            }

            return mWishDataList.size();
        }

        return 0;
    }

    @Override
    public Object getData(String name, int pos) {
        if (VH_RECENTLY.equals(name)) {
            if (mRecentlyDataList == null) {
                return null;
            }

            if (mRecentlyDataList.size() == 0) {
                return getRecentlyDumyData();
            }

            return mRecentlyDataList.get(pos);
        } else if (VH_RECOMMANDATION.equals(name)) {
            if (mMainResponse == null) {
                return null;
            }

            return mMainResponse.getRecommandationList().get(pos);
        } else if (VH_FAMOUS.equals(name)) {
            if (mMainResponse == null) {
                return null;
            }

            return mMainResponse.getFamousList().get(pos);
        }

        return null;
    }

    public MainResponse getMainResponse() {
        return mMainResponse;
    }

    public void setMainResponse(MainResponse response) {
        mMainResponse = response;
    }
}
