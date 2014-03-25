package com.folkol.concurrency;


import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;

import org.junit.Assert;
import org.junit.Test;

public class CountingServletTest {

    private static final int NUMREQUESTS = 1000000;

    @Test
    public void countVisitors() throws Exception {
        final CountingServlet servlet = new CountingServlet();

        ExecutorService workers = Executors.newFixedThreadPool(10);
        for(int i = 0; i < NUMREQUESTS; i++) {
            workers.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        servlet.doGet(null, null);
                    } catch (ServletException | IOException e) {
                    }
                }
            });
        }
        workers.shutdown();
        workers.awaitTermination(10, TimeUnit.SECONDS);

        Assert.assertEquals("Expected counter to match number of requests", NUMREQUESTS, servlet.counter);
    }

}
