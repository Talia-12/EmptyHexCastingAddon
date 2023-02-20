package yourmod.name.here.api;

import com.google.common.base.Suppliers;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;

public interface YourModAPI
{
	String MOD_ID = "yourmod";
	Logger LOGGER = LogManager.getLogger(MOD_ID);
	
	Supplier<YourModAPI> INSTANCE = Suppliers.memoize(() -> {
		try {
			return (YourModAPI) Class.forName("yourmod.name.here.common.impl.YourModAPIImpl")
								 .getDeclaredConstructor().newInstance();
		} catch (ReflectiveOperationException e) {
			LogManager.getLogger().warn("Unable to find YourModAPIImpl, using a dummy");
			return new YourModAPI() {
			};
		}
	});
	
	static YourModAPI instance() {
		return INSTANCE.get();
	}
	
	static ResourceLocation modLoc(String s) {
		return new ResourceLocation(MOD_ID, s);
	}
}
