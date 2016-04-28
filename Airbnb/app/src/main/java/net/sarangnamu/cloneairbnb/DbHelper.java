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

import net.sarangnamu.cloneairbnb.models.FamousData;
import net.sarangnamu.cloneairbnb.models.RecentlyData;
import net.sarangnamu.cloneairbnb.models.RecommandationData;
import net.sarangnamu.common.v7.IBkAdapterData;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2016. 3. 25.. <p/>
 */
public class DbHelper implements IBkAdapterData {
    private static final org.slf4j.Logger mLog = org.slf4j.LoggerFactory.getLogger(DbHelper.class);

    private static DbHelper mInst;

    public static DbHelper getInstance() {
        if (mInst == null) {
            mInst = new DbHelper();
        }

        return mInst;
    }

    private DbHelper() {

    }

    public Realm get() {
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(BkApp.context()).build();
        return Realm.getInstance(realmConfig);
    }

    public List<RecentlyData> getRecentlyAll() {
        return get().where(RecentlyData.class).findAll();
    }

    public void putRecently(RecentlyData data) {
        Realm db = get();

        db.beginTransaction();
        db.copyFromRealm(data);
        db.commitTransaction();
    }

    @Override
    public int getItemCount(String name) {
        mLog.debug("view holder name : " + name);
        return 3;
    }

    @Override
    public Object getData(String name, int pos) {
        if ("ViewHolderFamous".equals(name)) {
            FamousData data = new FamousData();
            data.title = name + " title";
            data.description = "description";

            return data;
        } else if ("ViewHolderRecently".equals(name)) {
            RecentlyData data = new RecentlyData();
            data.title = name + " title";
            data.description = "description";
            data.price = "100";
            data.unit = "$\n박";

            return data;
        } else if ("ViewHolderRecommandation".equals(name)) {
            RecommandationData data = new RecommandationData();
            data.title = name = " title";
            data.description = "description";

            return data;
        }

        return null;
    }
}
