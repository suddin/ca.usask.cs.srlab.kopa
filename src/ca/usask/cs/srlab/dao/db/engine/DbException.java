package ca.usask.cs.srlab.dao.db.engine;

public class DbException extends RuntimeException {

	private static final long serialVersionUID = -4147956096321855373L;

	public DbException(String message) {
		super (message);
	}

	public DbException(Exception e, String message) {
		super(message, e);
	}

}
