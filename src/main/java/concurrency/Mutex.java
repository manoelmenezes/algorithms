package concurrency;

public interface Mutex {

    void lock(int self);

    void unlock(int self);

}