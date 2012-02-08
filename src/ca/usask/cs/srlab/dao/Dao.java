package ca.usask.cs.srlab.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ca.usask.cs.srlab.dao.db.engine.IDbEngine;

public class Dao implements IDao {
	
	private IDbEngine dbEngine;
	
	@SuppressWarnings("unused")
	private Dao(){
	}

	public Dao(IDbEngine dbEngine){
		this.dbEngine = dbEngine;
	}
	
	@Override
	public void save(Object object) {
		dbEngine.save(object);
	}

	@Override
	public void delete(Object object) {
		dbEngine.delete(object);
	}

	@Override
	public Object get(Class<?> clazz, String idColumnName, Serializable id) {
		return dbEngine.get(clazz, idColumnName, id);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List getAll(Class<?> clazz) {
		List result = new ArrayList();
		result = Arrays.asList(dbEngine.getAll(clazz));
		return result;
	}
	
	@Override
	public void deleteAll(Class<?> clazz) {
		dbEngine.deleteAll(clazz);
	}

}
