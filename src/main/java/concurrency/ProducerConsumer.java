package concurrency;

public final class ProducerConsumer {


    public static void main(String[] args) throws InterruptedException {
        Mutex mutex = new PetersonMutualExclusion();
        Buffer buffer = new BufferArray();
        Runnable producer = new BufferProducer(0, mutex, buffer);
        Runnable consumer = new BufferConsumer(0, mutex, buffer);

        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);

        producerThread.start();
        consumerThread.start();

        producerThread.join();
        consumerThread.join();
    }

}
