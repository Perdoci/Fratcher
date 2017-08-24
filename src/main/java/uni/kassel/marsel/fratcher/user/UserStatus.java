package uni.kassel.marsel.fratcher.user;

import javax.persistence.*;

@Entity
public class UserStatus {
    //4x more characters than twitter: 140x4 = 560
    public static final int TITLE_LENGTH = 2048;

    @Id
    @GeneratedValue
    private Long id;

    private String status;

    @OneToOne
    private User owner;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getOwner() {
        return owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserStatus userStatus = (UserStatus) o;

        return id != null ? id.equals(userStatus.id) : userStatus.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
