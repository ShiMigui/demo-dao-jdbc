package db;

public class DBException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DBException(String msg) {
	super("[DATABASE] " + msg);
    }

    public DBException(String msg, String causedBy) {
	super("[DATABASE] " + msg + " Caused by: " + causedBy);
    }
}
