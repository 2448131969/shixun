package example;

import com.csvreader.CsvReader;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class jobs4 {
    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\LC\\Desktop\\课程设计\\任务\\任务\\zpsjj\\jobs4.csv");
        InputStream in = new FileInputStream(file);
        OutputStream out = new FileOutputStream("C:\\Users\\LC\\Desktop\\课程设计\\zpsjj\\jobs4.txt");//输出成txt文件
        CsvReader cr = new CsvReader(in, Charset.forName("utf-8"));
        //获取表头
        cr.readHeaders();
        int a = 0;
//        System.out.println(cr.getHeader(12));   //job_tag
//        System.out.println(Arrays.toString(cr.getHeaders()));    //[company_financing_stage, company_industry, company_location, company_name, company_nature, company_overview, company_people, job_edu_require, job_exp_require, job_info, job_name, job_salary, job_tag, job_welfare]
        //判断文件中是否有内容可读。
//        out.write(Integer.parseInt(Arrays.toString(cr.getHeaders())));
        int line = 0;//处理了多少行
        while (cr.readRecord()){
            //读取每一行内容
            String rawRecord = cr.getRawRecord();
            int columnCount = cr.getColumnCount();
//            System.out.println(columnCount);    //连空行有多少项
            for (int i = 0; i < columnCount; i++) {
                String str = cr.get(i);
                Pattern p = Pattern.compile("\\s+|\t+|\n|\r");
                Matcher m = p.matcher(str);
                String s = m.replaceAll("");
                System.out.println(s);

//                System.out.println(str);
                byte[] bytes = (s+",    ").getBytes();
                out.write(bytes);   //写入的是所有信息，但没有换行

                //将读取到的单行数据写入txt文件中
            }
            line += 1;
            //将读取到的所有数据换行写入
            out.write("\n".getBytes());


        }
        System.out.println(line);

        out.close();
        cr.close();
    }
}