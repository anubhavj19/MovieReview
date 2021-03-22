import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Main {

    public static List<User> userList = new ArrayList<>();
    public static List<Movie> movieList = new ArrayList<>();
    public static List<Review> reviewList = new ArrayList<>();

    public static void main(String[] args) {

    }

    public static void addMovie(String movieTitle, int releaseYear, List<String> movieGenres) {
        if (movieExists(movieTitle)) {
            System.out.println("Movie already exists. Skipped.");
            return;
        }

        movieList.add(new Movie(movieTitle, releaseYear, movieGenres));
    }

    public static void addUser(String userName) {
        if (userExists(userName)) {
            System.out.println("User already exists. Skipped.");
            return;
        }

        userList.add(new User(userName));
    }

    public static void addReview(String userName, String movieTitle, int reviewScore) throws Exception {
        if (!movieExists(movieTitle) || !userExists(userName)) {
            throw new Exception("Missing User and/or Movie in the database.");
        }

        if (reviewExists(movieTitle, userName)) {
            throw new Exception("Multiple reviews not allowed.");
        }

        if (userExists(userName)) {
            User user = getUser(userName);

            if ()
        }

        reviewList.add(new Review(userName, movieTitle, reviewScore));
    }

    public static boolean userExists(String userName) {
        for (User user : userList) {
            if (user.getUserName().equalsIgnoreCase(userName)) {
                return true;
            }
        }

        return false;
    }

    public static boolean movieExists(String movieTitle) {
        for (Movie movie : movieList) {
            if (movie.getMovieTitle().equalsIgnoreCase(movieTitle)) {
                return true;
            }
        }

        return false;
    }

    public static boolean reviewExists(String movieTitle, String userName) {
        for (Review review : reviewList) {
            if (review.getMovieTitle().equalsIgnoreCase(movieTitle) && review.getUserName().equalsIgnoreCase(userName)) {
                return true;
            }
        }

        return false;
    }

    public static User getUser(String userName) {
        for (User user : userList) {
            if (user.getUserName().equalsIgnoreCase(userName)) {
                return user;
            }
        }

        return null;
    }
}
