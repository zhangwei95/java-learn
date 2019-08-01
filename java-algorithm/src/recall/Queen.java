package recall;

/**
 * 经典八皇后
 */
public class Queen {
    public static int num = 0;//累计方案
    public static final int MAXQUEEN = 8;
    public static int[] cols = new int[MAXQUEEN];   //定义数组，表示8列棋子皇后摆放的位置

    /**
     * @param n 表示填第n列的皇后
     * */
    public void getCount(int n){
        boolean[] rows = new boolean[MAXQUEEN];

        for(int i = 0;i < n;i++){
            rows[cols[i]] = true;

            int d = n - i;  //计算差值

            //正斜方向
            if(cols[i] - d >= 0){
                rows[cols[i]- d] = true;
            }

            //反斜方向
            if(cols[i] + d <= (MAXQUEEN - 1)){
                rows[cols[i] + d] = true;
            }
        }

        for(int i = 0;i < MAXQUEEN;i++){
            if(rows[i]){
                continue;
            }
            cols[n] = i;

            if(n < MAXQUEEN - 1){
                getCount(n + 1);
            }else{
                //找到一套完整的方案
                num++;
                printQueen();
            }
        }
    }

    public void printQueen(){
        System.out.println("第" + num + "种方案");
        for(int i = 0;i < MAXQUEEN;i++){
            for(int j = 0;j < MAXQUEEN;j++){
                if(i == cols[j])
                    System.out.print("0 ");
                else
                    System.out.print("+ ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args){
        Queen q = new Queen();
        q.getCount(0);
    }

}
