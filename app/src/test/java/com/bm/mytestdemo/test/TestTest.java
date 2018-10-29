package com.bm.mytestdemo.test;

import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by xiao on 2018/9/17.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class TestTest {

    @Test
    public void tests() throws Exception {
        com.bm.mytestdemo.test.Test test = new com.bm.mytestdemo.test.Test();
        int sum = test.Tests(1,3);
        Assert.assertEquals(8,sum);

        //onView(withText("Say hello!")).perform(click()); //line 2
    }

}