import com.sun.istack.internal.NotNull;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DBofFile<T extends StringToDB> {
    private File file;

    public DBofFile(@NotNull String path) {
        this.file = new File(path);
    }

    private void writeLineBreakOrContinue(String item, Writer w) throws IOException {
        if(item.charAt(item.length() - 1) != '\n'){
            w.write('\n');
        }
    }

    public void addOne(String item) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write(item);
        writeLineBreakOrContinue(item, bw);
        bw.close();
    }
    public void addOne(T t) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write(t.toStringToDB());
        writeLineBreakOrContinue(t.toStringToDB(), bw);
        bw.close();
    }
    public void addMany(String ...items) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        Stream.of(items).forEach(s -> {
            try {
                bw.write(s);
                writeLineBreakOrContinue(s, bw);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        bw.close();
    }
    public void addMany(T ...t) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        Stream.of(t).forEach(s -> {
            try {
                bw.write(s.toStringToDB());
                writeLineBreakOrContinue(s.toStringToDB(), bw);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        bw.close();
    }

    public void addMany(List<String> list) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(this.file));
        list.forEach(s -> {
            try {
                bw.write(s);
                writeLineBreakOrContinue(s, bw);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        bw.close();
    }

    public List<String> getAll() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(this.file));
        return br.lines().collect(Collectors.toList());
    }
}
