public class Review {

    private String userName;

    private String movieTitle;

    private int reviewScore;

    public Review(String userName, String movieTitle, int reviewScore) {
        this.userName = userName;
        this.movieTitle = movieTitle;
        this.reviewScore = reviewScore;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getMovieTitle() {
        return this.movieTitle;
    }
}
