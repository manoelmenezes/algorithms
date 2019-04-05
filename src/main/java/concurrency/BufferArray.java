package concurrency;

public final class BufferArray implements Buffer {

    public static final int SIZE = 4;

    private byte[] buffer;

    public BufferArray() {
        this.buffer = new byte[SIZE];
    }

    @Override
    public int size() {
        return SIZE;
    }

    @Override
    public byte[] getBuffer() {
        return buffer;
    }

    @Override
    public void write(final byte[] buffer) {
        this.buffer = buffer;
    }

}