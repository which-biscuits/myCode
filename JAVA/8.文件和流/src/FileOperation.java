import java.util.Scanner;
import java.io.File;

public class FileOperation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("创建一个目录,请输入目录名 : ");
        String dirName = scanner.nextLine();    // 从控制台窗口读取当前行的输入信息
        File dir = new File(dirName);
        if (dir.exists()) {
            System.out.println("该目录或同名文件已经存在");
        } else
            dir.mkdirs();

        System.out.println("给一个文件(或目录)重命名,请输入旧名");
        String filename = scanner.nextLine();
        File old = new File(filename);
        if (!old.exists()) {
            System.out.println("不存在!");
        } else {
            System.out.println("请输入新名:");
            String fliename = scanner.nextLine();
            File newname = new File(filename);
            if (newname.exists()) {
                System.out.println("已存在!");
            } else {
                old.renameTo(newname);
            }
        }

        System.out.println("删除一个文件(或目录),请输入其名:");
        filename = scanner.nextLine();
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("不存在!");
        } else {
            System.out.println("你确定需要删除(Y/N)?");
            if (scanner.nextLine().trim().equalsIgnoreCase("Y")) {
                file.delete();
            }
        }

        System.out.println("查看目录中所含文件和子目录,请输入目录名:");
        dirName = scanner.nextLine();
        dir = new File(dirName);
        if (!dir.isDirectory()) {
            System.out.println("该目录不存在");
        } else {
            String[] list = dir.list();
            for (String s : list) {
                System.out.println(s);
            }
        }

        System.out.println("查看一个文件的属性, 请输入文件名:");
        filename = scanner.nextLine();
        if (!file.isFile()) {
            System.out.println("该文件不存在!");
        } else {
            System.out.println(file.getName() + "in" + file.getParent() + "is" + file.length() + "bytes"
            + "\n Can Read:" + file.canRead() + "\n Can Write:" + file.canWrite());
        }

    }
}
