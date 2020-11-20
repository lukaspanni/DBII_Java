package dhbw.lloyd;

import java.util.ArrayList;
import java.util.List;

public class Lloyd {

    private static final int T = 10;
    private static int k = 3;
    private static List<Cluster> clusters;

    public static void main(String[] args) {
        //Setup
        clusters = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            clusters.add(new Cluster());
        }
        Point[] data = Data.mData;

        //Lloyd-Algorithm

        // Random Points as centers
        int i = 0;
        for (Cluster c : clusters) {
            c.setCenter(data[i++]);
        }

        System.out.println("Start");
        clusters.forEach(System.out::println);
        System.out.println();
        //Main iteration
        for (int t = 0; t < T; t++) {


            clusters.forEach(Cluster::clear);
            //Add points to cluster
            for (Point p : data) {
                //Find nearest Cluster
                Cluster minCluster = clusters.get(0);
                double minDistance = minCluster.getCenter().computeDistanceTo(p);
                for (int j = 1; j < clusters.size(); j++) {
                    Cluster tempC = clusters.get(j);
                    double distance = tempC.getCenter().computeDistanceTo(p);
                    if (distance < minDistance) {
                        minDistance = distance;
                        minCluster = tempC;
                    }
                }
                minCluster.addPoint(p);
            }

            //Compute new centers
            clusters.forEach(Cluster::updateCenter);

            System.out.println("End of Iteration "+t);
            clusters.forEach(System.out::println);
        }

    }

}
