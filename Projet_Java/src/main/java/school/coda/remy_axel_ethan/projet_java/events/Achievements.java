package school.coda.remy_axel_ethan.projet_java.events;

public class Achievements {
    DataBase db;

    public Achievements(){
        this.db = new DataBase();
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

    public String achievements(int shots){
        String thirtySixShots = thirtySixShots(shots);
        if(thirtySixShots != null){return thirtySixShots;}

        String victoriesAchievement = numberOfVictories();
        if(victoriesAchievement != null){return victoriesAchievement;}

        String gamesAchievement = numberOfGames();
        if(gamesAchievement != null){return gamesAchievement;}

        return null;
    }
}
