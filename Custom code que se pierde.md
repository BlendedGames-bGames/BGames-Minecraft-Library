# Custom code que se pierde
Este codigo se pierde algunas veces por que mcreator sobrescribe archivos completos po lo que es mejor ir reservandolo en el transcurso
### Codigo que incluye los archivos de configuración
~~~
~/java/net/gsimken/bgameslibrary/BgamesLibraryMod.java
//IMPORTS
import net.gsimken.bgameslibrary.core.api_config.BgamesCommonConfigs;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
           
        //LOAD CONFIGS    
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, BgamesCommonConfigs.SPEC,"bgames-common-application.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, BgamesCommonConfigs.SPEC,"bgames-client-application.toml");
~~~
