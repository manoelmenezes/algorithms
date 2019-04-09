package matrix;

import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.NonNull;
import lombok.Value;

import java.util.Arrays;
import java.util.stream.IntStream;

@Getter
@Value
public final class Vector {

    private final int n;

    private final int[] x;

    public Vector(@NonNull final int[] x) {
        Preconditions.checkArgument(x.length > 0, "Array cannot be empty");

        this.n = x.length;
        this.x = Arrays.copyOf(x, this.n);
    }

    public boolean isUnitVector(final int i) {
        Preconditions.checkArgument(i >= 0 && i < n, String.format("Parameter must be in the range [0,%d)", n));

        long zeros = IntStream.range(0, n)
                .filter(j -> x[j] == 0)
                .count();

        return x[i] == 1 && zeros == n - 1;
    }

    public int[] getX() {
        return Arrays.copyOf(x, n);
    }


}
