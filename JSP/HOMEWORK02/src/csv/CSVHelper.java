package csv;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;

/*
* https://blog.csdn.net/zyxhangiian123456789/article/details/93719329
* */

public class CSVHelper {

    private String[] headers;
    private char separator = ',';

    public char getSeparator() {
        return separator;
    }

    public void setSeparator(char separator) {
        this.separator = separator;
    }

    public String[] getHeaders() {
        return headers;
    }

    public void setHeaders(String[] headers) {
        this.headers = headers;
    }

    public CSVHelper() {
    }

    /**
     * 读取csv文件
     */
    public List<String[]> readCSV(String path, boolean skipHeaders) {

        List<String[]> result = new LinkedList<>();

        try {
            // 第一参数：读取文件的路径; 第二个参数：分隔符; 第三个参数：字符集
            CsvReader csvReader = new CsvReader(path, separator, Charset.forName("GBK"));

            // 是否跳过标题
            if (!skipHeaders) {
                headers = csvReader.getHeaders();
            }

            // 读取每行的内容
            while (csvReader.readRecord()) {

                String[] line = csvReader.getValues();
                result.add(line);
            }

            csvReader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

        return result;
    }

    /**
     * 写入csv文件
     */
    public void writeCSV(String path, List<String[]> content) {

        try {
            CsvWriter csvWriter = new CsvWriter(path, separator, Charset.forName("GBK"));

            // 写表头和内容
            csvWriter.writeRecord(headers);
            for (String[] line : content) {
                csvWriter.writeRecord(line);
            }

            // 关闭csvWriter
            csvWriter.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }
}
