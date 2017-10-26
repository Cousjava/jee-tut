package com.sleightholme.jeetut.jms;

import java.io.Serializable;

/**
 *
 * @author jonathan coustick
 */
public class TopicMessage implements Serializable {

    private static int created = 0;

    private final String message;
    private final int id;
    private final Class origin;

    public TopicMessage(String message, Class origin) {
        this.message = message;
        created++;
        id = created;
        this.origin = origin;
    }

    public String getMessage() {
        return message;
    }

    public int getId() {
        return id;
    }

    public Class getOrigin() {
        return origin;
    }

    @Override
    public String toString() {
        return "TopicMessage {" + "message=" + message + ", id=" + id + ", origin=" + origin + '}';
    }

    
}
