import java.util.*;

public class Main {
    public static void main(String[] args) {
        Random r = new Random(20000);
        for (int n : new int[]{10, 1000, 20000}) {
            int[] arr = randomArray(n, r);
            int[] copy = arr.clone();
            Arrays.sort(copy);
            int[] a1 = arr.clone();
            MergeSort mergesort = new MergeSort();
            mergesort.mergeSort(a1);
            assert Arrays.equals(a1, copy) : "MergeSort failed";
            int[] a2 = arr.clone();
            QuickSort.quickSort(a2);
            assert Arrays.equals(a2, copy) : "QuickSort failed";
        }
        System.out.println("Sorting tests passed.");
        for (int t = 0; t < 100; t++) {
            int n = 100 + r.nextInt(200);
            int[] arr = randomArray(n, r);
            int[] sorted = arr.clone(); Arrays.sort(sorted);
            int k = 1 + r.nextInt(n);
            int val = DeterministicSelect.select(arr, k);
            assert val == sorted[k-1] : "Select failed";
        }
        System.out.println("Select tests passed.");

        for (int n : new int[]{10, 100, 2000}) {
            List<ClosestPairofPoints.Pt> pts = randomPts(n, r);
            double fast = ClosestPairofPoints.solve(pts);
            double slow = brute(pts);
            assert Math.abs(fast - slow) < 1e-9 : "Closest pair mismatch";
        }
        List<ClosestPairofPoints.Pt> big = randomPts(100_000, r);
        double d = ClosestPairofPoints.solve(big);
        assert d >= 0 : "Closest pair failed large n";
        System.out.println("Closest pair tests passed.");
    }
    private static int[] randomArray(int n, Random r) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = r.nextInt();
        return a;
    }
    private static List<ClosestPairofPoints.Pt> randomPts(int n, Random r) {
        ArrayList<ClosestPairofPoints.Pt> pts = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            pts.add(new ClosestPairofPoints.Pt(r.nextDouble(), r.nextDouble()));
        }
        return pts;
    }
    private static double brute(List<ClosestPairofPoints.Pt> pts) {
        double best = Double.POSITIVE_INFINITY;
        for (int i = 0; i < pts.size(); i++)
            for (int j = i+1; j < pts.size(); j++)
                best = Math.min(best,
                        Math.hypot(pts.get(i).x()-pts.get(j).x(), pts.get(i).y()-pts.get(j).y()));
        return best;
    }
}