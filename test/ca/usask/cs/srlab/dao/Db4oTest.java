package ca.usask.cs.srlab.dao;

import java.io.File;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.usask.cs.srlab.FileUtil;
import ca.usask.cs.srlab.dao.db.config.Db4oConfiguration;
import ca.usask.cs.srlab.dao.db.config.IDbEngineConfiguration;
import ca.usask.cs.srlab.dao.db.engine.Db4oEngine;
import ca.usask.cs.srlab.dao.db.engine.IDbEngine;
import ca.usask.cs.srlab.dao.model.Person;


public class Db4oTest {

	static IDbEngineConfiguration dbConfig;
	static IDbEngine dbEngine;
	static Dao dao;
	static String testDbFolderName;
	
	@BeforeClass
	public static void setupDb(){
		
		String systemRoot = new File(ClassLoader.getSystemClassLoader().getResource(".").getFile()).getParent();
		testDbFolderName = systemRoot.concat(System.getProperty("file.separator") + "db/db4o");

		File dbFolder = new File(testDbFolderName);
		if (dbFolder.exists()) {
			FileUtil.deleteDirectory(testDbFolderName);
		}
		dbFolder.mkdir();

		String dbFileName = testDbFolderName.concat(System
				.getProperty("file.separator") + "default.db4o");
		dbConfig = new Db4oConfiguration(dbFileName);
		dbEngine = new Db4oEngine(dbConfig).openDB();
		dao = new Dao(dbEngine);
	}
	
	@Before
	public void cleanupDb(){
		dao.deleteAll(Person.class);
	}
	
	@Test
	public void saveObject(){
		Person person1 = new Person(1, "Sharif", "Uddin");
		Person person2 = new Person(2, "Rony", "Chowdhury");
		
		dao.save(person1);
		dao.save(person2);
		
		List result = dao.getAll(Person.class);
		
		Assert.assertEquals(result.size(), 2);
	}
	
	@Test
	public void getObject(){
		Person person = new Person(1, "Sharif", "Uddin");
		dao.save(person);
		
		Person personX = (Person) dao.get(Person.class, "id", 1);
		Assert.assertEquals(person, personX);
	}
	
	@Test
	public void deleteObject(){
		Person person1 = new Person(1, "Sharif", "Uddin");
		Person person2 = new Person(2, "Rony", "Chowdhury");
		
		dao.save(person1);
		dao.save(person2);
		
		List result = dao.getAll(Person.class);
		Assert.assertEquals(result.size(), 2);
		
		dao.delete(person1);
		
		result = dao.getAll(Person.class);
		Assert.assertEquals(result.size(), 1);
		
		Person personX = (Person) dao.get(Person.class, "id", 1);
		Assert.assertEquals(personX, null);
	}
	
	
	@AfterClass
	public static void destroyDb(){
		dbEngine.closeDB();
		FileUtil.deleteDirectory(testDbFolderName);
	}
}
