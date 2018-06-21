package com.mussi;

import java.util.List;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.arquillian.CukeSpace;
import cucumber.runtime.arquillian.api.Features;

@RunWith(CukeSpace.class)
@Features({ "src/test/resources/com/mussi/UserFeature.feature" })
@CucumberOptions(plugin = { "pretty", "html:target/cucumber_report" })
public class UserServiceTest {

    @Inject
    UserService userService;

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

    @When("^create user:$")
    public void createUser(List<User> users) {
        // users.forEach(userService::saveUser);
        userService.saveAllUsers(users);
    }

    @Then("^user should exists:$")
    public void userShouldExist(List<User> users) {
        for (User user : users) {
            User userDB = userService.findUser(user);
            Assert.assertNotNull(userDB);
            Assert.assertEquals(user.getName(), userDB.getName());
            Assert.assertEquals(user.getEmail(), userDB.getEmail());
            Assert.assertEquals(user.getPassword(), userDB.getPassword());
        }
    }

}
