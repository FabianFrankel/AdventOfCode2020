public class App {
    public static void main(String[] args) throws Exception {
    InputReader ir = new InputReader();
    TabogganTrajectory tbgTraj = new TabogganTrajectory(ir.getArrayOfinputLines("input"));

    tbgTraj.countTreesInPath(3, 1);
    int [][] slopes = {{1, 1},{3,1},{5,1},{7,1},{1,2}};
    System.out.println(tbgTraj.getTreeProduct(slopes));
    }
}
