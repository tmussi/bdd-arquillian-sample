package com.mussi.sample.service;

import java.util.List;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.runner.RunWith;

import com.mussi.sample.entity.User;

import cucumber.api.CucumberOptions;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.arquillian.CukeSpace;
import cucumber.runtime.arquillian.api.Features;

@RunWith(CukeSpace.class)
@Features({ "src/test/resources/com/mussi/service/feature/User.feature" })
@CucumberOptions(plugin = { "pretty", "html:target/cucumber_report_user" })
public class UserFeature {

    @EJB
    UserService userEJB;

    @Deployment
    public static JavaArchive createDeployment() {
        JavaArchive jar = ShrinkWrap.create(JavaArchive.class).addClasses(User.class, UserService.class)
                .addAsResource("persistence.xml", "META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
        System.out.println(jar.toString(true));
        return jar;
    }

    @Before
    public void somethingBeforeScenario() {
        System.out.println("something here..");
    }

    // GIVEN steps are used to describe the initial context of the system - the
    // scene of the scenario. It is typically something that happened in the past.
    // I prefer to avoid using business layers to be able to simulate all type of
    // situations (expected and unexpected). For example:
    // @PersistenceContext
    // private EntityManager em;
    //
    // @Given("^that exists user:$")
    // public void thatExistsUser(List<User> users) {
    //     users.forEach(em::persist);
    // }

    // WHEN steps are used to describe an event, or an action. This can be a person
    // interacting with the system, or it can be an event triggered by another
    // system.
    @When("^create user:$")
    public void createUser(List<User> users) {
        userEJB.saveAllUsers(users);
    }

    // THEN steps are used to describe an expected outcome, or result.
    @Then("^user should exists:$")
    public void userShouldExist(List<User> usersExpected) {
        for (User userExpected : usersExpected) {
            User userDB = userEJB.findUser(userExpected);
            Assert.assertNotNull(userDB);
            Assert.assertEquals(userExpected.getName(), userDB.getName());
            Assert.assertEquals(userExpected.getEmail(), userDB.getEmail());
        }
    }

}
