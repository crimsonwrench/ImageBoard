package com.mick.Model;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "post_id")
    private int postId;

    @Column(name = "post_text", length = 999)
    private String postText;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "thread_id")
    private Thread thread;

    @CreationTimestamp
    @Column(name = "post_date", updatable = false)
    private Date postDate;

    @OneToOne
    @JoinColumn(name = "pic_id")
    private Picture picture;

    public Post() { }

    public int getPostId() { return postId; }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) { this.picture = picture; }

    public String getFormattedDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy E HH:mm:ss");
        return formatter.format(postDate);
    }
}
