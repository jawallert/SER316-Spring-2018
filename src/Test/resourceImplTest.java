package Test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.java.memoranda.Project;
import main.java.memoranda.ResourcesListImpl;
import main.java.memoranda.util.FileStorage;
import main.java.memoranda.util.Util;
import nu.xom.Document;
import nu.xom.Element;

public class resourceImplTest {
	Project testProj;
	//Element temp = new Element("test");
	//Document testDoc = new Document(temp);
	ResourcesListImpl testRes = new ResourcesListImpl(testProj);
	Element temp;
	Document testDoc;
	ResourcesListImpl testRes2;
	String path;
	String path2;
	String path3;
	
	
	@Before
	public void setUp() throws Exception {
		temp = new Element("test");
		testDoc = new Document(temp);
		testRes2 = new ResourcesListImpl(testDoc, testProj);
		path = Util.getEnvDir();
		path2 = path.concat("moretest");
		path3 = path.concat("internet");
	}

	@After
	public void tearDown() throws Exception {
		path = null;
		path2 = null;
		path3 = null;
		temp = null;
		testDoc = null;
		testRes2 = null;
	}
	
	/**
	 * Tests adding a resource, as well as getting said
	 * resource and verifying it exists in the project, if isInternet
	 */
	@Test
	public void testAddResource() {
		testRes.addResource(path);
		testRes.addResource(path2, false, false);
		testRes.addResource(path3, true, false);
		assertTrue(testRes.getResource(path).getPath().equals(path));
		assertTrue(testRes.getResource(path2).getPath().equals(path2));
		assertTrue(testRes.getResource(path3).isInetShortcut());
	}
	
	/**
	 * testGetAll checks that the size of the resources 
	 * is zero with nothing and then not zero after something is added
	 */
	@Test
	public void testGetAll() {
		assertFalse(testRes.getAllResources().size() > 0);
		testRes.addResource(path);
		assertTrue(testRes.getAllResources().size() > 0);
	}
	
	@Test
	public void testGetAllCount() {
		testRes.addResource(path);
		testRes.addResource(path2, false, true);
		testRes.addResource(path, true, false);
		assertTrue(testRes.getAllResourcesCount() == 3);
	}
	
	/**
	 * testRemoveResource checks both removeResource and getAllResourcesCount
	 * to verify that the resource is removed from the resource list.
	 */
	@Test
	public void testRemoveResource() {
		testRes.addResource(path);
		assertTrue(testRes.getAllResourcesCount() == 1);
		testRes.removeResource(path);
		assertFalse(testRes.getAllResourcesCount() >= 1);
	}
	/**
	 * testRemoveProject verifies the project conditional branch of removeResource
	 */
	@Test(expected = NullPointerException.class)
	public void testRemoveProject() {
		testRes.addResource(path2, false, true);
		assertTrue(testRes.getResource(path2).isProjectFile());
		testRes.removeResource(path2);
		testRes.getResource(path2).getPath();
	}
	
	/**
	 * testGetXml verifies the getXMLContent
	 */
	@Test
	public void testGetXml() {
	assertTrue(testRes.getXMLContent() != null);
	}
	
}
