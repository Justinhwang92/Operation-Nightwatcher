package Operation_Nightwatcher.Activity.Database;

public class User {
    String id;
    String username;
    int highscore;
    String emoji;

    public User() {

    }
    public User(String id, String username, int highscore, String emoji) {
        this.id = id;
        this.username = username;
        this.highscore = highscore;
        this.emoji = emoji;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }
    public void setEmoji(String emoji) {
        this.emoji = emoji;
    }
    public String getId() {
        return this.id;
    }
    public String getUsername() {
        return this.username;
    }
    public int getHighscore() {
        return this.highscore;
    }
    public String getEmoji() {
        return this.emoji;
    }
}

