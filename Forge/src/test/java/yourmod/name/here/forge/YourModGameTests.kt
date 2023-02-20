package yourmod.name.here.forge

import net.minecraftforge.event.RegisterGameTestsEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod.EventBusSubscriber
import yourmod.name.here.api.YourModAPI

@EventBusSubscriber(modid = YourModAPI.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public object YourModGameTests {
	@SubscribeEvent
	public fun registerTests(event: RegisterGameTestsEvent) {
		YourModAPI.LOGGER.debug("registering tests")
		event.register(ExampleTests::class.java)
	}
}