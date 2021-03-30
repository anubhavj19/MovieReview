import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MovieReviewTest {

    MovieReview movieReview;

    @BeforeEach
    void init() {
        movieReview = new MovieReview();
    }

    @Test
    void addMovie() {
        movieReview.addMovie("Don", 2006, Arrays.asList("Action", "Comedy"));
        movieReview.addMovie("Tiger", 2008, Arrays.asList("Drama"));
        movieReview.addMovie("Padmaavat", 2006, Arrays.asList("Comedy"));
        movieReview.addMovie("Lunchbox", 2021, Arrays.asList("Drama"));
        movieReview.addMovie("Guru", 2006, Arrays.asList("Drama"));
        movieReview.addMovie("Metro", 2006, Arrays.asList("Romance"));
        movieReview.addMovie("Metro", 2007, Arrays.asList("Romance", "Drama")); //repeated movie
        assertEquals(6, movieReview.getTotalMovieCount());
    }

    @Test
    void addUser() {
        movieReview.addUser("SRK");
        movieReview.addUser("Salman");
        movieReview.addUser("Deepika");
        movieReview.addUser("deepika");
        movieReview.addUser("sALman");
        assertEquals(3, movieReview.getTotalUserCount());
    }

    @Test
    void addReview() {
        movieReview.addMovie("Don", 2006, Arrays.asList("Action", "Comedy"));
        movieReview.addMovie("Tiger", 2008, Arrays.asList("Drama"));
        movieReview.addMovie("Padmaavat", 2006, Arrays.asList("Comedy"));
        movieReview.addMovie("Twilight", 2022, Arrays.asList("Comedy"));

        movieReview.addUser("SRK");
        movieReview.addUser("Salman");
        movieReview.addUser("Deepika");

        movieReview.addReview("SRK", "Don", 2);
        movieReview.addReview("srk", "Don", 2); //Case change does not affect the count
        movieReview.addReview("Shahrukh", "Don", 2); //Review won't be added since user does not exist
        movieReview.addReview("Salman", "ABCD", 2); //Review won't be added since movie does not exist
        movieReview.addReview("Salman", "Twilight", 2); //Review won't be added since movie has not released

        assertEquals(1, movieReview.getTotalReviewCount());
    }

    @Test
    void averageReviewScoreForAYear() {
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

        assertEquals(7, movieReview.averageReviewScoreForAYear(2006));
    }

    @Test
    void averageReviewScoreForAMovie() {
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

        assertEquals(5, movieReview.averageReviewScoreForAMovie("don"));
    }
}