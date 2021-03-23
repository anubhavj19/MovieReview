import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        MovieReview movieReview = new MovieReview();
        movieReview.addMovie("Lunchbox", 2021, Arrays.asList("Drama"));
        movieReview.addMovie("Guru", 2006, Arrays.asList("Drama"));
        movieReview.addMovie("Metro", 2006, Arrays.asList("Romance"));

        movieReview.addUser("SRK");
        movieReview.addUser("Salman");
        movieReview.addUser("Deepika");

        movieReview.addReview("Deepika", "Lunchbox", 20);
        movieReview.addReview("SRK", "Tiger", 5);
        movieReview.addReview("SRK", "Metro", 20);
        movieReview.addReview("Salman", "Don", 5);
    }
}
