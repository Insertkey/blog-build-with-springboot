package xyz.iknown.jdbc.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class File {
    @Id
    @GeneratedValue
    private Integer id;

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
     * 文章简介
     */
    @Column
    private String shortIntroduction;

    /**
     * 文章分类
     */
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    /**
     * 文章标签
     */
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(joinColumns=@JoinColumn(name="file_id"),inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags=new HashSet<>();

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getShortIntroduction() {
        return shortIntroduction;
    }

    public void setShortIntroduction(String shortIntroduction) {
        this.shortIntroduction = shortIntroduction;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
