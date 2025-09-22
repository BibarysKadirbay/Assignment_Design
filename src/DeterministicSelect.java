import static java.util.Collections.swap;

public final class DeterministicSelect {
    public static int select(int[] a, int k) {
        if (a == null) throw new IllegalArgumentException("array is null");
        if (k < 1 || k > a.length) throw new IllegalArgumentException("k out of range");
        return select(a, 0, a.length - 1, k);
    }
    private static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
    private static int select(int[] a, int l, int r, int k) {
        while (true){
            if (l == r) return a[l];
            int i = medianOfMediansIndex(a, l, r);
            int p = partition(a, l, r, i);
            int rank = p - l + 1;
            if (k == rank) {
                return a[p];
            } else if (k < rank){
                r = p - 1;
            } else {
                k -= rank;
                l = p + 1;
            }
        }
    }
    private static int partition(int[] a, int l, int r, int ind) {
        swap(a, ind, r);
        int x = a[r];
        int i = l;
        for (int j = l; j < r; j++) {
            if (a[j] <= x) {
                swap(a, i, j);
                i++;
            }
        }
        swap(a, i, r);
        return i;
    }
    private static int medianOfMediansIndex(int[] a, int l, int r) {
        int n = r - l + 1;
        if (n <= 5) {
            insertion(a, l, r);
            return (l+r) >> 1;
        }
        int numGroups = (n + 4) / 5;
        for (int g = 0; g < numGroups; g++) {
            int gl = l + 5 * g;
            int gr = Math.min(gl + 4, r);
            insertion(a, gl, gr);
            int medianIndex = gl + (gr - gl) / 2;
            swap(a, l + g, medianIndex);
        }
        int kMed = (numGroups + 1) / 2;
        int medVal = select(a, l, l + numGroups - 1, kMed);
        for (int i = l; i < l + numGroups; i++) if (a[i] == medVal) return i;
        return l;
    }
    private static void insertion(int[] a, int l, int r) {
        for (int i = l + 1; i <= r; i++) {
            int x = a[i], j = i - 1;
            while (j >= l && a[j] > x) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = x;
        }
    }
}