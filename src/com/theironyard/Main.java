package com.theironyard;

import spark.ModelAndView;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
	// write your code here
        ArrayList<User> users = new ArrayList();
        Spark.staticFileLocation("/public");
        Spark.init(); //to win it
        Spark.post(
                "/create-account",
                ((request, response) -> {
                    User user = new User();
                    user.name = request.queryParams("username");
                    user.password = request.queryParams("password");
                    users.add(user);
                    response.redirect("/"); //slash represents homepage
                    return "Created account!";
                })
        );//end post
        Spark.get(
                "/accounts",
                ((request, response) -> {
                    HashMap m = new HashMap();
                    m.put("count", users.size());
                    m.put("accounts", users);
                    return new ModelAndView(m, "accounts.html");
                }),
                new MustacheTemplateEngine()
        );







    }//end main
}//end everything