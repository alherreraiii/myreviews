package herreraa.myreviews;

import android.support.annotation.NonNull;

/**
 * This is the movie class
 *
 * @author Al Herrera
 * @version 2/12/2017
 */
class Game implements Comparable<Game>{

    private String gameTitle;
    private String company;
    private String gameGenre;
    private float gameStars;
    private String gameReview;
    private String gameDateReview;

    /**
     * Default Constructor
     */
    Game() {
    }

    /**
     * Access the game title
     *
     * @return the game title
     */
    String getGameTitle() {
        return gameTitle;
    }

    /**
     * set the title of the game
     *
     * @param gameTitle the title of the game
     */
    void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }

    /**
     * Access the company name
     *
     * @return a string of the company name
     */
    String getCompany() {
        return company;
    }

    /**
     * set the company name
     *
     * @param company the name of the company
     */
    void setCompany(String company) {
        this.company = company;
    }

    /**
     * Access the game genre
     *
     * @return a string of the genre of the game
     */
    String getGameGenre() {
        return gameGenre;
    }

    /**
     * set the game genre
     *
     * @param gameGenre the name off the genre
     */
    void setGameGenre(String gameGenre) {
        this.gameGenre = gameGenre;
    }

    /**
     * Access the game stars
     *
     * @return float of the game stars
     */
    float getGameStars() {
        return gameStars;
    }

    /**
     * Set the game stars
     *
     * @param gameStars number of stars
     */
    void setGameStars(float gameStars) {
        this.gameStars = gameStars;
    }

    /**
     * Access the game review
     *
     * @return a string of the game review
     */
    String getGameReview() {
        return gameReview;
    }

    /**
     * Set the game review
     *
     * @param gameReview text to set the game review
     */
    void setGameReview(String gameReview) {
        this.gameReview = gameReview;
    }

    /**
     * Access the date of the game review
     *
     * @return a string of the date review
     */
    String getGameDateReview() {
        return gameDateReview;
    }

    /**
     * Set the game date reivew
     *
     * @param gameDateReview the game date review
     */
    void setGameDateReview(String gameDateReview) {
        this.gameDateReview = gameDateReview;
    }

    /**
     *  the string for the game
     *
     * @return return the game title
     */
    @Override
    public String toString() {
        return gameTitle;
    }

    /**
     * compare to for the game
     *
     * @param game game object which holds a game
     * @return int for the comparison purposes
     */
    @Override
    public int compareTo(@NonNull Game game) {
            return this.getGameTitle().compareTo(game.gameTitle);
    }
}
