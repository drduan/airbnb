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

import net.sarangnamu.cloneairbnb.BkApp;
import net.sarangnamu.cloneairbnb.MainActivity;
import net.sarangnamu.common.InflateFrgmtBase;
import net.sarangnamu.common.ui.LpInst;

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2016. 5. 3.. <p/>
 */
public class MainListDetailFrgmt extends InflateFrgmtBase {
    private static final org.slf4j.Logger mLog = org.slf4j.LoggerFactory.getLogger(MainListDetailFrgmt.class);

    @Override
    protected void initLayout() {
        // 알 수 없는 이유로 view 의 size 가 wrap_content 형태로 동작 함

        mBaseView.setLayoutParams(LpInst.frame(BkApp.screenX(), BkApp.screenY()));

        // hide tab menu
//        ((MainActivity) getActivity()).setVisibleTabMenu(false);
    }

    @Override
    protected String getPrefixForPage() {
        return "";
    }
}
