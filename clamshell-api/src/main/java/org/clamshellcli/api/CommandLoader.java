package org.clamshellcli.api;

import java.util.List;


public interface CommandLoader {

	List<Command> loadCommands(Context plug);

}
