import java.lang.reflect.Array;

public class TabogganTrajectory {
    String[] input;  


    public TabogganTrajectory(String[] input){
        this.input = input;
    }
    

    public void print(){
        for (String string : this.input) {
            System.out.println(string);
        }
    }

    public int countTreesInPath(int right, int down){
        int[] index = {0,0};
        int treeCount = 0;
        while(this.getNextIndex(index, right, down) != null){
            index = this.getNextIndex(index, right, down);
            if(this.isTree(index)){
                treeCount ++;
            }
        }
        return treeCount;
    }

    private int [] getNextIndex(int [] index, int right, int down){
        int[] nextIndex = {0,0};
        if(index != null){
            nextIndex[0] = (index[0] + right);
        nextIndex[1] = index[1] + down;
        if(nextIndex[1] < this.input.length){
            return nextIndex;
        }
        }
        return null;   
    }
    public int getTreeProduct(int[][] slopes){
        int prod = 1;
        for (int[] slope : slopes) {
            prod = prod*this.countTreesInPath(slope[0], slope[1]);
        }
        return prod;
    }


    private boolean isTree(int [] index){
        if (index != null) {
            return this.input[index[1]].charAt(index[0]%this.input[0].length()) ==  "#".charAt(0);
        }
        return false;
    }
    
} 
