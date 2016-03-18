package model.entity.task;

import javax.persistence.*;
import java.util.Date;

/**
 * Date: 18.05.13
 *
 * @author andrey.tkachuk31
 */
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Temporal(TemporalType.DATE)
    private Date date = new Date();

    private Status status;
    private String points;
    private String bestPath;

    public Task() {
    }

    /* Accessors for the fields. JPA doesn't use these, but your application does. */

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getBestPath() {
        return bestPath;
    }

    public void setBestPath(String bestPath) {
        this.bestPath = bestPath;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }
}
