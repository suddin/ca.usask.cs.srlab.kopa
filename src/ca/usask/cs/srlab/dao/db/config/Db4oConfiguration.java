package ca.usask.cs.srlab.dao.db.config;

import java.io.File;
import java.io.IOException;

public class Db4oConfiguration implements IDbEngineConfiguration {

	private String dbFileName;
	
	private Db4oConfiguration(){
	}
	
	public Db4oConfiguration(String dbFileName) {
		super();
		this.dbFileName = dbFileName;
	}

	public String getDbFileName() {
		return dbFileName;
	}
	
	
	public static Db4oConfiguration getDefaultConfiguration(){
		Db4oConfiguration config = new Db4oConfiguration();
		String systemRoot = new File(ClassLoader.getSystemClassLoader()
				.getResource(".").getFile()).getParent();

		String dbFolderName = systemRoot.concat(System
				.getProperty("file.separator") + "db");
		File dbFolder = new File(dbFolderName);
		if (!dbFolder.exists()) {
			dbFolder.mkdir();
		}

		config.dbFileName = dbFolderName.concat(System
				.getProperty("file.separator") + "default.db4o");

		File dbFile = new File(config.dbFileName);
		if (!dbFile.exists()) {
			try {
				dbFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return config;
	}

}
