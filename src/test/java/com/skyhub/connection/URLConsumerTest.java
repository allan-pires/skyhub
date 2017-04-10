package com.skyhub.connection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by allan on 09/04/17.
 */
public class URLConsumerTest {

    private URLConsumer consumer;

    @Before
    public void setup(){
        consumer = new URLConsumer();
    }

    @Test
    public void consumeReturnsText() throws Exception {
        String text = consumer.getTextFromURL("http://54.152.221.29/images.json");

        Assert.assertNotNull(text);
        Assert.assertTrue(text.contains("images"));
    }


    @Test
    public void consumeReturnsEmptyString() throws Exception{
        String text = consumer.getTextFromURL("Some bad url");

        Assert.assertNotNull(text);
        Assert.assertEquals("", text);
    }
}