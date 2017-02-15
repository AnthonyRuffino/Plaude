package org.ncidence.plaude.messages;

import java.util.HashSet;
import java.util.Set;

public class RosterMessage {
	private Set<String> names = new HashSet<>();

	public RosterMessage() {
		super();
	}

	public Set<String> getNames() {
		return names;
	}

	public void setNames(Set<String> names) {
		this.names = names;
	}

	
	
	
	
	
}
