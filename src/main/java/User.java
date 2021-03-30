public class User {

    //private String userId;
    private String name;

    public enum Level {
        Viewer,
        Critic;

        public Level getNext() {
            return this.ordinal() < Level.values().length - 1 ? Level.values()[this.ordinal() + 1] : null;
        }
    }

    private Level level;

    private int reviewCount;

    public int getReviewCount() {
        return this.reviewCount;
    }

    public void incrementReviewCount() {
        this.reviewCount++;

        if (this.reviewCount  == 3) {
            this.upgradeLevel();
        }
    }

    public Level getUserLevel() {
        return this.level;
    }

    public void upgradeLevel() {
        if (this.level == Level.Critic) {
            return;
        }

        this.level = this.getUserLevel().getNext();
        System.out.println("User " + this.name + " has been upgraded to " + this.level.name() + ".");
    }

    public User(String name) {
        this.name = name;
        this.level = Level.Viewer;
        this.reviewCount = 0;
    }
}
