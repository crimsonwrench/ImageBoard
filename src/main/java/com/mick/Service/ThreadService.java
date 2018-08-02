package com.mick.Service;

import com.mick.Model.Picture;
import com.mick.Model.Thread;
import com.mick.Repository.ThreadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThreadService {

    private ThreadRepository threadRepository;

    @Autowired
    public ThreadService(ThreadRepository threadRepository) {
        this.threadRepository = threadRepository;
    }

    public List<Thread> loadThreads(String boardRef) { return threadRepository.findAllByBoard_BoardRef(boardRef); }

    public void createThread(Thread thread){ threadRepository.save(thread);}

    public Thread findById(int id) { return threadRepository.findById(id); }

    public void deleteById(int id) { threadRepository.deleteById(id); }

}
