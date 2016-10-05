
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        System.out.println("Start reading files ...");
        long a = System.currentTimeMillis();
        Table t1 = new Table("Table1.txt");
        Table t2 = new Table("Table2.txt");
        Table result = new Table("Result.txt");
        System.out.println("Read Files: "+ (System.currentTimeMillis()-a)/1f + "ms" );
//        List<List<String>> lls = new LinkedList<>();
//        List<String> ls;
//        Random r = new Random();
//        r.setSeed(3);
//        for(int i = 1; i <= 1000; i++){
//            ls = new LinkedList<>();
//            ls.add(Integer.toString(i));
//            ls.add(Integer.toString(r.nextInt()%100));
//            ls.add(Integer.toString(r.nextInt()%100));
//            ls.add(Integer.toString(r.nextInt()%100));
//            ls.add(Integer.toString(r.nextInt()%100));
//            lls.add(ls);
//        }
        Table.cartsianProduct(t1,t2,result);
    }
}
