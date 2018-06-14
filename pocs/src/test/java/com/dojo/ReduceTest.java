package com.dojo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Optional;

public class ReduceTest {

    Reduce reduce;

    @Before
    public void setUp(){
        reduce = new Reduce();
    }

    @Test
    public void reduceTest(){
        BigDecimal expected = new BigDecimal(2);
        Optional<BigDecimal> result = reduce.sampleReduce(Optional.of(new BigDecimal(1)), Optional.of(new BigDecimal(1)));

        Assert.assertTrue(result.isPresent());
        Assert.assertEquals(expected ,  result.get());
    }

    @Test
    public void reduceEmptyTest(){
        Optional<BigDecimal> result = reduce.sampleReduce(Optional.empty(), Optional.empty());
        Assert.assertFalse(result.isPresent());
    }
}
