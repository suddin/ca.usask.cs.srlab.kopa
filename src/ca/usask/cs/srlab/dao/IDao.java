package ca.usask.cs.srlab.dao;

import java.io.Serializable;
import java.util.List;

public interface IDao {

	public void save(Object object);
	
	public void delete(Object object);
	
	public Object get(Class<?> clazz, String idColumnName, Serializable id);

	public void deleteAll(Class<?> clazz);

	public List<?> getAll(Class<?> clazz);
	
}
