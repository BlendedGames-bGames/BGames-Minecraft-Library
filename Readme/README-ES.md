# BGames Library Mod
[![es](https://img.shields.io/badge/lang-es-green.svg)](https://github.com/Gsimken/BGames-Minecraft-Library-Forge-Fabric/blob/master/README.md)
[![en](https://img.shields.io/badge/lang-en-blue.svg)](https://github.com/Gsimken/BGames-Minecraft-Library-Forge-Fabric/blob/master/Readme/README-EN.md)


La Librería BGames Mod es una biblioteca para Fabric y Forge diseñada para integrarse con otros mods. Esta establece una conexión con bGames, permitiendo al jugador autenticarse y visualizar sus puntos bGames.

## Antes de comenzar
Al momento de inicializar Minecraft con el mod instalado se creará un archivo de configuraciones dependiendo del modloader que se esté utilizando, este se encontrara en la carpeta .minecraft/config y debe ser configurado dependeiendo del servidor de bGames al que se desee conectar (si el servidor de bGames esta configurado por defecto, bastaría con cambiar el URL).

### Forge
Archivo: bgames-common-application.toml


    ["Config Bgames API"]
        #This is the url to make the request. Default: http://localhost:
        "API URL" = "http://localhost:"
        #This is the port to make get requets. Default: 3001
        "API GET PORT" = 3001
        #This is the port to make post requets. Default: 3002
        "API POST PORT" = 3002
        #This is the port to make user management requets. Default: 3010
        "API USER PORT" = 3010

### Fabric
Archivo: bGames-config.json
        
    {
        "URL":"http://localhost:",
        "GET_PORT":3001,
        "POST_PORT":3002,
        "USER_PORT":3010
        }

Una vez el archivo haya sido modificado y guardado, no olvides reiniciar el juego.

## Primeros Pasos
Al ingresar a un mundo, se solicitará un perfil válido de bGames, mostrando la siguiente pantalla de inicio de sesión:

![Inicio de sesión modal bGames](https://drive.google.com/uc?export=view&id=1AJ1Xfk4d5Xty88Yfup73VBMm5RyqbFpN)

Esta pantalla permite al usuario conectarse a bGames y obtener sus puntos de dimensiones en Minecraft. Si la conexión falla por un error o ingreso inválido, la pantalla se cerrará e informará al usuario a través del chat. Esta ventana puede ser reabierta utilizando el comando "/login". 

Una vez que el usuario esté autenticado con bGames, podrá utilizar el botón de la librería presente en el inventario.

![Inventario del jugador con botón en la parte superior](https://drive.google.com/uc?export=view&id=1O8BM-daC16On-hyt3umy52oSQ3RzO32K)

Al presionar este botón, se mostrarán los puntos obtenidos anteriormente en una nueva ventana, informándole al usuario los puntos disponibles para su uso. Estos puntos se actualizan cada vez que se abre el modal. 

![Modal con dimensiones](https://drive.google.com/uc?export=view&id=15WxeedXBPX0Pjoh9YdkHIQ6xtXDrhELl)

**Nota:** En la versión 1.0.3 de la librería, solo se utilizan 5 clasificaciones de dimensiones, no se consideran los atributos. Esto puede variar en el futuro con la inclusión de nuevas dimensiones.

---

## Requisitos
* ### **Conexión a Internet**
    Dado que bGames es un módulo en la nube, se requiere una conexión a Internet para acceder.
* ### **Cuenta de bGames**
    Es necesario contar con un perfil en bGames, ya que si el usuario no existe no tendrá puntos que utilizar.
* ### **Minecraft Forge | FabricMc**
    En caso de utilizar Forge, no existen pre-requisitos. Si se usa Fabric, el mod requiere [Fabric Api](https://www.curseforge.com/minecraft/mc-mods/fabric-api).

---

## Preguntas y Respuestas (FAQ)

* ### **¿Qué es Blended Games (bGames)?**
    Blended Games es un proyecto/framework de código abierto desarrollado por el Laboratorio InTeractiOn de la Universidad de Santiago de Chile. Su objetivo es promover el desarrollo de videojuegos que faciliten el equilibrio entre las tareas cotidianas y la vida virtual, con el fin de prevenir situaciones perjudiciales para los jugadores, como la adicción a los videojuegos.

* ### **¿Cómo funciona bGames?**
    bGames funciona a través de una serie de servicios que miden y almacenan la actividad de un jugador en un perfil en la nube. Los puntos que constituyen estos perfiles provienen de actividades rastreadas tanto por software como por hardware. Esto podría incluir, por ejemplo, las estadísticas de un jugador en Chess.com o el tiempo de uso de su teléfono móvil. Estos puntos luego se categorizan en diversas dimensiones y atributos, que pueden ser intercambiados por ventajas en los videojuegos.

    **Nota:** Para obtener más información sobre bGames o su arquitectura, puedes visitar la sección bGames en la página de [InTeractiOn Labs](https://interaction-lab.info/publications).
  
* ### No tengo minecraft, ¿Donde puedo obtenerlo y como puedo usar la biblioteca?
    Se ha preparado una guía especial para estos casos, puede encontrar una guía en la sección "[Extra](https://github.com/Gsimken/BGames-Minecraft-Library/wiki/Extra:-Minecraft-and-Mods-Installation-Tutorial)" de la wiki
  
* ### **¿Puedo desarrollar mods de Minecraft para bGames?**
    ¡Por supuesto! Esta biblioteca busca ampliar el catálogo de bGames en Minecraft. Puedes utilizarla tanto en mods existentes como en nuevos. Para más detalles sobre las funcionalidades para desarrolladores, puedes consultar la wiki en el repositorio de [GitHub](https://github.com/Gsimken/BGames-Minecraft-Library-Forge-Fabric/wiki).

* ### **¿bGames solo está disponible en Minecraft?**
    No, bGames tiene la intención de llegar a todas las plataformas y juegos que los desarrolladores deseen, por lo que su integración no se limita solo a Minecraft. Hasta la fecha, se han hecho adaptaciones de algunos juegos como Tetris y Chess, así como juegos creados desde cero que permiten el uso de bGames.
