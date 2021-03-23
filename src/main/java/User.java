public class User {

    private String userName;

    private enum Level {
        Viewer,
        Critic;

        public Level getNext() {
            return this.ordinal() < Level.values().length - 1 ? Level.values()[this.ordinal() + 1] : null;
        }
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
        this.reviewCount++;
    }

    public Level getUserLevel() {
        return this.userLevel;
    }

    public void upgradeLevel() {
        if (this.userLevel == Level.Critic) {
            return;
        }

        this.userLevel = this.getUserLevel().getNext();
    }

    public User(String userName) {
        this.userName = userName;
        this.userLevel = Level.Viewer;
        this.reviewCount = 0;
    }
}
