package co.crisi.linkresearcher.test;

import java.util.Date;

import javax.ejb.EJB;
import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.crisi.linkresearcher.ejb.AdministratorEJB;
import co.crisi.linkresearcher.ejb.exceptions.NullRelevantResultException;
import co.crisi.linkresearcher.ejb.exceptions.NullResearchException;
import co.crisi.linkresearcher.ejb.exceptions.NullSearchException;
import co.crisi.linkresearcher.ejb.exceptions.RepeatedRelevantResultException;
import co.crisi.linkresearcher.ejb.exceptions.RepeatedResearchException;
import co.crisi.linkresearcher.entities.Research;
import co.crisi.linkresearcher.entities.Status;

@RunWith(Arquillian.class)
public class BusinessTest {

	@Deployment
	public static Archive<?> createDeploymentPackage() {
		return ShrinkWrap.create(JavaArchive.class).addClass(AdministratorEJB.class)

				.addPackage(Research.class.getPackage())
				.addAsResource("persistenceForTest.xml", "META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");

	}

	@EJB
	private AdministratorEJB adminEJB;

	@Test
	@Transactional(value = TransactionMode.COMMIT)
	public void addResearchTest() {
		try {
			adminEJB.addResearch("Sentiment analysis in social networks");
			System.out.println("ADDED");
		} catch (RepeatedResearchException e) {
			e.printStackTrace();
		}
	}

	@Test
	@Transactional(value = TransactionMode.COMMIT)
	public void removeResearchTest() {
		try {
			adminEJB.removeResearch("Sentiment analysis in social networks");
			System.out.println("REMOVED!");
		} catch (NullResearchException e) {
			e.printStackTrace();
		}
	}

	@Test
	@Transactional(value = TransactionMode.COMMIT)
	public void addSearchTest() {
		adminEJB.addSearch("Depression in social network", "ACM", "DEPRESSION", 165132100);
		System.out.println("ADDED");
	}

	@Test
	@Transactional(value = TransactionMode.COMMIT)
	public void addRelevantResultTest() {
		try {
			adminEJB.addRelevantResult("DEPRESSION", 3, "www.twitter.google.com.co", new Date(), Status.RED);
			System.out.println("ADDED!");
		} catch (RepeatedRelevantResultException e) {
			e.printStackTrace();
		}
	}

	@Test
	@Transactional(value = TransactionMode.COMMIT)
	public void removeSearchTest() {
		try {
			adminEJB.removeSearch("DEPRESSION");
			System.out.println("REMOVED!");
		} catch (NullSearchException e) {
			e.printStackTrace();
		}
	}

	@Test
	@Transactional(value = TransactionMode.COMMIT)
	public void removeRelevantResultTest() {
		try {
			adminEJB.removeRelevantResult("www.twitter.google.com.co");
			System.out.println("REMOVED!");
		} catch (NullRelevantResultException e) {
			e.printStackTrace();
		}
	}

}
