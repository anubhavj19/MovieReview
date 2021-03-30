import java.time.LocalDate;
import java.util.*;

public class MovieReview {

    private Map<String, User> users;
    private Map<String, Movie> movies;
    private Map<List<String>, Review> reviews;

    public MovieReview() {
        this.users = new HashMap<>();
        this.movies = new HashMap<>();
        this.reviews = new HashMap<>();
    }

    public static final int currentYear = LocalDate.now().getYear();
    public static final int minReviewScore = 0;
    public static final int maxReviewScore = 10;

    public int getTotalUserCount() {
        return users.size();
    }

    public int getTotalMovieCount() {
        return movies.size();
    }

    public int getTotalReviewCount() {
        return reviews.size();
    }

    public void addMovie(String movieTitle, int releaseYear, List<String> movieGenres) {
        if (movieExists(movieTitle)) {
            System.out.println("Movie already exists. Skipped.");
            return;
        }

        movies.put(movieTitle.toLowerCase(), new Movie(movieTitle, releaseYear, movieGenres));
    }

    public void addUser(String userName) {
        if (userExists(userName)) {
            System.out.println("User already exists. Skipped.");
            return;
        }

        users.put(userName.toLowerCase(), new User(userName));
    }

    public void addReview(String userName, String movieTitle, int reviewScore) {
        try {
            if (!movieExists(movieTitle)) {
                throw new Exception("Missing Movie in the database.");
            } else if (!userExists(userName))  {
                throw new Exception("Missing User in the database.");
            } else if (!isMovieReleased(movieTitle)) {
                throw new Exception("Movie has not released yet.");
            } else if (reviewExists(userName, movieTitle)) {
                throw new Exception("Multiple reviews for the same movie are not allowed for a user.");
            } else if (reviewScore < minReviewScore || reviewScore > maxReviewScore) {
                throw new Exception("Review score must be between 0 and 10.");
            } else {
                User user = getUser(userName);

                if (user.getUserLevel() == User.Level.Critic) {
                    reviewScore *= 2;
                }

                user.incrementReviewCount();
                reviews.put(Arrays.asList(userName.toLowerCase(), movieTitle.toLowerCase()), new Review(userName, movieTitle, reviewScore));
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    public void topNMoviesByCriticReviewScoreInAGenre(int n, String genre) {
        Map<String, Integer> moviesInGenre = new HashMap<>();

        //Getting movies in the genre provided
        try {
            if (reviews.size() == 0) {
                throw new Exception("There are no saved reviews in the database.");
            }

            for (Review review : reviews.values()) {
                String movieTitle = review.getMovieTitle();
                Movie movie = movies.get(movieTitle.toLowerCase());

                if (movie.getMovieGenres().stream().anyMatch(genre::equalsIgnoreCase)) {
                    moviesInGenre.put(movieTitle, 0);
                    User user = users.get(review.getUserName().toLowerCase());

                    if (user.getUserLevel() == User.Level.Critic) {
                        int previousScore = moviesInGenre.get(movieTitle);
                        int currentScore = review.getScore();
                        moviesInGenre.put(movieTitle, previousScore + currentScore);
                    }
                }
            }

            if (moviesInGenre.size() == 0) {
                throw new Exception("There are no movies in that genre.");
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        //Sorting the movies in the genre based on review score by critics
        Map<String, Integer> moviesSortedByCriticReviews = sortByValue(moviesInGenre);

        //Printing the list from highest review score to lowest.
        //If the number of movies requested by user is more that the total movies in that
        //genre, all of them would be returned else only the requested number would be
        //returned

        if (n >= moviesSortedByCriticReviews.size()) {
            System.out.println("There are only " + moviesSortedByCriticReviews.size() + " movies in this genre:\n");
            System.out.println("Movie" + " Score");
            for (Map.Entry movie : moviesSortedByCriticReviews.entrySet()) {
                System.out.print(movie.getKey().toString() + " " + movie.getValue().toString() + "\n");
            }
        } else {
            List<String> movieNames = new ArrayList(moviesSortedByCriticReviews.keySet());
            List<Integer> movieScores = new ArrayList(moviesSortedByCriticReviews.values());
            System.out.println("Movie" + " Score");

            for (int i = 0; i < n; i++) {
                System.out.print(movieNames.get(i).toString() + " " + movieScores.get(i).toString() + "\n");
            }
        }
    }

    public int averageReviewScoreForAYear(int year) {
        int totalScore = 0;
        int movieCountInYear = 0;

        try {
            if (reviews.size() == 0) {
                throw new Exception("There are no saved reviews in the database.");
            }

            for (Review review : reviews.values()) {
                Movie movie = movies.get(review.getMovieTitle().toLowerCase());

                if (movie.getReleaseYear() == year) {
                    totalScore += review.getScore();
                    movieCountInYear++;
                }
            }

            if (movieCountInYear == 0) {
                throw new Exception("There are no movies released in that year.");
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

        int averageScore = totalScore / movieCountInYear;
        return averageScore;
    }

    public int averageReviewScoreForAMovie(String movieTitle) {
        int totalScore = 0;
        int movieOccurrences = 0;

        try {
            if (reviews.size() == 0) {
                throw new Exception("There are no saved reviews in the database.");
            }

            for (List<String> key : reviews.keySet()) {
                if (key.contains(movieTitle.toLowerCase())) {
                    totalScore += reviews.get(key).getScore();
                    movieOccurrences++;
                }
            }

            if (movieOccurrences == 0) {
                throw new Exception("There are no reviews for that movie in the database.");
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

        int averageScore = totalScore / movieOccurrences;
        return averageScore;
    }

    private boolean userExists(String userName) {
        return users.containsKey(userName.toLowerCase());
    }

    private boolean movieExists(String movieTitle) {
        return movies.containsKey(movieTitle.toLowerCase());
    }

    private boolean reviewExists(String userName, String movieTitle) {
        return reviews.containsKey(Arrays.asList(userName.toLowerCase(), movieTitle.toLowerCase()));
    }

    private User getUser(String userName) {
        return users.get(userName.toLowerCase());
    }

    private boolean isMovieReleased(String movieTitle) {
        Movie movie = movies.get(movieTitle.toLowerCase());

        if (movie.getReleaseYear() <= currentYear) {
            return true;
        }

        return false;
    }

    // Method to sort HashMap entries in descending order of values
    private Map<String, Integer> sortByValue(Map<String, Integer> moviesInGenre) {
        List<Map.Entry<String, Integer>> list =
                new LinkedList<Map.Entry<String, Integer> >(moviesInGenre.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2)
            {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();

        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }

        return temp;
    }
}
