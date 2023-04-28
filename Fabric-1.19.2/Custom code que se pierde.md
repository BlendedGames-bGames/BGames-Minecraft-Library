# Custom code que se pierde
Este codigo se pierde algunas veces por que mcreator sobrescribe archivos completos po lo que es mejor ir reservandolo en el transcurso
### Codigo que incluye los archivos de configuración
~~~
~/java/net/gsimken/bgameslibrary/BgamesLibraryMod.java
//IMPORTS
import net.gsimken.bgameslibrary.core.api_config.BGamesApiConfigsModel;

public class BgameslibraryMod implements ModInitializer {
	public static final Logger LOGGER = LogManager.getLogger();
	public static final String MODID = "bgameslibrary";

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing BgameslibraryMod");
		BGamesApiConfigsModel init = new BGamesApiConfigsModel(); // load the config
		init.loadConfigs();
		init.printConfigs();
	}
~~~

