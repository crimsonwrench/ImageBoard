package com.mick.Model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "boards")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private int id;

    @Column(name = "board_name")
    private String boardName;

    @Column(name = "board_reference")
    private String boardRef;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "board", orphanRemoval = true)
    private List<Thread> threads;

    public Board() { }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public String getBoardRef() { return boardRef; }

    public void setBoardRef(String boardRef) { this.boardRef = boardRef; }

    public List<Thread> getThreads() {
        return threads;
    }

    public void setThreads(List<Thread> threads) {
        this.threads = threads;
    }

    public String getBoardTitle() { return "/" + boardRef + "/ - " + boardName; }
}
