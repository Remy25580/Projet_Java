package school.coda.remy_axel_ethan.projet_java.events;

import school.coda.remy_axel_ethan.projet_java.game.GameController;

public class Achievements {
    DataBase db;
    GameController controller;

    public Achievements(){
        this.db = new DataBase();
        this.controller = new GameController();
    }

    public String numberOfGames(){
        int nb = db.gamesCounter();
        return switch (nb) {
            case 10 -> "Félicitations! Vous avez joué 10 parties";
            case 50 -> "Félicitations! Vous avez joué 50 parties";
            case 100 -> "Félicitations! Vous avez joué 100 parties";
            default -> null;
        };
    }

    public String numberOfVictories(){
        int nb = db.victoriesCounter();
        return switch (nb) {
            case 1 -> "Félicitations! Vous avez gagné votre première partie!";
            case 10 -> "Félicitations! Vous avez gagné 10 parties!";
            case 25 -> "Félicitations! Vous avez gagné 25 parties!";
            case 50 -> "Félicitations! Vous avez gagné 50 parties!";
            case 67 -> "Félicitations! Vous avez gagné 67 parties!";
            case 100 -> "Félicitations! Vous avez gagné 100 parties! Quel maître !";
            default -> null;
        };
    }

    public String thirtySixShots(int shots){
        if(shots <= 36){return "Félicitations! Vous avez gagné en moins de 36 coups!";}
        return null;
    }

    public void achievements(int shots){
        String thirtySixShots = thirtySixShots(shots);
        if(thirtySixShots != null){controller.showAchievement(thirtySixShots);}

        String victoriesAchievement = numberOfVictories();
        if(victoriesAchievement != null){controller.showAchievement(victoriesAchievement);}

        String gamesAchievement = numberOfGames();
        if(gamesAchievement != null){controller.showAchievement(gamesAchievement);}
    }
}
