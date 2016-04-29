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

package net.sarangnamu.common.net;

import net.sarangnamu.common.json.JsonTool;

import java.io.IOException;

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2016. 4. 28.. <p/>
 */
public abstract class IBkNetHelper<CT> {
    protected CT mClient;

    protected abstract void initClient();
    public abstract CT getClient();
    public abstract String get(String url);
    public abstract String post(String url);

    public <T> T get(String url, Class clazz) {
        String res = get(url);
        if (res == null) {
            return null;
        }

        try {
            return JsonTool.unmarshalling(res, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public <T> T post(String url, Class clazz) {
        String res = post(url);
        if (res == null) {
            return null;
        }

        try {
            return JsonTool.unmarshalling(res, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
