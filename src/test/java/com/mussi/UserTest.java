package com.mussi;

import java.util.List;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class UserTest {

    @When("^create user:$")
    public void createUser(List<User> users) {
        System.out.println("Executing @When");
        users.forEach(System.out::println);
    }

    @Then("^user should exists:$")
    public void userShouldExist(List<User> users) {
        System.out.println("Executing @Then");
        users.forEach(System.out::println);
    }

}
