package br.com.fulltime.app;

import br.com.fulltime.app.generator.RandomNumberGenerator;
import br.com.fulltime.app.history.History;
import com.sun.source.doctree.SeeTree;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class Program {

    public static void program() throws Exception {

        FileInputStream fis = new FileInputStream("idstorage.txt");
        InputStreamReader iSReader = new InputStreamReader(fis);
        BufferedReader reader = new BufferedReader(iSReader);
        History.idToStore = Integer.parseInt(reader.readLine());

        /*
         *
         *
         *
         */

        boolean isTheProgramSupposedToBeRunning = true;

        do {

            String[] buttons = {"Generate Number", "Check History", "Clean History Line", "Clean Full History", "Exit"};

            int userChoice = JOptionPane.showOptionDialog(null, "What would you like to do?", "RNG",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, buttons, buttons[0]);

            switch (userChoice) {
                case 0: {
                    RandomNumberGenerator.generateRandomNumber();
                    break;
                }
                case 1: {
                    History.getHistory();
                    break;
                }
                case 2: {
                    History.cleanLine();
                    break;
                }
                case 3: {
                    History.cleanFullHistory();
                    break;
                }
                case 4:
                default: {
                    isTheProgramSupposedToBeRunning = false;
                    break;
                }
            }
        } while (isTheProgramSupposedToBeRunning);

    }
}
