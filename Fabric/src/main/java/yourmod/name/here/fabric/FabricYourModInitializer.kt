package yourmod.name.here.fabric

import net.fabricmc.api.ModInitializer
import net.minecraft.core.Registry
import net.minecraft.resources.ResourceLocation
import yourmod.name.here.api.YourModAPI
import yourmod.name.here.common.casting.Patterns
import java.util.function.BiConsumer

object FabricYourModInitializer : ModInitializer {

    override fun onInitialize() {
        YourModAPI.LOGGER.info("Hello Fabric World!")

        FabricYourModConfig.setup()

        initListeners()

        initRegistries()

        Patterns.registerPatterns()
    }

    private fun initListeners() {}

    private fun initRegistries() {}


    private fun <T> bind(registry: Registry<in T>): BiConsumer<T, ResourceLocation> =
        BiConsumer<T, ResourceLocation> { t, id -> Registry.register(registry, id, t) }
}