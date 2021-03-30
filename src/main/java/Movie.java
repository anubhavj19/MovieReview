import java.util.List;

public class Movie {

    private String title;

    private List<String> movieGenres;

    private int releaseYear;

    public String getMovieTitle() {
        return this.title;
    }

    public Movie(String title, int releaseYear, List<String> movieGenres) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.movieGenres = movieGenres;
    }

    public int getReleaseYear() {
        return this.releaseYear;
    }

    public List<String> getMovieGenres() {
        return this.movieGenres;
    }
}
