import java.util.*;

public  class ClosestPairofPoints{
    public record Pt(double x, double y) { }
    private static double dist(Pt a, Pt b) {
        double dx = a.x() - b.x(), dy = a.y() - b.y();
        return Math.hypot(dx, dy);
    }
    public static double solve(List<Pt> pts) {
        if (pts.size() < 2) return Double.POSITIVE_INFINITY;
        List<Pt> px = new ArrayList<>(pts);
        px.sort(Comparator.comparingDouble(Pt::x));
        List<Pt> py = new ArrayList<>(px);
        py.sort(Comparator.comparingDouble(Pt::y));
        return rec(px, py, 0, px.size());
    }

    private static double rec(List<Pt> px, List<Pt> py, int l, int r) {
        int n = r - l;
        if (n <= 3) {
            double d = Double.POSITIVE_INFINITY;
            for (int i = l; i < r; i++)
                for (int j = i + 1; j < r; j++)
                    d = Math.min(d, dist(px.get(i), px.get(j)));
            return d;
        }
        int m = l + n / 2;
        double midx = px.get(m).x();
        List<Pt> pyl = new ArrayList<>(m - l);
        List<Pt> pyr = new ArrayList<>(r - m);
        for (Pt p : py) {
            if (p.x() < midx || (p.x() == midx && pyl.size() < (m - l))) pyl.add(p);
            else pyr.add(p);
        }
        double dl = rec(px, pyl, l, m);
        double dr = rec(px, pyr, m, r);
        double d = Math.min(dl, dr);
        List<Pt> strip = new ArrayList<>();
        for (Pt p : py) if (Math.abs(p.x() - midx) <= d) strip.add(p);
        for (int i = 0; i < strip.size(); i++) {
            for (int j = i + 1; j < strip.size() && j <= i + 7; j++) {
                d = Math.min(d, dist(strip.get(i), strip.get(j)));
            }
        }
        return d;
    }
}