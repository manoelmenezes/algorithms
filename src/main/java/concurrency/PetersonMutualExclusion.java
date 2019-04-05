package concurrency;

/**
 * Mutual exclusion algorithm implemented by Peterson.
 */
public class PetersonMutualExclusion implements Mutex {

    private static final int MAX = 100;

    private boolean[] flag;

    private int turn;

    private int answer;

    public PetersonMutualExclusion() {
        flag = new boolean[2];
        turn = 0;
        answer = 0;
    }

    public static void main(String[] args) throws InterruptedException {
        PetersonMutualExclusion mutex = new PetersonMutualExclusion();

        Runnable runable1 = () -> mutex.run(0);
        Runnable runable2 = () -> mutex.run(1);

        Thread thread1 = new Thread(runable1);
        Thread thread2 = new Thread(runable2);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(mutex.getMsg());

    }

    /**
     * Executed before entering critical section
     *
     * @param self - int value indicating wgich thread is executing.
     */
    @Override
    public void lock(int self) {
        // set flag[self] = true saying you want to acquire the lock
        flag[self] = true;

        // But first give the other thread the change to acquire the lock
        turn = 1 - self;

        // wait until the other thread looses the desire to acquire the lock
        // or it is your turn to acquire the lock.
        while (flag[1 - self] && turn == 1 - self) ;
    }

    /**
     * Executed before leaving the critical section.
     *
     * @param self - int value indicating wgich thread is executing.
     */
    @Override
    public void unlock(int self) {
        // You do not desire to acquire the lock in future, This will allow the other
        // thread to acquire the lock.
        flag[self] = false;
    }

    public void run(int thread) {
        if (thread < 0 || thread >= 2) {
            throw new IllegalArgumentException("Thread value must be 0 or 1");
        }

        lock(thread);

        for (int i = 0; i < MAX; i++) {
            answer++;
        }

        unlock(thread);
    }

    public int getAnswer() {
        return answer;
    }

    public int getExpected() {
        return MAX * 2;
    }

    public String getMsg() {
        boolean isCorrect = getExpected() == getAnswer();
        String msg = String.format("Expected %d, actual % d, is correct %b", getExpected(), getAnswer(), isCorrect);
        return msg;
    }


}
