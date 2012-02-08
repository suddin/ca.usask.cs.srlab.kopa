package ca.usask.cs.srlab.dao.db.engine;

import java.io.Serializable;

public interface IDbEngine {

	public void save(Object object);
	
	public void delete(Object object);
	
	public Object get(Class<?> clazz, String idColumnName, Serializable id);

	public Object[] getAll(Class<?> clazz);

	public void deleteAll(Class<?> clazz);
	
	public IDbEngine openDB();
	
	public boolean isDbOpen();
	
	public void closeDB();
	
	public void commit();
	
	public void rollback();
	
}
