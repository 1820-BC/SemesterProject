package Functions;

public class Functions {


    /*
    A set of universal helper methods for the project
     */
    private Functions(){
        return;
    }

    //returns [start,end)
    public static int[] range(int start, int end){
        int[] a=new int[end-start];
        for(int i=start;i<end;i++){
            a[i-start]=i;
        }


        return a;
    }

    public static int[] range(int end){
        return range(0,end);
    }

    public static boolean isEven(int i){
        return i%2==0;
    }
    public static int[] randomVector(){
        int[] ret={(int)(Math.random()*3)-1,(int)(Math.random()*3)-1}; //gives a pseudorandom number in set {1, 0,-1} in both slots.
        return ret;
    }
}
