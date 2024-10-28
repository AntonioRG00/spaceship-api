package com.antoniorg.spaceship.application.exception;

public class ItemNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1334169281526304148L;

	public ItemNotFoundException(String entityName, Long id) {
		super(entityName + " with ID " + id + " was not found");
	}
}
