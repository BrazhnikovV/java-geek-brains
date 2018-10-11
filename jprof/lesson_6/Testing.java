package jprof.lesson_6;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.lang.*;
import java.util.Arrays;
import java.util.Collection;

public class Testing {

    private ArrayWorker arrayWorker;

    private static int[] arrOne = {1, 2, 4, 4, 2, 3, 4, 1, 7};

    private static int[] arrTwo = {1, 1, 4, 4, 1, 4, 4, 1, 4};

    @Before
    public void startTest() {
        this.arrayWorker = new ArrayWorker();
    }

    @Test
    public void testGetPartArrayAfterRequiredNumber() {
        Assert.assertArrayEquals(
            new int[]{4, 1, 7},
            this.arrayWorker.getPartArrayAfterRequiredNumber( arrOne,4 )
        );

        Assert.assertNotNull( this.arrayWorker.getPartArrayAfterRequiredNumber( arrOne,4 ) );

        Assert.assertEquals(
            true,
            this.arrayWorker.getPartArrayAfterRequiredNumber(arrOne, 4).getClass().isArray()
        );
    }

    @Test
    public void testCheckArrayFromOneFourNumbers() {
        Assert.assertEquals( false, this.arrayWorker.checkArrayFromOneFourNumbers( arrOne ) );
        Assert.assertTrue( this.arrayWorker.checkArrayFromOneFourNumbers( arrTwo ) );
    }

    /*
    @Test
    public void massTestAdd() {
        Assert.assertEquals(b, this.arrayWorker.getPartArrayAfterRequiredNumber( arrOne, a));
    }
    */
}
