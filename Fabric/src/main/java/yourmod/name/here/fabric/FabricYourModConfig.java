package yourmod.name.here.fabric;

import at.petrak.hexcasting.api.misc.MediaConstants;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import yourmod.name.here.api.YourModAPI;
import yourmod.name.here.api.config.YourModConfig;
import yourmod.name.here.xplat.IXplatAbstractions;

@SuppressWarnings({"FieldCanBeLocal", "FieldMayBeFinal"})
@Config(name = YourModAPI.MOD_ID)
@Config.Gui.Background("minecraft:textures/block/calcite.png")
public class FabricYourModConfig extends PartitioningSerializer.GlobalData {
    @ConfigEntry.Category("common")
    @ConfigEntry.Gui.TransitiveObject
    public final Common common = new Common();
    @ConfigEntry.Category("client")
    @ConfigEntry.Gui.TransitiveObject
    public final Client client = new Client();
    @ConfigEntry.Category("server")
    @ConfigEntry.Gui.TransitiveObject
    public final Server server = new Server();

    public static FabricYourModConfig setup() {
        AutoConfig.register(FabricYourModConfig.class, PartitioningSerializer.wrap(JanksonConfigSerializer::new));
        var instance = AutoConfig.getConfigHolder(FabricYourModConfig.class).getConfig();

        YourModConfig.setCommon(instance.common);
        // We care about the client only on the *physical* client ...
        if (IXplatAbstractions.INSTANCE.isPhysicalClient()) {
            YourModConfig.setClient(instance.client);
        }
        // but we care about the server on the *logical* server
        // i believe this should Just Work without a guard? assuming we don't access it from the client ever
        YourModConfig.setServer(instance.server);

        return instance;
    }


    @Config(name = "common")
    private static class Common implements ConfigData, YourModConfig.CommonConfigAccess { }

    @Config(name = "client")
    private static class Client implements ConfigData, YourModConfig.ClientConfigAccess { }


    @Config(name = "server")
    private static class Server implements ConfigData, YourModConfig.ServerConfigAccess {

        @ConfigEntry.Gui.CollapsibleObject
        private ExampleSpells exampleSpells = new ExampleSpells();

        static class ExampleSpells {
            // costs of misc spells
            double exampleConstActionCost = DEFAULT_EXAMPLE_CONST_ACTION_COST;
            double exampleSpellActionCost = DEFAULT_EXAMPLE_SPELL_ACTION_COST;
        }

        @Override
        public void validatePostLoad() throws ValidationException {
            // costs of misc spells
            this.exampleSpells.exampleConstActionCost = bound(this.exampleSpells.exampleConstActionCost, DEF_MIN_COST, DEF_MAX_COST);
            this.exampleSpells.exampleSpellActionCost = bound(this.exampleSpells.exampleSpellActionCost, DEF_MIN_COST, DEF_MAX_COST);
        }

        private int bound(int toBind, int lower, int upper) {
            return Math.min(Math.max(toBind, lower), upper);
        }
        private double bound(double toBind, double lower, double upper) {
            return Math.min(Math.max(toBind, lower), upper);
        }


        //region getters
        @Override
        public int getExampleConstActionCost() {
            return (int) (exampleSpells.exampleConstActionCost * MediaConstants.DUST_UNIT);
        }

        @Override
        public int getExampleSpellActionCost() {
            return (int) (exampleSpells.exampleSpellActionCost * MediaConstants.DUST_UNIT);
        }
        //endregion
    }
}
