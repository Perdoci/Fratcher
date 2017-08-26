package uni.kassel.marsel.fratcher.message;

import uni.kassel.marsel.fratcher.user.User;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Message {
    public static final int TEXT_LENGTH = 10240;

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(optional = false)
    private User owner;

    private String text;

    private Date timestamp;

    public Message(){
        this.timestamp = new Date(System.currentTimeMillis());
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getOwner() {
        return owner;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Message message = (Message) o;

        return id != null ? id.equals(message.id) : message.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
