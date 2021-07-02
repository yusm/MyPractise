package com.example.yusm.mypractise;

import org.junit.Test;

import static org.junit.Assert.*;
/*
 *
 * Date: 2019/7/8
 * Desc：
 */

public class AidlClientActivityTest {

    @Test
    public void sum() {
        assertEquals(AidlClientActivity.sum(2,2),4);
    }
}