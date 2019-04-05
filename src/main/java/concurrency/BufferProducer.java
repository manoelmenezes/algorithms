package concurrency;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class BufferProducer implements Producer, Runnable {

    private static final Random RANDOM = new Random();

    private final int id;

    private final Mutex mutex;

    private final Buffer buffer;

    public BufferProducer(final int id, final Mutex mutex, final Buffer buffer) {
        this.id = id;
        this.mutex = mutex;
        this.buffer = buffer;
    }

    @Override
    public void produce() {
        mutex.lock(id);

        int size = buffer.size();

        String str = generateRandom(size);

        buffer.write(str.getBytes());

        System.out.println("Producer: " + str);

        mutex.unlock(id);
    }

    @Override
    public void run() {
        while (true) {
            produce();
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                // ignored
            }
        }
    }

    private String generateRandom(int size) {
        List<Character> chars = IntStream.range(0, size)
                .mapToObj(index -> Character.valueOf((char) (RANDOM.nextInt(5) + 65)))
                .collect(Collectors.toList());

        char[] result = new char[size];
        IntStream.range(0, size)
                .forEach(index -> result[index] = chars.get(index));

        return new String(result);
    }

}