import java.util.HashSet;
import java.util.List;

public class Movie {

    private String movieTitle;

    private enum Status {
        Released,
        Upcoming
    }

    private List<String> movieGenres;

    private int releaseYear;

    private int totalReviewScore;

    public String getMovieTitle() {
        return this.movieTitle;
    }

    public Movie(String movieTitle, int releaseYear, List<String> movieGenres) {
        this.movieTitle = movieTitle;
        this.releaseYear = releaseYear;
        this.movieGenres = movieGenres;
    }

    public int getReleaseYear() {
        return this.releaseYear;
    }
}
