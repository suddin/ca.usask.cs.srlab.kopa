package ca.usask.cs.srlab.dao.db.engine;

import java.io.Serializable;

import ca.usask.cs.srlab.dao.db.config.Db4oConfiguration;
import ca.usask.cs.srlab.dao.db.config.IDbEngineConfiguration;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.query.Query;

public class Db4oEngine implements IDbEngine {

	private ObjectContainer db;

	private Db4oConfiguration dbConfig;
	
	private EmbeddedConfiguration embeddedConfig;

	@SuppressWarnings("unused")
	private Db4oEngine() {
	}

	public Db4oEngine(IDbEngineConfiguration dbConfig) {
		this.dbConfig = ((Db4oConfiguration) dbConfig);
	}

	@Override
	public void save(Object object) {
		if(isDbOpen)
			db.store(object);
		else
			throw new DbException("database is not open, make sure to call openDB() method of the engine instance before database operation");
	}

	@Override
	public void delete(Object object) {
		if(isDbOpen)	
			db.delete(object);
		else
			throw new DbException("database is not open, make sure to call openDB() method of the engine instance before database operation");
	}

	@Override
	public Object get(Class<?> clazz, String idColumnName, Serializable id) {
		if(isDbOpen){
			Query query = db.query();
			query.constrain(clazz);
			query.descend(idColumnName).constrain(id);
			@SuppressWarnings("rawtypes")
			ObjectSet result = query.execute();
			return result.hasNext() ? result.next() : null;
		}
		else
			throw new DbException("database is not open, make sure to call openDB() method of the engine instance before database operation");
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object[] getAll(Class<?> clazz) {
		if(isDbOpen){	
			ObjectSet result = db.queryByExample(clazz);
			return result.toArray();
		}else
			throw new DbException("database is not open, make sure to call openDB() method of the engine instance before database operation");
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void deleteAll(Class<?> clazz) {
		if(isDbOpen){
			ObjectSet result=db.queryByExample(clazz);
	        while(result.hasNext()) {
	            db.delete(result.next());
	        }
		}else
			throw new DbException("database is not open, make sure to call openDB() method of the engine instance before database operation");
	}
	
	private boolean isDbOpen;
	
	@Override
	public IDbEngine openDB() {
		if(!isDbOpen){
			try{
				embeddedConfig = Db4oEmbedded.newConfiguration();
				db = Db4oEmbedded.openFile(embeddedConfig,
					this.dbConfig.getDbFileName());
			}catch (Exception e) {
				throw new DbException(e, "error in opening database");
			}
			isDbOpen = true;
			return this;
		}else{
			throw new DbException("database already open!");
		}
	}

	@Override
	public void closeDB() {
		if(isDbOpen){
			isDbOpen = false;
			db.close();
		}
	}

	@Override
	public boolean isDbOpen() {
		return isDbOpen;
	}

	@Override
	public void commit() {
		if(isDbOpen)
			try {
				db.commit();
			}catch (Exception e) {
				throw new DbException(e, "error occured in commit operation");
			}
		else
			throw new DbException("invalid commit request, database closed");
	}

	@Override
	public void rollback() {
		if(isDbOpen)
			try {
				db.rollback();
			}catch (Exception e) {
				throw new DbException(e, "error occured in rollback operation");
			}
		else
			throw new DbException("invalid rollback request, database closed");
	}
}
