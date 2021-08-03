package com.usermanagement;

import com.usermanagement.utils.Utilities;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class has mockito test method for all the methods of Utilities class
 */
public class UtilitiesTest {

    /**
     * This test method includes:
     * Testcase1: validation of age with wrong date format of birthdate
     * Testcase2: Validation of age with right format of birthdate
     * Testcase3: validation age with null birthdate
     *
     * @throws ParseException throws exception if date parsing failed
     */
    @Test
    public void testIsAdult() throws ParseException {
        Assert.assertFalse(Utilities.isAdult(new Date()));
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse("11/01/1990");
        Assert.assertTrue(Utilities.isAdult(date));
        date = new SimpleDateFormat("yyyy-MM-dd").parse("1992-09-20");
        Assert.assertTrue(Utilities.isAdult(date));
    }

    /**
     * This test method includes:
     * Testcase1: given country is null
     * Testcase2: Empty string as input
     * Testcase3: Country other than France
     * Testcase4: France as input
     * Testcase5: Case sensitivity check
     */
    @Test
    public void testIsFrench() {
        Assert.assertFalse(Utilities.isFrench(null));
        Assert.assertFalse(Utilities.isFrench(""));
        Assert.assertFalse(Utilities.isFrench("Spain"));
        Assert.assertTrue(Utilities.isFrench("France"));
        Assert.assertTrue(Utilities.isFrench("france"));
    }
}
