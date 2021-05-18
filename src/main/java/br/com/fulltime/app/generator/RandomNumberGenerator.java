package br.com.fulltime.app.generator;

import br.com.fulltime.app.history.History;

import javax.swing.*;
import java.io.IOException;
import java.time.*;
import java.time.temporal.*;

public class RandomNumberGenerator {

    private static long generateSeed() {

        Instant now = Instant.now();
        long seed = now.getLong(ChronoField.NANO_OF_SECOND) + 1000000000l;
        return seed;

    }

    public static long generateRandomNumber() {

        try {
        int max = Integer.parseInt(JOptionPane.showInputDialog(null, "Type in the max number."));
        long seed = generateSeed(), number = seed % max;
        System.out.println(seed);
        JOptionPane.showMessageDialog(null, "Generated number: " + number);
        Number value = new Number(seed, number);

            History.storeRN(value);
            return number;
        } catch (IOException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível gerar um número.");
        }
        return 0;
    }
}
