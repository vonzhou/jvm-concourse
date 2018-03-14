package com.vonzhou.learn.hotspotinaction.ch08is;

public class SimpleException extends Exception {
	private static final long serialVersionUID = -8983381128076107849L;

	public SimpleException(String message) {
		super(message);
	}

	@Override
	public String toString() {
		return "SimpleException [" + super.getMessage() + "]";
	}
}
