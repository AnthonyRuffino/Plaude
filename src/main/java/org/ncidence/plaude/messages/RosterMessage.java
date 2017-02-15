package org.ncidence.plaude.messages;

import java.util.ArrayList;
import java.util.List;

public class RosterMessage {
	private List<String> names = new ArrayList<>();

	public RosterMessage() {
		super();
	}

	public List<String> getNames() {
		return names;
	}

	public void setNames(List<String> names) {
		this.names = names;
	}

}
