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

import android.support.v4.app.FragmentTransaction;

import net.sarangnamu.common.frgmt.FrgmtManager;

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2016. 5. 3.. <p/>
 */
public class MainPageManager extends FrgmtManager {
    private static MainPageManager mInst;

    public static MainPageManager getInstance() {
        if (mInst == null) {
            mInst = new MainPageManager();
        }

        return mInst;
    }

    private MainPageManager() {

    }

    @Override
    protected void setTransition(FragmentTransaction trans) {
        setSlideTransition(trans);
    }
}
