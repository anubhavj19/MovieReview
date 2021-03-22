public class User {

    private String userName;

    private enum Level {
        Viewer,
        Critic
    }

    private Level userLevel;

    private int reviewCount;

    public String getUserName() {
        return userName;
    }

    public int getReviewCount() {
        return this.reviewCount;
    }

    public void incrementReviewCount() {
        reviewCount++;
    }

    public User(String userName) {
        this.userName = userName;
        this.userLevel = Level.Viewer;
        this.reviewCount = 0;
    }
}
