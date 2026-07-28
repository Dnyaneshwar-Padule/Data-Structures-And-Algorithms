package com.tca.threadedbst.exception;

public class DuplicateDataException extends Exception {

	private static final long serialVersionUID = 1L;

	public DuplicateDataException() {
	}

	public DuplicateDataException(String message) {
		super(message);
	}

	public DuplicateDataException(Throwable cause) {
		super(cause);
	}

	public DuplicateDataException(String message, Throwable cause) {
		super(message, cause);
	}

	public DuplicateDataException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
