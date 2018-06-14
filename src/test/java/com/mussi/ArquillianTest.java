package com.mussi;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class ArquillianTest {

    @PersistenceContext
    private EntityManager em;

    @Deployment
    public static JavaArchive createDeployment() {
        JavaArchive jar = ShrinkWrap.create(JavaArchive.class).addClasses(User.class)
                .addAsResource("persistence.xml", "META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
        System.out.println(jar.toString(true));
        return jar;
    }
    
    @Test
    public void nothing() throws NotSupportedException, SystemException {
        Integer usersOnDatabase = em.createQuery("FROM User u").getResultList().size();
        System.out.println("Users on derby database: " + usersOnDatabase);
    }
}
