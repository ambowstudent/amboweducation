import java.util.Scanner;

public class Jia {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入基数：");
        int x=scanner.nextInt();
        System.out.println("请输入循环次数：");
        int y=scanner.nextInt();
        jia(x,y);
    }

    private static void jia(int x, int y) {
        int a=0,sum=0;
        for(int i=0;i<y;i++){
            a=(a*10)+x;
            sum+=a;
        }
        System.out.println(sum);
    }
}
