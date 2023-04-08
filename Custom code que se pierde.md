# Custom code que se pierde
Este codigo se pierde algunas veces por que mcreator sobrescribe archivos completos po lo que es mejor ir reservandolo en el transcurso
### Codigo que incluye los archivos de configuración
~~~
~/java/net/gsimken/bgameslibrary/BgamesLibraryMod.java
//IMPORTS
import net.gsimken.bgameslibrary.core.api_config.BgamesCommonConfigs;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;


@Mod("bgames_library")
public class BgamesLibraryMod {
    public static final Logger LOGGER = LogManager.getLogger(BgamesLibraryMod.class);
    public static final String MODID = "bgames_library";

    public BgamesLibraryMod() {
        MinecraftForge.EVENT_BUS.register(this);

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        BgamesLibraryModMenus.REGISTRY.register(bus);
        //LOAD CONFIGS    
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, BgamesCommonConfigs.SPEC,"bgames-common-application.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, BgamesCommonConfigs.SPEC,"bgames-client-application.toml");

    }
~~~

Testeo
 /debug_email_pass_get_id "gerardo.ternero@usach.cl" "asd123"

 a a