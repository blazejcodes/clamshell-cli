package org.clamshellcli.test;

import java.util.List;

import org.clamshellcli.api.Command;
import org.clamshellcli.api.CommandLoader;
import org.clamshellcli.api.Context;
import org.clamshellcli.api.Plugin;

public class MockCommandLoader implements CommandLoader, Plugin {

	private final List<Command> commands;

	public MockCommandLoader(List<Command> commands) {
		this.commands = commands;
	}

	@Override
	public List<Command> loadCommands(Context plug) {
		return commands;
	}

	@Override
	public void plug(Context plug) {
		// NO OP
	}

	@Override
	public void unplug(Context plug) {
		// NO OP

	}

}
