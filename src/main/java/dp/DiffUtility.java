package dp;

public class DiffUtility {

    private String X;

    private String Y;

    private int m;

    private int n;

    public DiffUtility(String X, String Y) {
        this.X = X;
        this.Y = Y;
        this.m = X.length();
        this.n = Y.length();
    }

    /**
     *              X           M             J           Y
     *              -X          -X-M         -X-M-J      -X-M-J-Y
     *  Y +Y        -X+Y        -X+Y-M       -X+Y-M-J    -X-M-JY
     *  M +Y+M      -X+Y+M      -X+YM        -X+YM-J     -X+YM-J-Y
     *  J +Y+M+J    -X+Y+M+J    -X+YM+J
     *  Z +Y+M+J+Z
     */
    public void diff() {

    }
}
