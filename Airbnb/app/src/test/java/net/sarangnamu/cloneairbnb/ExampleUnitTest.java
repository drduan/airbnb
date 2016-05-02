package net.sarangnamu.cloneairbnb;

import android.content.Context;
import android.test.InstrumentationTestCase;
import android.util.Log;

import net.sarangnamu.cloneairbnb.models.RecentlyData;

import org.hamcrest.core.DescribedAs;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
@RunWith(MockitoJUnitRunner.class)
public class ExampleUnitTest {
    private static final String TAG = "ExampleUnitTest";

    @Mock
    Context mMockContext;

    @Test
    public void realmInsert() throws Exception {
        DataManager manager = DataManager.getInstance();
        manager.setContext(mMockContext);

        RecentlyData data = new RecentlyData();
        data.title = "test title";
        data.description = "test description";
        data.price = "3333";
        data.unit = "$\nDay";

        manager.putRecently(data);
        List<RecentlyData> recentlyList = manager.getRecentlyAll();

        assertEquals(1, recentlyList);

        RecentlyData dbdata = recentlyList.get(0);

        Log.d(TAG, "===================================================================");
        Log.d(TAG, "RECENTLY");
        Log.d(TAG, "===================================================================");
        Log.d(TAG, "ID          : " + dbdata.id);
        Log.d(TAG, "TITLE       : " + dbdata.title);
        Log.d(TAG, "DESCRIPTION : " + dbdata.description);
        Log.d(TAG, "PRICE       : " + dbdata.price);
        Log.d(TAG, "UNIT        : " + dbdata.unit);
    }
}