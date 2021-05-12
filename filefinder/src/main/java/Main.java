import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        int count;
        String separator = File.separator;
        String path = "D:" +separator+ "testfolder" +separator;
        ArrayList<String> str1 = new ArrayList<>();
        ArrayList<File> fileList = new ArrayList<>();
        searchFiles(new File(path), str1);
        Collections.sort(str1);
        for(count = 0; count < str1.size(); count++) {
            System.out.println(str1.get(count));
        }
    }

    private static void searchFiles(File rootFile, List<String> str1) throws IOException {
        String str;
        if (rootFile.isDirectory()) {
            System.out.println(rootFile.getAbsolutePath());
            File[] directoryFiles = rootFile.listFiles();
            if (directoryFiles != null)
                for (File file: directoryFiles) {
                    if (file.isDirectory()) {
                        searchFiles(file, str1);
                    } else {
                        if (file.getName().toLowerCase().endsWith(".txt")) {
                            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                            while((str = bufferedReader.readLine()) != null) {
                                str1.add(str);
                            }
                        }
                }
            }
        }
    }
}
