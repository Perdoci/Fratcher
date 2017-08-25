package uni.kassel.marsel.fratcher.matches;

import uni.kassel.marsel.fratcher.interaction.Like;
import uni.kassel.marsel.fratcher.message.Message;
import uni.kassel.marsel.fratcher.user.User;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Match {

    @Id
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<User> users;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> messageList;

    private Date timestamp;

    public Match(){
        this.timestamp = new Date(System.currentTimeMillis());
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public void addUser(User user){
        this.users.add(user);
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void addMessage(Message message){
        messageList.add(message);
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Date getTimestamp() {
        return timestamp;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Match match = (Match) o;

        return id != null ? id.equals(match.id) : match.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}
