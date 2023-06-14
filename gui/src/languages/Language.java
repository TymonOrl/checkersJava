package languages;

public abstract class Language {
    enum LanguageChosen{
        ENGLISH,
        FRENCH,
        POLISH
    }
    LanguageChosen languageChosen;
    protected String play, ranking, exit, resign, player
            , winner, settings, rematch, returnToMenu, load
            ,enterName ,wrongName, save;

    public LanguageChosen getLanguageChosen() {
        return languageChosen;
    }

    public String getPlay() {
        return play;
    }

    public String getRanking() {
        return ranking;
    }

    public String getExit() {
        return exit;
    }

    public String getResign() {
        return resign;
    }

    public String getPlayer() {
        return player;
    }

    public String getWinner() {
        return winner;
    }

    public String getSettings() {
        return settings;
    }

    public String getRematch() {
        return rematch;
    }

    public String getReturnToMenu() {
        return returnToMenu;
    }

    public String getLoad() {
        return load;
    }

    public String getEnterName() {
        return enterName;
    }

    public String getWrongName() {
        return wrongName;
    }

    public String getSave(){ return save;}
}
