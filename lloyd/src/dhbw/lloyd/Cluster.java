package dhbw.lloyd;

import java.util.ArrayList;
import java.util.List;

public class Cluster {
    private List<Point> mPoints = new ArrayList<>();
    private Point mCenter = new Point(0, 0);

    public void setCenter(Point mCenter) {
        this.mCenter = mCenter;
    }

    public Point getCenter() {
        return mCenter;
    }

    public void addPoint(Point p) {
        this.mPoints.add(p);
    }

    public void clear() {
        this.mPoints = new ArrayList<>();
    }

    public void updateCenter() {
        double sumX = 0;
        double sumY = 0;
        for (Point p : mPoints) {
            sumX += p.x;
            sumY += p.y;
        }
        Point newCenter = new Point(sumX / mPoints.size(), sumY / mPoints.size());
        this.mCenter = newCenter;
    }

    @Override
    public String toString() {
        return "Cluster{" + "mCenter=" + mCenter + ", mPoints=" + mPoints + '}';
    }
}

