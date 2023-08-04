
# bGames Library Docs

## Prerrequisitos
Como prerrequisito, es necesario que ya tenga instalado JDK (v17+), los microservicios y la base de datos de bGames ejecutandose, como tambien un proyecto de Forge o Fabric listo para comenzar.

En caso de necesitar información adicional sobre cómo iniciar un proyecto en alguno de los modloaders, puede consultar las siguientes páginas:
- [Minecraft Forge](https://docs.minecraftforge.net/en/latest/gettingstarted/#from-zero-to-modding)
- [Fabric](https://fabricmc.net/wiki/tutorial:setup)

## Comenzando
Lo primero es añadir la biblioteca de bGames a su mod. Para esto tiene 2 maneras de hacerlo, ambas en Gradle: la primera, integrándolo mediante [Curse Maven](https://www.cursemaven.com) y la segundausando flatDir.

### Integración con Curse Maven
Agregue Curse Maven a sus repositorios en `build.gradle`
 ```gradle
repositories {
    maven {
        url "https://cursemaven.com"
        content {
            includeGroup "curse.maven"
        }
    }
}
 ```
Ahora, dependiendo de si está usando FabricMC o Minecraft Forge, la manera de agregar la dependencia variará. Continúe en base al modLoader con el que se encuentre trabajando.

- #### Forge
 ```gradle
dependencies {
    implementation fg.deobf("curse.maven:<nombre_proyecto>-<id_proyecto>:<id_archivo>")
    }
 ```
- #### Fabric
Además de añadir la biblioteca, es necesario añadir fabric-api
 ```gradle
dependencies {
    modImplementation "net.fabricmc.fabric-api:fabric-api:${project.fabric_version}"
    modImplementation "curse.maven:<nombre_proyecto>-<id_proyecto>:<id_archivo>"
}
 ```
En `nombre_proyecto` va un identificador a elección, una buena idea podría ser `bgames-library`.

En `id_proyecto` debe ir el identificador de [bGames Library](https://www.curseforge.com/minecraft/mc-mods/bgames-library), este se encuentra ubicado al costado derecho de la página de CurseForge, e `id_archivo` es el identificador único al final del enlace cuando se encuentra revisando un archivo. Para información más detallada, consulte [Curse Maven](https://www.cursemaven.com).

Actualmente, CurseForge ofrece una funcionalidad al entrar a un mod, que permite generar dicha línea automáticamente para copiar y pegar.

Por ejemplo, en el archivo de Forge de la librería se vería así.
![Imagen CurseForge forge mod line](https://drive.google.com/uc?export=view&id=1nHtwT3h08FH93QsQZm7KFAHlyoTzgZO6)
Y en el caso de Fabric de esta manera.
![Imagen CurseForge fabric mod line](https://drive.google.com/uc?export=view&id=1m_1nTzLnlzIPiGBYNrGDyWA4MKENp706)

En este punto, ya es posible avanzar a la siguiente sección.

### Uso de flatDir
Para utilizar flatDir, es necesario descargar la biblioteca previamente. En este caso, se usará el `.jar` con las siguientes características:

- Nombre: bgameslibrary-\<modloader>-1.0.4-1.19.2.jar
- Grupo: net.gsimken
- Mod Id: bgameslibrary
- Versión: 1.0.4-1.19.2

El primer paso es colocar el archivo descargado en una carpeta en la raíz del proyecto. Para el ejemplo, se utilizará el nombre `libs`. Este nombre debe ser agregado de la siguiente manera a sus repositorios en `build.gradle`
 ```gradle
repositories {
     flatDir {
         dir 'libs'
    }
}
 ```
Con esto agregado, toca agregar a las dependencias la biblioteca. Dependerá del modloader usado la línea que se deba usar.
- #### Forge
 ```gradle
dependencies {
       implementation fg.deobf("net.gsimken.bgameslibrary:bgameslibrary-forge:1.0.4-1.19.2")
    }
 ```
- #### Fabric
Además de añadir la biblioteca, es necesario añadir fabric-api
 ```gradle
dependencies {
    modImplementation "net.fabricmc.fabric-api:fabric-api:${project.fabric_version}"
	modImplementation "net.gsimken.bgameslibrary:bgameslibrary-fabric:1.0.4-1.19.2"
}
 ```
Para más detalles sobre las dependencias en flatDir, puede consultar la [Modding Tutorial](https://moddingtutorials.org/dependencies/#your-computer)

En este punto, ya es posible avanzar a la siguiente sección.

### bGames Library como dependencia
Debido a que se estarán usando funcionalidades del proyecto, es necesario que este sea agregado como dependencia a los proyectos que se vayan creando, es decir, como un requisito obligatorio para el usuario que use el nuevo mod. Para esto, es necesario agregarlo en las especificaciones del mod, así el juego puede avisarle a los usuarios cuando no tengan bGames Library en su carpeta de mods.

Para agregar la dependencia en Forge, es necesario agregar al archivo `mods.toml` un ítem extra al final del archivo.
 ```toml
[[dependencies.examplemodid]]
    # the modid of the dependency
    modId="bgameslibrary" #mandatory
    # Does this dependency have to exist - if not, ordering below must be specified
    mandatory=true #mandatory
    # The version range of the dependency
    versionRange="[1.0.4,)" #mandatory
    # An ordering relationship for the dependency - BEFORE or AFTER required if the relationship is not mandatory
    ordering="BEFORE"
    # Side this dependency is applied on - BOTH, CLIENT or SERVER
    side="BOTH"
 ```
El código anterior especifica que el producto desarrollado con mod id "examplemodid" requiere una dependencia cuyo mod id es bgameslibrary, además de que es obligatoria (mandatory), y que acepta cualquier versión a partir de la 1.0.4 en adelante. Además, dicha dependencia debe ser cargada antes que examplemod en Minecraft y debe estar tanto en el servidor como en el cliente.

Para Fabric, la dependencia se añade al archivo `fabric.mod.json`, de forma similar a la anterior. Si no tiene un elemento "depends", es necesario añadirlo así.
 ```json
    "depends": {
		"fabricloader": ">=0.14.19",
		"minecraft": "~1.19.2",
		"java": ">=17",
		"bgameslibrary": ">=1.0.4-1.19.2"
	},
 ```

Si ha llegado hasta aquí, ¡felicidades! Su entorno de trabajo ya se encuentra listo. Puede pasar a revisar las funciones que ofrece bGames Library.
