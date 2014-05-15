package org.clamshellcli.impl.test;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.regex.Pattern;

import org.clamshellcli.api.Command;
import org.clamshellcli.api.Context;
import org.clamshellcli.api.Plugin;
import org.clamshellcli.impl.CmdController;
import org.clamshellcli.test.MockCommand;
import org.clamshellcli.test.MockCommandLoader;
import org.clamshellcli.test.MockContext;
import org.junit.Test;

public class CmdControllerTest {

	@Test
	public void testExtractPrefixedCmdFromInput() {

		CmdController controller = new CmdController();

		MockContext ctx = MockContext.createInstance();
		ctx.setPlugins(Arrays.<Plugin> asList(new MockCommandLoader(Arrays.<Command> asList(new MockCommand(){

			@Override
            public Object execute(Context ctx) {
	            return "";
            }}))));

		controller.plug(ctx);

		controller.setInputPattern(Pattern.compile("\\:(?<command>.*)"));

		ctx.putValue(Context.KEY_COMMAND_LINE_INPUT, ":mock");

		boolean handled = controller.handle(ctx);

		assertTrue(handled);

	}
}
