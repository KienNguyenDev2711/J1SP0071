package org.kiennguyenfpt.j1sp0071.main;

import org.kiennguyenfpt.j1sp0071.service.TaskServiceImpl;
import org.kiennguyenfpt.j1sp0071.view.ConsoleView;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class J1Sp0071Application {
    public static void main(String[] args) {
        ConsoleView view = new ConsoleView(new TaskServiceImpl());
        view.run();
    }
}
