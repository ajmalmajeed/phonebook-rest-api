package com.ajmal.phonebook.controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class HomeController extends Controller {

    public Result index() {
        return ok("<html>\n" +
                "<head>\n" +
                "<title>Phonebook API</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<h1>Phonebook API Overview</h1>\n" +
                "<p>The Developerʼs API Documentation web site is where you will ﬁnd all updates, usage guidelines, " +
                "sample code and setup instructions for this service. All questions regarding this service will be handled" +
                " by Amila Nuwan.</p>\n" +
                "\n" +
                "</body>\n" +
                "</html>").as("text/html");
    }
}
