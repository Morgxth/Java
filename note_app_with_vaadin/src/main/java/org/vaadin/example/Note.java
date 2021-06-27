package org.vaadin.example;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A entity object, like in any other Java application. In a typical real world
 * application this could for example be a JPA entity.
 */
@SuppressWarnings("serial")
public class Note implements Serializable, Cloneable {

    private Long id;

    private String name = "";

    private String content = "";

    private LocalDateTime noteDate;

    private NoteStatus status;

    private String email = "";

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the value of email
     *
     * @return the value of email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the value of email
     *
     * @param email new value of email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the value of status
     *
     * @return the value of status
     */
    public NoteStatus getStatus() {
        return status;
    }

    /**
     * Set the value of status
     *
     * @param status new value of status
     */
    public void setStatus(NoteStatus status) {
        this.status = status;
    }

    /**
     * Get the value of noteDate
     *
     * @return the value of noteDate
     */
    public LocalDateTime getNoteDate() {
        return noteDate;
    }

    /**
     * Set the value of noteDate
     *
     * @param noteDate new value of noteDate
     */
    public void setNoteDate(LocalDateTime noteDate) {
        this.noteDate = noteDate;
    }

    /**
     * Get the value of content
     *
     * @return the value of content
     */
    public String getContent() {
        return content;
    }

    /**
     * Set the value of content
     *
     * @param content new value of content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }

    public boolean isPersisted() {
        return id != null;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (this.id == null) {
            return false;
        }

        if (obj instanceof Note && obj.getClass().equals(getClass())) {
            return this.id.equals(((Note) obj).id);
        }

        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + (id == null ? 0 : id.hashCode());
        return hash;
    }

    @Override
    public Note clone() throws CloneNotSupportedException {
        return (Note) super.clone();
    }

    @Override
    public String toString() {
        return name + " " + content;
    }
}
