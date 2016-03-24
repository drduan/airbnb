/*
 * AutoInflateFrgmtBase.java
 * Copyright 2014 Burke.Choi All rights reserved.
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
package net.sarangnamu.common;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AutoInflateFrgmtBase extends InflateFrgmtBase {
    private static final Logger mLog = LoggerFactory.getLogger(AutoInflateFrgmtBase.class);

    private static final String PREFIX_MENU = "mnu_";
    private static final String IDENTIFIER_STRING = "string";

    protected View mView;
    protected ViewGroup mPageContent; // /< child view

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBaseView = (ViewGroup) inflate(getLayoutId());

        autoInflate();
        initLayout();

        return mBaseView;
    }

    private void autoInflate() {
        int id = getLayoutIdentifier();

        // base <- pageContent <- other View (class_name.xml)
        mPageContent = (ViewGroup) mBaseView.findViewById(getPageContentId());

        if (id != 0) {
            mView = inflate(id);
            mPageContent.addView(mView);
        } else {
            mLog.error("initLayout, not found layout id for pageContent");
        }
    }

    protected int getStringTitleIdentifier() {
        return getResources().getIdentifier(getPrefixForMenu() + getClassSimpleName(),
                IDENTIFIER_STRING, getActivity().getPackageName());
    }

    protected int getLayoutIdentifier() {
        String layoutFileName = getPrefixForPage() + getClassSimpleName();

        if (mLog.isDebugEnabled()) {
            mLog.debug("load : " + layoutFileName);
        }

        return getResources().getIdentifier(layoutFileName, IDENTIFIER_LAYOUT, getActivity().getPackageName());
    }

    protected String getPrefixForMenu() {
        return PREFIX_MENU;
    }

    // //////////////////////////////////////////////////////////////////////////////////
    //
    // ABSTRACT
    //
    // //////////////////////////////////////////////////////////////////////////////////

    protected abstract int getPageContentId();
}
