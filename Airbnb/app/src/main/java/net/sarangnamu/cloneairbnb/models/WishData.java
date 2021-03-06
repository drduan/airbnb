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

package net.sarangnamu.cloneairbnb.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2016. 5. 10.. <p/>
 */
public class WishData extends RealmObject {
    @PrimaryKey
    public long id;

    public String title;
    public String description;

    public String price;
    public String unit;

    public byte[] background;
    public boolean favorite;
    public byte[] seller;
}
