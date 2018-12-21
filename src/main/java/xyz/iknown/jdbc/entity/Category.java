package xyz.iknown.jdbc.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Category {
    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String categoryName;

    @Column
    private long lastEditTime;

    @Column
    @OneToMany(mappedBy = "category",fetch = FetchType.EAGER)
    private Set<File> files=new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public long getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(long lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    @JsonBackReference
    public Set<File> getFiles() {
        return files;
    }

    @JsonBackReference
    public void setFiles(Set<File> files) {
        this.files = files;
    }
}
