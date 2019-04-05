package concurrency;

public interface Buffer {

    int size();

    byte[] getBuffer();

    void write(byte[] buffer);

}