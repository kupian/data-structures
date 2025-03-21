package alexcarr.ads;

public class MinCostRopes {
    private int[] lengths;
    private MinPriorityQueue mpq;
    private int totalCost = 0;

    public MinCostRopes(int[] lengths) {
        this.lengths = lengths;
        mpq = new MinPriorityQueue(lengths.length);
    }

    private void calculate() {
        for (int rope : lengths) {
            mpq.insert(rope);
        }
        while (mpq.getSize() > 1) {
            int ropeA = mpq.remove();
            int ropeB = mpq.remove();
            int ropeC = ropeA + ropeB;
            System.out.println("Connect rope of length " + ropeA + " to rope of length " + ropeB + " giving rope of length " + ropeC);
            mpq.insert(ropeC);
            totalCost += ropeC;
        }
        System.out.println("Total cost: " + totalCost);
    }

    public static void main(String[] args) {
        MinCostRopes ropes = new MinCostRopes(new int[]{4,8,3,1,6,9,12,7,2});
        ropes.calculate();
    }
}
