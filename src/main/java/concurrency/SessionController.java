package concurrency;

import java.util.HashMap;
import java.util.Map;

interface Task {
    void run();

    String name();
}

public class SessionController {

    private Session session;

    public SessionController(Session session) {
        this.session = session;
    }

    public static void main(String[] args) throws InterruptedException {
        SessionController sessionController = new SessionController(new Session());

        WordThread wordThread = new WordThread(sessionController);
        AdobeThread adobeThread = new AdobeThread(sessionController);

        wordThread.start();
        adobeThread.start();

        wordThread.join();
        adobeThread.join();
    }

    public void runTask(Task task) {
        synchronized (session) {
            if (session.containsTask(task.name())) {
                System.out.println("An instance is running");
            } else {
                session.runTask(task);
            }
        }
    }
}

class AdobeThread extends Thread {
    private SessionController sessionController;

    public AdobeThread(SessionController sessionController) {
        this.sessionController = sessionController;
    }

    public void run() {
        while (true) {
            sessionController.runTask(new Adobe());
        }
    }
}

class WordThread extends Thread {
    private SessionController sessionController;

    public WordThread(SessionController sessionController) {
        this.sessionController = sessionController;
    }

    public void run() {
        while (true) {
            sessionController.runTask(new Word());
        }
    }
}

class Session {
    private Map<String, Task> tasks = new HashMap<>();

    public boolean containsTask(String name) {
        return tasks.containsKey(name);
    }

    public void runTask(Task task) {
        tasks.put(task.name(), task);
        task.run();
    }
}

class Adobe implements Task {
    public String name() {
        return "Adobe";
    }

    public void run() {
        System.out.println(name() + " has started");
    }
}

class Word implements Task {
    public String name() {
        return "Word";
    }

    public void run() {
        System.out.println(name() + "has started");
    }
}

