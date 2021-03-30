import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        MovieReview movieReview = new MovieReview();

        movieReview.addMovie("Don", 2006, Arrays.asList("Action", "Comedy"));
        movieReview.addMovie("Tiger", 2008, Arrays.asList("Drama"));
        movieReview.addMovie("Padmaavat", 2006, Arrays.asList("Comedy"));
        movieReview.addMovie("Lunchbox", 2022, Arrays.asList("Drama"));
        movieReview.addMovie("Guru", 2006, Arrays.asList("Drama"));
        movieReview.addMovie("Metro", 2006, Arrays.asList("Romance"));

        movieReview.addUser("SRK");
        movieReview.addUser("Salman");
        movieReview.addUser("Deepika");

        movieReview.addReview("SRK", "Don", 2);
        movieReview.addReview("SRK", "Padmaavat", 8);
        movieReview.addReview("Salman", "Don", 5);
        movieReview.addReview("Deepika", "Don", 9);
        movieReview.addReview("Deepika", "Guru", 6);
        movieReview.addReview("SRK", "Don", 10);
        movieReview.addReview("Deepika", "Lunchbox", 5);
        movieReview.addReview("SRK", "Tiger", 5);
        movieReview.addReview("SRK", "Metro", 7);

        System.out.println("Average score in year 2006: " + movieReview.averageReviewScoreForAYear(2006));
        System.out.println("Average score of Don: " + movieReview.averageReviewScoreForAMovie("Don"));
        movieReview.topNMoviesByCriticReviewScoreInAGenre(5, "drama");
    }
}
