package xyz.iknown.jdbc.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Tag {
    @Id
    @GeneratedValue
    private long id;

    @Column
    private String tag;

    @ManyToMany(mappedBy = "tags")
    private Set<File> files=new HashSet<>();

    public Tag(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Set<File> getFiles() {
        return files;
    }

    public void setFiles(Set<File> files) {
        this.files = files;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", tag='" + tag + '\'' +
                '}';
    }
}
