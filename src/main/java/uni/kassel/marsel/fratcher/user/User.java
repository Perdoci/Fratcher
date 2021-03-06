package uni.kassel.marsel.fratcher.user;

import uni.kassel.marsel.fratcher.interaction.Dislike;
import uni.kassel.marsel.fratcher.interaction.Like;
import uni.kassel.marsel.fratcher.matches.Match;
import uni.kassel.marsel.fratcher.message.Message;

import javax.persistence.*;
import java.util.List;

@Entity(name = "User_")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String email;

    @Column(name = "user_pass")
    private String userPass;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setUserPass(String password) {
        this.userPass = password;
    }

    public String getUserPass() {
        return userPass;
    }

    public  User(){

    }

    @OneToOne
    private Like like;

    public void setLike(Like like) {
        this.like = like;
    }

    public Like getLike() {
        return like;
    }

    @OneToOne
    private Dislike dislike;

    public void setDislike(Dislike dislike) {
        this.dislike = dislike;
    }

    public Dislike getDislike() {
        return dislike;
    }


    /**
     * Status has to be a pojo. Reason: when somebody likes a status
     * we should get the owner of the status an ask the database if
     * that owner also liked the current user. If so, we open the profile
     * of the user with name email (pic) and the possibility to chat
     * with him. If the owner had no action on the status of the current
     * user than we just save the like with the id of the status owner.
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        return id != null ? id.equals(user.id) : user.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}
