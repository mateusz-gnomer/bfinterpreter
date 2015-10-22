/**
 * 
 */
package main;

import ui.*;
import model.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import interpreter.*;
/**
 * @author gn
 * Klasa uruchamiająca cały program
 */
public class Kontroler implements ActionListener{

    private static InterfejsUzytkownika ui;
    private static StanMaszyny stanMaszyny;
    private static Interpreter interpreter;
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO uzależnienie interfejsu od argumentów uruchomienia
        if(args.length == 1){
            ui = new InterfejsGraficzny();
        }else{
            ui = new InterfejsTekstowy();
        }
        
        stanMaszyny = new ProstyStanMaszyny();
        interpreter = new ProstyInterpreter(stanMaszyny);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO akcja krok do przodu
        // TODO akcja edycja kodu
        // TODO akcja edycja wejścia
        // TODO akcja uruchom do breakpointa
        // TODO akcja uruchom
        // TODO akcja przerwij
        
    }

}
