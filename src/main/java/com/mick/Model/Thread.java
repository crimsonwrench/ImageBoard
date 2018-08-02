package com.mick.Model;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "threads")
public class Thread {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "thread_id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @Column(name = "title", length = 50)
    private String title;

    @Column(name = "text", length = 999)
    private String text;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "thread", orphanRemoval = true)
    private List<Post> replies;

    @CreationTimestamp
    @Column(name = "last_reply_date", updatable = false)
    private Date date;

    @Transient
    private List<Post> lastPosts;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pic_id")
    private Picture picture;

    public Thread() { }

    public int getId() { return id; }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() { return text; }

    public void setText(String text) { this.text = text; }

    public List<Post> getReplies() {
        return replies;
    }

    public void setReplies(List<Post> replies) {
        this.replies = replies;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Post> getLastPosts() { return lastPosts; }

    public void setLastPosts(List<Post> lastPosts) { this.lastPosts = lastPosts; }

    public Picture getPicture() { return picture; }

    public void setPicture(Picture picture) { this.picture = picture; }

    public String getFormattedDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy E HH:mm:ss");
        return formatter.format(date);
    }
}
