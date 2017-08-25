package uni.kassel.marsel.fratcher.interaction;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "LikeStatus")
public class Like {

    @Id
    @GeneratedValue
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    private Long giver;

    private Long taker;


    public void setGiver(Long giver) {
        this.giver = giver;
    }

    public Long getGiver() {
        return giver;
    }

    public void setTaker(Long taker) {
        this.taker = taker;
    }

    public Long getTaker() {
        return taker;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Like like = (Like) o;

        return id != null ? id.equals(like.id) : like.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }



}
