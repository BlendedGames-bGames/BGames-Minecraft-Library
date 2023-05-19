package net.gsimken.bgameslibrary.bgames;

public class ClientBGamesPlayerData {
    /*
    Class use in the client side its only one per client
    */
    private static int playerId;
    private static int playerSocialPoints;
    private static int playerPhysicalPoints;
    private static int playerLinguisticPoints;
    private static int playerAffectivePoints;
    private static int playerCognitivePoints;
    private static String playerEmail;
    private static String playerPassword;

    public static boolean isLoggedIn() {;
        if (playerId!=-1) {
            return true;
        }
        return false;
    }
    public static int getPlayerId() {
        return playerId;
    }

    public static void setPlayerId(int playerId) {
        ClientBGamesPlayerData.playerId = playerId;
    }

    public static int getPlayerSocialPoints() {
        return playerSocialPoints;
    }

    public static void setPlayerSocialPoints(int playerSocialPoints) {
        ClientBGamesPlayerData.playerSocialPoints = playerSocialPoints;
    }

    public static int getPlayerPhysicalPoints() {
        return playerPhysicalPoints;
    }

    public static void setPlayerPhysicalPoints(int playerPhysicalPoints) {
        ClientBGamesPlayerData.playerPhysicalPoints = playerPhysicalPoints;
    }

    public static int getPlayerLinguisticPoints() {
        return playerLinguisticPoints;
    }

    public static void setPlayerLinguisticPoints(int playerLinguisticPoints) {
        ClientBGamesPlayerData.playerLinguisticPoints = playerLinguisticPoints;
    }

    public static int getPlayerAffectivePoints() {
        return playerAffectivePoints;
    }

    public static void setPlayerAffectivePoints(int playerAffectivePoints) {
        ClientBGamesPlayerData.playerAffectivePoints = playerAffectivePoints;
    }

    public static int getPlayerCognitivePoints() {
        return playerCognitivePoints;
    }

    public static void setPlayerCognitivePoints(int playerCognitivePoints) {
        ClientBGamesPlayerData.playerCognitivePoints = playerCognitivePoints;
    }

    public static String getPlayerEmail() {
        return playerEmail;
    }

    public static void setPlayerEmail(String playerEmail) {
        ClientBGamesPlayerData.playerEmail = playerEmail;
    }

    public static String getPlayerPassword() {
        return playerPassword;
    }

    public static void setPlayerPassword(String playerPassword) {
        ClientBGamesPlayerData.playerPassword = playerPassword;
    }
}
