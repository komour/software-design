package ru.ifmo.komarov.sd3.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Duration;

@Entity
public class Action implements Comparable<Action> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private final Long accountId;
    private final Type type;
    private final String data;
    private final long time;
    private final static long hourMillis = Duration.ofHours(1).toMillis();
    private final static long minMillis = Duration.ofMinutes(1).toMillis();
    private final static long dayMillis = Duration.ofDays(1).toMillis();

    @Override
    public int compareTo(Action other) {
        if (id.equals(other.id)) {
            return 0;
        }
        return id < other.id ? -1 : 1;
    }

    public String toString() {
        return "Account: " + accountId + ", Action: " + type + ", Time: " + getPrettyTime() + " GMT";
    }

    public String getPrettyTime() {
        long day = time / dayMillis;
        long hour = (time - day * dayMillis) / hourMillis;
        long hoursLeft = time / hourMillis;
        long minute = (time -  hoursLeft * hourMillis) / minMillis;
        return hour + ":" + minute;
    }

    protected Action() {
        this.accountId = 0L;
        this.type = Type.None;
        this.data = "";
        this.time = System.currentTimeMillis();
    }

    public Action(Long accountId, Type type, String data) {
        this.accountId = accountId;
        this.type = type;
        this.data = data;
        this.time = System.currentTimeMillis();
    }

    public Action(Long accountId, Type type) {
        this.accountId = accountId;
        this.type = type;
        this.data = "";
        this.time = System.currentTimeMillis();
    }

    public Type getType() {
        return type;
    }

    public String getData() {
        return data;
    }

    public long getTime() {
        return time;
    }

    public enum Type {
        Enter,
        Leave,
        Register,
        AddVisits,
        None
    }
}
