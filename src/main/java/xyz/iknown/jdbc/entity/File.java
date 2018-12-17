package xyz.iknown.jdbc.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class File {
    @Id
    @GeneratedValue
    private long id;

    @Column
    private String articleName;

    /**
     * 文件路径
     */
    @Column
    private String fullPath;

    /**
     * 创建时间
     */
    @Column
    private long createTime;

    /**
     * 上一次修改时间
     */
    @Column
    private long lastEditTime;

    /**
     * 文章标签
     */
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(joinColumns=@JoinColumn(name="file_id"),inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags=new HashSet<>();


    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", articleName='" + articleName + '\'' +
                ", fullPath='" + fullPath + '\'' +
                ", createTime=" + createTime +
                ", lastEditTime=" + lastEditTime +
                ", tags=" + tags +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(long lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

}