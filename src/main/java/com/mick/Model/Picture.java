package com.mick.Model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "pictures")
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pic_id")
    private int id;

    @Column(name = "pic_src")
    private String source;

    @Column(name = "pic_name")
    private String name;

    @Column(name = "pic_size")
    private Long size;

    @Column(name = "height")
    private Integer height;

    @Column(name = "width")
    private Integer width;

    @Column(name = "hash")
    private int hash;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "picture")
    private Set<Thread> threads;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "picture")
    private Set<Post> posts;


    public Picture() { }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHash() {
        return hash;
    }

    public void setHash(int hash) {
        this.hash = hash;
    }

    public Set<Thread> getThreads() {
        return threads;
    }

    public void setThreads(Set<Thread> threads) {
        this.threads = threads;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public String getFullPath() { return source + name; }

    @Override
    public int hashCode() {
        int hash = 31 *        height.hashCode();
        hash =     31 * hash + width.hashCode();
        hash =     31 * hash + size.hashCode();
        return Math.abs(hash);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Picture)) return false;

        Picture pic = (Picture) obj;

        if (!height.equals(pic.height)) return false;
        if (!width.equals(pic.width)) return false;
        return size.equals(pic.size);
    }
}
