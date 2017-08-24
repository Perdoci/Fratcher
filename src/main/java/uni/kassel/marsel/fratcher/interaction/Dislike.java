package uni.kassel.marsel.fratcher.interaction;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Dislike {

    private Long id;

    private Long giver;

    private Long taker;

    public void Dislike(){

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setTaker(Long taker) {
        this.taker = taker;
    }

    public Long getTaker() {
        return taker;
    }

    public void setGiver(Long giver) {
        this.giver = giver;
    }

    public Long getGiver() {
        return giver;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Dislike dislike = (Dislike) o;

        return id != null ? id.equals(dislike.id) : dislike.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}
