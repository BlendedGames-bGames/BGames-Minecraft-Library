# BGames Library Mod
[![es](https://img.shields.io/badge/lang-es-green.svg)](https://github.com/Gsimken/BGames-Minecraft-Library-Forge-Fabric/blob/master/Readme/README-ES.md)
[![en](https://img.shields.io/badge/lang-en-blue.svg)](https://github.com/Gsimken/BGames-Minecraft-Library-Forge-Fabric/blob/master/README.md)



bGames Library Mod is a library for Fabric and Forge designed to integrate with other mods. It establishes a connection with bGames, allowing the player to authenticate and view their bGames points.
## Before Starting
Upon initializing Minecraft with the installed mod, a configuration file will be created depending on the modloader being used. This file will be located in the .minecraft/config folder and should be configured based on the bGames server you wish to connect to (if the bGames server is set to default, you would simply need to change the URL).

### Forge
File: bgames-common-application.toml

    ["Config Bgames API"]
        #This is the url to make the request. Default: http://localhost:
        "API URL" = "http://localhost:"
        #This is the port to make get requests. Default: 3001
        "API GET PORT" = 3001
        #This is the port to make post requests. Default: 3002
        "API POST PORT" = 3002
        #This is the port to make user management requests. Default: 3010
        "API USER PORT" = 3010

### Fabric
File: bGames-config.json

    {
        "URL":"http://localhost:",
        "GET_PORT":3001,
        "POST_PORT":3002,
        "USER_PORT":3010
    }

Once the file has been modified and saved, don't forget to restart the game.

## Getting Started
Upon entering a world, a valid bGames profile will be requested, displaying the following login screen:

![bGames modal login screen](https://drive.google.com/uc?export=view&id=1AJ1Xfk4d5Xty88Yfup73VBMm5RyqbFpN)

This screen allows the user to connect to bGames and obtain their dimensions points in Minecraft. If the connection fails due to an error or invalid entry, the screen will close and inform the user through the chat. This window can be reopened using the "/login" command. 

Once the user is authenticated with bGames, they will be able to use the library button present in the inventory.

![Player inventory with button at the top](https://drive.google.com/uc?export=view&id=1O8BM-daC16On-hyt3umy52oSQ3RzO32K)

Pressing this button will display the points previously obtained in a new window, informing the user of the points available for use. These points are updated every time the modal is opened.

![Modal with dimensions](https://drive.google.com/uc?export=view&id=15WxeedXBPX0Pjoh9YdkHIQ6xtXDrhELl)

**Note:** In version 1.0.3 of the library, only 5 dimensions classifications are used, attributes are not considered. This may vary in the future with the inclusion of new dimensions.

---

## Requirements
* ### **Internet Connection**
    Since bGames is a cloud-based module, an Internet connection is required to access.
* ### **bGames Account**
    It is necessary to have a bGames profile, as if the user does not exist, they will not have points to use.
* ### **Minecraft Forge | FabricMc**
    In case of using Forge, there are no prerequisites. If Fabric is used, the mod requires [Fabric Api](https://www.curseforge.com/minecraft/mc-mods/fabric-api).

---

## Questions and Answers (FAQ)

* ### **What is Blended Games (bGames)?**

    Blended Games is an open source project/framework developed by the InTeractiOn Lab at the University of Santiago de Chile. Its goal is to promote the development of video games that facilitate a balance between day to day tasks and virtual life, in order to prevent harmful situations for players, such as video game addiction.

* ### **How does bGames work?**

    bGames works through a series of services that measure and store a player's activity in a cloud profile. The points that constitute these profiles come from activities tracked by software and hardware. This could include, for example, a player's statistics on Chess.com or their mobile phone time use. These points are then categorized into various dimensions and attributes, which can be exchanged for advantages in video games.

    **Note:** For more information about bGames or its architecture, you can visit the bGames section on the [InTeractiOn Labs](https://bgames.interaction-lab.info/publications)  page.

* ### I don't have Minecraft, where can I get it and how can I use the library?
    A special guide has been prepared for these cases. You can find a tutorial in the "[Extra](https://github.com/Gsimken/BGames-Minecraft-Library/wiki/Extra:-Minecraft-and-Mods-Installation-Tutorial)" section of the wiki.

* ### **Can I develop Minecraft mods for bGames?**
    Absolutely! This library seeks to expand the bGames catalog in Minecraft. You can use it in both existing mods and new ones. For more details on developer features, you can consult the wiki on the [GitHub](https://github.com/Gsimken/BGames-Minecraft-Library/wiki/Guide-for-bGames-Library-Developers) repository.

* ### **Is bGames only available in Minecraft?**
    No, bGames intends to reach all platforms and games that developers seek, so its integration is not limited only to Minecraft. To date, adaptations have been made of some games like Tetris and Chess, as well as games created from scratch that allow the use of bGames.
