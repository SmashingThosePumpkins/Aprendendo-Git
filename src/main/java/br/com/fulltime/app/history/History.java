package br.com.fulltime.app.history;

import br.com.fulltime.app.generator.Number;

import javax.swing.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class History {

    public static int idToStore;
    private static final String ARQUIVO_TEXTO = "textstorage.txt";
    private static final String ARQUIVO_ID = "idstorage.txt";
    private static final String ARQUIVO_TEXTO_TEMPORARIO = "textstorageTEMP.txt";


    public static void storeID(int idToStore) throws IOException {
        FileOutputStream textOutput = new FileOutputStream(ARQUIVO_ID, false);
        textOutput.write((idToStore + "").
                getBytes(StandardCharsets.UTF_8));
        textOutput.close();
    }

    public static void storeRN(Number n) throws IOException {

        FileOutputStream textOutput = new FileOutputStream(ARQUIVO_TEXTO, true);
        textOutput.write((
                String.format("Number: %d [ID = %04d]\n", n.getValue(), idToStore++)).
                getBytes(StandardCharsets.UTF_8));

        textOutput.close();

        storeID(idToStore);

    }

    public static String getHistory() throws IOException {

        FileInputStream textInput = new FileInputStream(ARQUIVO_TEXTO);
        StringBuilder history = new StringBuilder();
        history.append("History:\n\n");
        int available = textInput.available();
        if (available > 0) {
            System.out.println();
            byte[] seila = new byte[available];
            textInput.read(seila, 0, available);
            System.out.println(new String(seila));
            history.append(new String(seila));

        }

        textInput.close();

        JOptionPane.showMessageDialog(null, history);
        return history.toString();

    }

    public static void cleanLine() throws Exception {

        String id = JOptionPane.showInputDialog(null, "Type in the ID of the line to be deleted.");

        try {

            try {
                Integer.parseInt(id);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "IDs are numbers.");
                throw ex;
            }

            if (id.length() != 4) {
                JOptionPane.showMessageDialog(null, "IDs are four digits long.");
            } else {

                Scanner scanner = new Scanner(new File(ARQUIVO_TEXTO));
                FileWriter fileWriter = new FileWriter(ARQUIVO_TEXTO_TEMPORARIO);
                BufferedWriter buffWriter = new BufferedWriter(fileWriter);
                String line;

                do {
                    line = scanner.nextLine();
                    if (line.contains(id)) {
                        continue;
                    } else {
                        buffWriter.write(line + "\n");
                    }
                } while (scanner.hasNext());

                buffWriter.flush();
                scanner.close();

                // Transfers contents of "textstorageTEMP.txt" to "textstorage.txt" and deletes the first
                Scanner scannero = new Scanner(new File(ARQUIVO_TEXTO_TEMPORARIO));
                FileWriter transferTemp = new FileWriter(ARQUIVO_TEXTO);
                BufferedWriter transferBuffWriter = new BufferedWriter(transferTemp);
                String lina;

                do {
                    lina = scannero.nextLine();
                    transferBuffWriter.write(lina + "\n");
                } while (scannero.hasNext());

                transferBuffWriter.flush();

                JOptionPane.showMessageDialog(null, String.format("Number with ID %s deleted successfully.", id));

            }
        } catch (NullPointerException | NumberFormatException ex) {

        } catch (NoSuchElementException ex) {
            JOptionPane.showMessageDialog(null, "Esse ID é inválido.");
        }

        File temp = new File(ARQUIVO_TEXTO_TEMPORARIO);
        temp.delete();

    }

    public static void cleanFullHistory() throws Exception {

        FileOutputStream fos = new FileOutputStream(ARQUIVO_TEXTO, false);
        fos.write(("").getBytes(StandardCharsets.UTF_8));

        JOptionPane.showMessageDialog(null, "History was sucessfuly cleaned up.");

        fos.close();

        idToStore = 1;
        storeID(idToStore);
    }
}
