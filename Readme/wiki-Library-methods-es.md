
# Introduccion
En esta sección se irán actualizando los métodos que se vayan agregando a la librería. Cabe destacar que todo lo mencionado en esta sección aplica tanto para Fabric como para Forge, sin embargo, los ejemplos están con `mappings oficiales` utilizados por Forge, y no con los `yarn mappings` recomendados por Fabric.

## Información de dimensiones

Actualmente la biblioteca trabaja con 5 dimensiones de bGames, se planea integrar a futuro un manejo dinámico de ellas.

Entre ellas existen las dimensiones:
- Física
- Lingüística
- Social
- Afectiva
- Cognitiva

Para acceder a sus nombres, se puede realizar mediante atributos estáticos de la biblioteca.

- `BgamesLibrary.bgames_physical_name`
- `BgamesLibrary.bgames_linguistic_name`
- `BgamesLibrary.bgames_social_name`
- `BgamesLibrary.bgames_affective_name`
- `BgamesLibrary.bgames_cognitive_name`

Además, incluso si los nombres se encuentran en español en la base de datos, estos cuentan con sus traducciones al inglés correspondientes y pueden ser usados mediante las siguientes claves de traducción:

- `gui.bgameslibrary.display_attributes.label_physical`: Physical
- `gui.bgameslibrary.display_attributes.label_linguistic`: Linguistic
- `gui.bgameslibrary.display_attributes.label_social`: Social
- `gui.bgameslibrary.display_attributes.label_affective`: Affective
- `gui.bgameslibrary.display_attributes.label_cognitive`: Cognitive

## Métodos disponibles
Se han pensado funcionalidades exclusivas para los desarrolladores. Para esto se ha creado una clase estática exclusivamente con los métodos, dicha clase lleva el nombre de `BGamesLibraryTools`.

Es importante mencionar que las siguientes funcionalidades funcionan tanto en `ClientSide` como en `ServerSide`, pero se recomienda encarecidamente utilizarlas en `ServerSide`.

### Verificar inicio de sesión
Para verificar si un usuario inició sesión dentro de la plataforma bGames, se debe utilizar el método `isPlayerLogged` el que recibe una clase `Player` y retorna un `boolean`.

Un ejemplo de utilización de esto sería el siguiente fragmento.
```java
ServerPlayer player = context.getSender();
if(BGamesLibraryTools.isPlayerLogged(player)){
    //Haz algo
}
else{
    //informar que no está logueado
    //Clave de traducción
    //login.bgameslibrary.not_logged
}
```
### Gastar puntos
Para consumir puntos existe el método `spendPoints`, este método recibe un Jugador (`Player`), un `String` con el nombre del atributo a gastar, un `int` con la cantidad de puntos a usar, y retorna un `boolean`, representando un `true` si se logró usar la cantidad de puntos, y un `false`, en caso de que no, en este caso además muestra un mensaje por el chat mencionando la razón del por qué.

Ejemplo:
```java
ServerPlayer player = context.getSender();
 //Se consulta si el jugador está logueado
if(BGamesLibraryTools.isPlayerLogged(player)){
   //En caso de estarlo, se intenta gastar un punto de la dimensión social
    if(BGamesLibraryTools.spendPoints(player, BgamesLibrary.bgames_social_name,1)){
        //Si se logró se le otorga un efecto de héroe de la aldea nivel 5 por 30 segundos (600 ticks)
        player.addEffect(new MobEffectInstance(MobEffects.HERO_OF_THE_VILLAGE, 600, 4, false, false));
    }
}
```
### Obtener puntos
Si se desea verificar los puntos de una dimensión en un jugador, se puede utilizar `getPoints` que recibe un `String` con el nombre de la dimensión y un `Player` siendo este el jugador que se busca consultar, esta devuelve un `int`, este número representa los puntos en caso de existir, o un -1 si no. Es necesario tener encuenta que el método no consulta a la `API de bGames`, si no a los datos guardados en el jugador, pero dichos datos se refrescan en cada oportunidad que el usuario abre el modal de visualizaciones y cuando se usa el método de `spendPoints`

La siguiente línea es un ejemplo que permite obtener los puntos de la dimensión cognitiva de un jugador.

```java
Player player = container.player
BGamesLibraryTools.getPoints(BgamesLibrary.bgames_cognitive_name,player)
```

## Otros ejemplos
Si requieres alguna manera de visualizar cómo puede utilizarse la bGames Library, puedes ver un mod desarrollado exclusivamente para realizar pruebas dentro del juego. Dicho mod también está disponible tanto para Fabric como para Forge y se denomina [bGames Mod]().
