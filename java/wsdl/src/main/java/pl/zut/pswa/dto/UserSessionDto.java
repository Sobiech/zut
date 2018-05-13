package pl.zut.pswa.dto;

import com.google.common.base.Objects;

import pl.zut.pswa.entity.User;
import java.util.Date;
import java.util.UUID;

public class UserSessionDto {

    private String sessionId;

    private User user;

    private Date createdAt;


    public static UserSessionDto Build(User user) {

        UserSessionDto
            dto = new UserSessionDto();
            dto.user = user;
            dto.createdAt = new Date();
            dto.sessionId = UUID.randomUUID().toString().replaceAll("-","");

        return dto;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserSessionDto that = (UserSessionDto) o;
        return Objects.equal(sessionId, that.sessionId);
    }


    @Override
    public int hashCode() {
        return Objects.hashCode(sessionId);
    }


    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
