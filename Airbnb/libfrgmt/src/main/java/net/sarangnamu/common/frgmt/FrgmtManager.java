/*
 * FrgmtManager.java
 * Copyright 2013 Burke Choi All rights reserved.
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
package net.sarangnamu.common.frgmt;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.BackStackEntry;
import android.support.v4.app.FragmentTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 * {@code
 *     public class Navigator extends FrgmtManager {
 *         private static Navigator inst;
 * 
 *         public static Navigator getInstance(FragmentActivity act) {
 *             if (inst == null) {
 *                 inst = new Navigator();
 *             }
 * 
 *             inst.setFragmentManager(act);
 * 
 *             return inst;
 *         }
 * 
 *         private Navigator() {
 * 
 *         }
 *     }
 * 
 *     - example
 *     Navigator nv = Navigator.getInstance(this);
 *     nv.setBase(HomeFrgmt.class);
 * 
 *     - change page
 *     nv.replace(OtherFrgmt.class);
 * }
 * </pre>
 * 
 * @author <a href="mailto:aucd29@gmail.com">Burke Choi</a>
 */
public abstract class FrgmtManager {
    private static final Logger mLog = LoggerFactory.getLogger(FrgmtManager.class);

    protected FragmentManager mFrgmtManager;

    public FrgmtManager() {
    }

    public void setFragmentManager(FragmentActivity act) {
        if (act == null) {
            mLog.error("setFragmentManager act is null");
            return;
        }

        mFrgmtManager = act.getSupportFragmentManager();
    }

    public void setFragmentManager(Fragment frgmt) {
        if (frgmt == null) {
            mLog.error("setFragmentManager frgmt is null");
            return;
        }

        mFrgmtManager = frgmt.getChildFragmentManager();
    }

    public void add(int id, Class<?> cls) {
        add(id, cls, false);
    }

    public void add(int id, Class<?> cls, boolean transition) {
        try {
            Fragment frgmt = (Fragment) cls.newInstance();
            if (frgmt == null) {
                mLog.error("setBase frgmt == null");
                return;
            }

            FragmentTransaction trans = mFrgmtManager.beginTransaction();
            if (frgmt.isVisible()) {
                return;
            }

            if (transition) {
                setTransition(trans);
            }

            trans.add(id, frgmt, frgmt.getClass().getName());
            trans.commit();
        } catch (Exception e) {
            mLog.error(e.getMessage());
        }
    }

    public void resetAdd(int id, Class<?> cls) {
        replace(id, cls, false, null);
    }

    public Fragment replace(int id, Class<?> cls) {
        return replace(id, cls, true, null);
    }

    public Fragment replace(int id, Class<?> cls, Bundle bundle) {
        return replace(id, cls, true, bundle);
    }

    private Fragment replace(int id, Class<?> cls, boolean stack, Bundle bundle) {
        try {
            Fragment frgmt = mFrgmtManager.findFragmentByTag(cls.getName());
            FragmentTransaction trans = mFrgmtManager.beginTransaction();

            if (frgmt != null && frgmt.isVisible()) {
                return frgmt;
            }

            frgmt = (Fragment) cls.newInstance();
            if (frgmt == null) {
                mLog.error("replace frgmt == null");
                return null;
            }

            if (bundle != null) {
                frgmt.setArguments(bundle);
            }

            if (stack) {
                setTransition(trans);
            }

            trans.replace(id, frgmt, frgmt.getClass().getName());

            if (stack) {
                trans.addToBackStack(frgmt.getClass().getName());
            }

            trans.commit();

            return frgmt;
        } catch (Exception e) {
            mLog.error(e.getMessage());
        }

        return null;
    }

    public void popBack() {
        if (mFrgmtManager != null) {
            mFrgmtManager.popBackStack();
        }
    }

    public void popBackAll() {
        if (mFrgmtManager == null) {
            mLog.error("setFragmentManager fm is null");
            return;
        }

        int count = mFrgmtManager.getBackStackEntryCount();
        for (int i = 0; i < count; ++i) {
            mFrgmtManager.popBackStack(i, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

    protected void setTransition(FragmentTransaction trans) {
        // TODO
//        trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
    }

    protected void setSlideTransition(FragmentTransaction trans) {
        trans.setCustomAnimations(R.anim.slide_in_current, R.anim.slide_in_next,
                R.anim.slide_out_current, R.anim.slide_out_prev);
    }

    protected void setUpTransition(FragmentTransaction trans) {
        trans.setCustomAnimations(R.anim.slide_up_current, R.anim.slide_up_next,
                R.anim.slide_down_current, R.anim.slide_down_prev);
    }

    protected void setHalfSlideTransition(FragmentTransaction trans) {
        trans.setCustomAnimations(R.anim.slide_in_current_half, 0,
                0, 0);
    }

    public Fragment getCurrentFragment() {
        if (mFrgmtManager == null) {
            return null;
        }

        int count = mFrgmtManager.getBackStackEntryCount();
        if (count > 0) {
            BackStackEntry frgmt = mFrgmtManager.getBackStackEntryAt(count - 1);
            return mFrgmtManager.findFragmentByTag(frgmt.getName());
        }

        return null;
    }

    public Fragment getFragment(Class<?> cls) {
        if (mFrgmtManager == null) {
            return null;
        }

        return mFrgmtManager.findFragmentByTag(cls.getName());
    }

    public int getChildCount() {
        if (mFrgmtManager == null) {
            return 0;
        }

        return mFrgmtManager.getBackStackEntryCount();
    }
}
