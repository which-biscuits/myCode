import java.io.*;

public class FileIO {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("命令行形式应为 : java FileIO 源文件 目标文件");
            System.exit(0);
        }

        BufferedReader in = null;
        PrintWriter out = null;

        try {
            File sourceFile = new File(args[0]);
            if (!sourceFile.exists()) {
                System.out.println("源文件" + args[0] + "不存在!");
                System.exit(0);
            }
            File targetFile = new File(args[1]);
            if (targetFile.exists()) {
                System.out.println("源文件" + args[1] + "已存在!");
                System.exit(0);
            }
            String str;
            int lineCount = 1;
            in = new BufferedReader(new FileReader(sourceFile));
            out = new PrintWriter(new FileWriter(targetFile));

            while ((str = in.readLine()) != null) {
                out.println(lineCount + "\t" + str);
                lineCount++;
            }
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            try {
                if (in != null) in.close();
                if (out != null) out.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}
