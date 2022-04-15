package site.xddongx.auth.model;

import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;
import java.time.Instant;

@RedisHash("user_cache")
public class UserHash {
    @Id
    private String cookieId;
    private String username;
    private Instant longinTimestamp;

    public UserHash() {

    }

    public UserHash(String cookieId, String username, Instant longinTimestamp) {
        this.cookieId = cookieId;
        this.username = username;
        this.longinTimestamp = longinTimestamp;
    }

    public String getCookieId() {
        return cookieId;
    }

    public void setCookieId(String cookieId) {
        this.cookieId = cookieId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Instant getLonginTimestamp() {
        return longinTimestamp;
    }

    public void setLonginTimestamp(Instant longinTimestamp) {
        this.longinTimestamp = longinTimestamp;
    }

    @Override
    public String toString() {
        return "UserHash{" +
                "cookieId='" + cookieId + '\'' +
                ", username='" + username + '\'' +
                ", longinTimestamp=" + longinTimestamp +
                '}';
    }
}
