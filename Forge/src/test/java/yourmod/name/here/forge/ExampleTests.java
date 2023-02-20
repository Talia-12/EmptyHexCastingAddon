package yourmod.name.here.forge;

import net.minecraft.gametest.framework.*;
import yourmod.name.here.api.YourModAPI;

public class ExampleTests {
	@GameTest(templateNamespace = YourModAPI.MOD_ID, template = "basic")
	public static void exampleTest(GameTestHelper helper) {
		YourModAPI.LOGGER.debug("running example test");
		
		helper.onEachTick(() -> {
			YourModAPI.LOGGER.debug("current tick: " + helper.getTick());
		});
	}
}
