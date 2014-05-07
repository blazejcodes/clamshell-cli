package org.clamshellcli.impl;

import java.io.File;
import java.util.Collections;
import java.util.List;

import org.clamshellcli.api.Command;
import org.clamshellcli.api.CommandLoader;
import org.clamshellcli.api.Configurator;
import org.clamshellcli.api.Context;
import org.clamshellcli.api.Plugin;
import org.clamshellcli.core.Clamshell;

public class FilesystemCommandLoader implements CommandLoader, Plugin {

	private static final Class<Command> COMMAND_TYPE = Command.class;
	private static final String COMMANDS_DIR_NAME = "commands";
	private static final String CLASSES_DIR_NAME = "classes";

	@Override
	public List<Command> loadCommands(Context plug) {
		File commandsDir = new File(COMMANDS_DIR_NAME);
		File classesDir = new File(commandsDir, CLASSES_DIR_NAME);
		List<Command> result = Collections.emptyList();

		// load classes from jar files
		if (commandsDir.isDirectory()) {
			ClassLoader jarsCl = null;
			try {
				jarsCl = Clamshell.ClassManager.getClassLoaderFromFiles(new File[] { commandsDir },
						Configurator.JARFILE_PATTERN, plug.getClassLoader());
				result = Clamshell.Runtime.loadServicePlugins(COMMAND_TYPE, jarsCl);
			} catch (Exception ex) {
				// failure OK. No classes loaded
			}
		}

		// load raw java classes in commands/classes
		if (classesDir.isDirectory()) {
			ClassLoader classesCl = null;
			try {
				classesCl = Clamshell.ClassManager.getClassLoaderFromDirs(new File[] { classesDir }, Thread
						.currentThread().getContextClassLoader());
				result.addAll(Clamshell.Runtime.loadServicePlugins(COMMAND_TYPE, classesCl));
			} catch (Exception ex) {
				// failure OK. No classes loaded.
			}
		}
		return result;
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
