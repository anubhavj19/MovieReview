public class Review {

    private String userName;

    private String movieTitle;

    private int score;

    public Review(String userName, String movieTitle, int score) {
        this.userName = userName;
        this.movieTitle = movieTitle;
        this.score = score;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getMovieTitle() {
        return this.movieTitle;
    }

    public int getScore() {
        return this.score;
    }
}
