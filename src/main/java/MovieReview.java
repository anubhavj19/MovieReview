import java.util.ArrayList;
import java.util.List;

public class MovieReview {

    private List<User> userList = new ArrayList<>();
    private List<Movie> movieList = new ArrayList<>();
    private List<Review> reviewList = new ArrayList<>();
    public static final int currentYear = 2020;

    public void addMovie(String movieTitle, int releaseYear, List<String> movieGenres) {
        if (movieExists(movieTitle)) {
            System.out.println("Movie already exists. Skipped.");
            return;
        }

        movieList.add(new Movie(movieTitle, releaseYear, movieGenres));
    }

    public void addUser(String userName) {
        if (userExists(userName)) {
            System.out.println("User already exists. Skipped.");
            return;
        }

        userList.add(new User(userName));
    }

    public void addReview(String userName, String movieTitle, int reviewScore) {
        try {
            if (!movieExists(movieTitle) || !userExists(userName)) {
                throw new Exception("Missing User and/or Movie in the database.");
            } else if (!isMovieReleased(movieTitle)) {
                throw new Exception("Movie has not released yet.");
            } else if (reviewExists(movieTitle, userName)) {
                throw new Exception("Multiple reviews for the same movie are not allowed.");
            } else {
                User user = getUser(userName);
                user.incrementReviewCount();

                if (user.getReviewCount() == 3) {
                    user.upgradeLevel();
                    System.out.println("User " + user.getUserName() + "is now a critic.");
                }

                try {
                    if (reviewScore < 0 || reviewScore > 10) {
                        throw new Exception("Review score must be between 0 and 10.");
                    }

                    if (user.getReviewCount() > 3) {
                        reviewScore *= 2;
                    }

                    reviewList.add(new Review(userName, movieTitle, reviewScore));
                } catch (Exception ex) {
                    System.out.println(ex.toString());
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    public boolean userExists(String userName) {
        for (User user : userList) {
            if (user.getUserName().equalsIgnoreCase(userName)) {
                return true;
            }
        }

        return false;
    }

    public boolean movieExists(String movieTitle) {
        for (Movie movie : movieList) {
            if (movie.getMovieTitle().equalsIgnoreCase(movieTitle)) {
                return true;
            }
        }

        return false;
    }

    public boolean reviewExists(String movieTitle, String userName) {
        for (Review review : reviewList) {
            if (review.getMovieTitle().equalsIgnoreCase(movieTitle) && review.getUserName().equalsIgnoreCase(userName)) {
                return true;
            }
        }

        return false;
    }

    public User getUser(String userName) {
        for (User user : userList) {
            if (user.getUserName().equalsIgnoreCase(userName)) {
                return user;
            }
        }

        return null;
    }

    public boolean isMovieReleased(String movieTitle) {
        for (Movie movie : movieList) {
            if (movie.getMovieTitle().equalsIgnoreCase(movieTitle)) {
                if (movie.getReleaseYear() <= currentYear) {
                    return true;
                }
            }
        }

        return false;
    }
}
