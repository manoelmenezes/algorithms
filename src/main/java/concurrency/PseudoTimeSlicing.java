package concurrency;

public class PseudoTimeSlicing implements Runnable {

    private static final String
            JAVA_VERSION = System.getProperty("java.version"),
            JAVA_VENDOR = System.getProperty("java.vendor"),
            OS_NAME = System.getProperty("os.name"),
            OS_ARCH = System.getProperty("os.arch"),
            OS_VERSION = System.getProperty("os.version");
    private static Thread me = null;
    private static int timeSlice;

    public PseudoTimeSlicing() {
        this(100);
    }

    public PseudoTimeSlicing(int ts) {
// See http://www.javaworld.com/javaworld/jw-04-1999/
// jw-04-toolbox_p.html for the correct way to do singletons
// in a multithreaded situation.
        if (me == null) {
            System.out.println("Java version=" + JAVA_VERSION
                    + "\nJava vendor=" + JAVA_VENDOR
                    + "\nOS name=" + OS_NAME + "\nOS arch=" + OS_ARCH
                    + "\nOS version=" + OS_VERSION);
            if (OS_NAME.equals("Solaris") ||
                    (OS_NAME.equals("Linux") &&
                            !JAVA_VENDOR.startsWith("IBM"))) {
                timeSlice = ts;
                me = new Thread(this);
                me.setPriority(Thread.MAX_PRIORITY);
                me.setDaemon(true);
                me.start();
                System.out.println("PseudoTimeSlicing installed");
            } else
                System.out.println("No PseudoTimeSlicing needed");
        } else System.out.println(
                "PseudoTimeSlicing already installed");
    }

    public static void main(String[] args) {
        new PseudoTimeSlicing();
    }

    public void run() {
        if (Thread.currentThread() != me) return;
// this highest-priority thread waking up sends the
// currently executing thread back to the runnable set
        while (true) {
            try {
                Thread.sleep(timeSlice);
            } catch (InterruptedException e) { /* ignored */ }
        }
    }
}