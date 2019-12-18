package com.eduard.dataBase;

import com.eduard.model.Flight;
import com.eduard.model.OfDBString;
import com.eduard.model.Reservation;
import com.eduard.model.StringToDB;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class DBofFile<T extends StringToDB & OfDBString> {
    private File file;

    public DBofFile(@NotNull String path) {
        this.file = new File(path);
    }

    private void writeLinebreakOrContinue(String item, Writer w) throws IOException {
        if (item.charAt(item.length() - 1) != '\n') {
            w.write('\n');
        }
    }

    public void addOne(T t) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write(t.toDBSting());
        writeLinebreakOrContinue(t.toDBSting(), bw);
        bw.close();
    }

    public void addMany(List<T> list) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(this.file));
        list.forEach(s -> {
            try {
                bw.write(s.toDBSting());
                writeLinebreakOrContinue(s.toDBSting(), bw);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        bw.close();
    }

    public void clearAndWrite(List<T> list) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(this.file));
        bw.write("");
        bw.close();
        addMany(list);
    }

    public List<Flight> getAllFlights() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(this.file));
        return br.lines().map(s -> new Flight().ofDBString(s)).collect(Collectors.toList());
    }
    public List<Reservation> getAllReservations() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(this.file));
        return br.lines().map(s -> new Reservation().ofDBString(s)).collect(Collectors.toList());
    }

}
