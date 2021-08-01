package com.usermanagement;

import com.usermanagement.utils.Utilities;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilitiesTest {

    @Test
    public void testIsAdult() throws ParseException {
        Assert.assertFalse(Utilities.isAdult(new Date()));
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse("11/01/1990");
        Assert.assertTrue(Utilities.isAdult(date));
        date = new SimpleDateFormat("yyyy-MM-dd").parse("1992-09-20");
        Assert.assertTrue(Utilities.isAdult(date));
    }

    @Test
    public void testIsFrench() {
        Assert.assertFalse(Utilities.isFrench("Spain"));
        Assert.assertTrue(Utilities.isFrench("France"));
        Assert.assertTrue(Utilities.isFrench("france"));
    }
}
