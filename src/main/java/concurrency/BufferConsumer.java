package concurrency;

public final class BufferConsumer implements Consumer, Runnable {

    private final int id;

    private final Mutex mutex;

    private final Buffer buffer;

    public BufferConsumer(final int id, final Mutex mutex, final Buffer buffer) {
        this.id = id;
        this.mutex = mutex;
        this.buffer = buffer;
    }

    @Override
    public void consume() {
        mutex.lock(id);

        System.out.println("Consumer: " + new String(buffer.getBuffer()));

        mutex.unlock(id);
    }

    @Override
    public void run() {
        while (true) {
            consume();
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                // ignored
            }
        }
    }

}