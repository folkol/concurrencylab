package com.folkol.concurrency;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CountingServlet extends HttpServlet {

    public int counter;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        incrementCounter();
    }

    private void incrementCounter() {
        counter++;
    }

}
