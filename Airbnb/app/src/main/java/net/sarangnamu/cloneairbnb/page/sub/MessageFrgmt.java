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

package net.sarangnamu.cloneairbnb.page.sub;

import net.sarangnamu.cloneairbnb.DataManager;
import net.sarangnamu.cloneairbnb.R;
import net.sarangnamu.cloneairbnb.models.MessageData;
import net.sarangnamu.cloneairbnb.page.EmptyFrgmtBase;

import java.util.List;

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2016. 3. 21.. <p/>
 */
public class MessageFrgmt extends EmptyFrgmtBase {
    private static final org.slf4j.Logger mLog = org.slf4j.LoggerFactory.getLogger(MessageFrgmt.class);

    @Override
    protected void initLayout() {
        super.initLayout();

        List<MessageData> dataList = DataManager.getInstance().getMessageList();
        if (dataList.size() == 0) {
            showEmptyLayout(getResources().getStringArray(R.array.message_empty), R.drawable.test3);
            return ;
        }

        // instance message layout
    }
}
