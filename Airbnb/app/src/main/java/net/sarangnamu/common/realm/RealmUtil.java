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

package net.sarangnamu.common.realm;

import io.realm.Realm;
import io.realm.RealmObject;

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2016. 4. 29.. <p/>
 *
 * @see http://sys1yagi.hatenablog.com/entry/2015/08/14/131226
 */
public class RealmUtil {
    private static final String ID = "id";
    
    public static long newId(Realm realm, Class<? extends RealmObject> clazz) {
        return newIdWithIdName(realm, clazz, ID);
    }

    public static long newIdWithIdName(Realm realm, Class<? extends RealmObject> clazz, String idName) {
        try {
            return realm.where(clazz).max(idName).longValue() + 1;
        } catch (NullPointerException e) {
            return 1;
        }
    }
}
