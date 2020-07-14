package algorithm.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Description: 给定一批会议 每个会议有开始  结束时间 给一个当前时间 如何安排 会议完成最多
 * @Author: zhangwei
 * @Date: 2020/7/10 16:32
 */
public class T04_BestArrange {
    static class Program {
        int start;
        int end;

        Program(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "Program{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }


    public static int arrange(Program[] programs, int timeLine) {

        if (programs == null || programs.length == 0) {
            return 0;
        }

        return  process(programs,0,0);
    }


    public static int process(Program[] programs, int done ,int timeLine){

        if (programs.length == 0){
            return done;
        }

        int max = done;

        for (Program program : programs){
            if (program.start >= timeLine){
                Program[] next = copyAndExcept(programs,program);
                max = Math.max(max,process(next,done+1,program.end));
            }
        }

        return max;
    }


    public static int arrange2(Program[] programs,int timeLine){
        Arrays.sort(programs,new ProgramComparator());
        int result = 0;
        for (Program program : programs) {
            if (timeLine <= program.start){
                result++;
                timeLine = program.end;
            }
        }
        return result;
    }

    static class ProgramComparator implements Comparator<Program>{

        @Override
        public int compare(Program o1, Program o2) {
            return o1.end - o2.end;
        }
    }

    private static Program[] copyAndExcept(Program[] programs, Program program) {
        Program[] help = new Program[programs.length-1];

        int index = 0;

        for (int i = 0; i < programs.length;i++){
            if (programs[i] != program){
                help[index++] = programs[i];
            }
        }
        return help;


    }

    // for test
    public static Program[] generatePrograms(int programSize, int timeMax) {
        Program[] ans = new Program[(int) (Math.random() * (programSize + 1))];
        for (int i = 0; i < ans.length; i++) {
            int r1 = (int) (Math.random() * (timeMax + 1));
            int r2 = (int) (Math.random() * (timeMax + 1));
            if (r1 == r2) {
                ans[i] = new Program(r1, r1 + 1);
            } else {
                ans[i] = new Program(Math.min(r1, r2), Math.max(r1, r2));
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        int programSize = 12;
        int timeMax = 20;
        int timeTimes = 1000000;



        for (int i = 0; i < timeTimes; i++) {
//            Program[] programs = {new Program(5,11),new Program(11,13),new Program(4,19)};
            Program[] programs = generatePrograms(programSize, timeMax);
            if (arrange(programs,0) != arrange2(programs,0)) {
                System.out.println(Arrays.toString(programs));
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }


}
