package org.camposmdev.model.db;

public class DBStore {
	private DBStore() {
		/* TODO - connect to db to crud user data and game data */
		/* What db to use? */
	}

	private static DBStore singleton;

	public static DBStore instance() {
		if (singleton == null)
			singleton = new DBStore();
		return singleton;
	}
}
