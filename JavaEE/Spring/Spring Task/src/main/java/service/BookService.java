package service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Scheduled(fixedDelay = 3000)
    public void addSchedule() {
        System.out.println("Add book scheduling");
    }

    @Async
    public void updateAsync() {
        sleep();
        System.out.println("Update book asynchronously");
    }

    private void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }
}
