import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Define the Table
 * Including a function to calculate the Cartesian Product of two Tables
 * Created by cxz on 2016/10/4.
 */
public class Table {
    private final File file;
    private final List<List<String>> records;

    public List<List<String>> getRecords(){
        return records;
    }
    Table(String fileName){
        this.file = new File(fileName);
        records = new LinkedList<>();
        readTable();
    }
    private void readTable() {
        String[] temp;
        List<String> record;
        Scanner scanner =null;
        try {
            scanner = new Scanner(file);

            while(scanner.hasNextLine()){
                temp = scanner.nextLine().split("[,]");
                if (temp.length <= 0) {
                    System.err.println("File is empty");
                    return;
                }
                record = new ArrayList<String>();
                for(String s : temp){
                    record.add(s);
                }
                records.add(record);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(scanner != null){
                scanner.close();
            }
        }
    }
    @Override
    public String toString(){
        StringBuilder strB = new StringBuilder();
        for( List<String> l : records){
            strB.append(l.toString());
            strB.append(System.getProperty("line.separator"));
        }
        return strB.toString();
    }
    /**
     * Write the "records" into the file of table
     * Using "append" to decide whether to replace or append
     * If "append" is false, function will clear the previous content
     * Otherwise function will start a new line and write the "records"
     * */
    public void writeTable(List<List<String>> records,Boolean append){
        StringBuilder strB = new StringBuilder();
        FileWriter fileWriter = null;
        try{
            fileWriter = new FileWriter(file,append);
            if(append){
                strB.append(System.getProperty("line.separator"));
            }
            for(List<String> l : records){
                for(String s : l){
                    strB.append(s);
                    strB.append(",");
                }
                strB.deleteCharAt(strB.length()-1);
                strB.append(System.getProperty("line.separator"));
                fileWriter.write(strB.toString());
                strB.delete(0,strB.length());
            }
            fileWriter.flush();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (fileWriter != null)
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException("关闭失败！");
                }

        }
    }
    /**
     * Rewrite the Table "Result" with the cartsianProduct
     * */
    public static void cartsianProduct(Table T1,Table T2,Table Result){
        long a = System.currentTimeMillis();
        List<List<String>> T1Records = T1.getRecords();
        List<List<String>> T2Records = T2.getRecords();
        List<List<String>> ResultRecords = new LinkedList<>();
        List<String> tempList;

        for(List<String> record1 : T1Records){
            for(List<String> record2 : T2Records){
                tempList = new LinkedList<>(record1);
                tempList.addAll(record2);
                ResultRecords.add(tempList);
            }
        }
        System.out.println("Do Cartsian Product in memory: "+ (System.currentTimeMillis()-a)/1f + "ms" );
        long b = System.currentTimeMillis();
        System.out.println("Start writing files ...");
        Result.writeTable(ResultRecords,false);
        System.out.println("Write to file: "+ (System.currentTimeMillis()-b)/1f + "ms" );
    }
}

