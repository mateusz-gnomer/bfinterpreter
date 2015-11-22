/**
 * 
 */
package main;

import ui.*;
import model.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//import javax.swing.SwingUtilities;

import interpreter.*;
/**
 * @author gn
 * Klasa uruchamiająca cały program
 */
public class Kontroler implements ActionListener{

    private InterfejsUzytkownika ui;
    private StanMaszyny stanMaszyny;
    private Interpreter interpreter;
    /**
     * @param args
     */
    public void ustawStanMaszyny(StanMaszyny sm){
        this.stanMaszyny = sm;
    }
    
    public void ustawInterpreter(Interpreter in){
        this.interpreter = in;
    }
    
    public void ustawInterfejs(InterfejsUzytkownika ui){
        this.ui = ui;

    }
    
    public void uruchom(){
        this.stanMaszyny.resetuj();
        this.ui.ustawTasme(this.stanMaszyny.zwrocTasme());
        this.ui.wyswietlInterfejs();
    }
    
    public static void main(String[] args) {
        // TODO uzależnienie interfejsu od argumentów uruchomienia
        Kontroler instancjaKontrolera = new Kontroler();
        StanMaszyny stanMaszyny = new ProstyStanMaszyny();
        Interpreter interpreter = new ProstyInterpreter(stanMaszyny);
        
        InterfejsUzytkownika ui;
        instancjaKontrolera.ustawStanMaszyny(stanMaszyny);
        instancjaKontrolera.ustawInterpreter(interpreter);

        ui = new InterfejsGraficzny(instancjaKontrolera);

        
        instancjaKontrolera.ustawInterfejs(ui);
        instancjaKontrolera.uruchom(); 
        
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand().equals("uruchom")){
            this.stanMaszyny.resetuj();
            this.stanMaszyny.ustawKod(this.ui.zwrocKod());
            this.stanMaszyny.ustawWejscie(this.ui.zwrocWejscie());
        }
        if(e.getActionCommand().equals("step")){
            this.interpreter.krok();
            this.ui.ustawWyjscie(this.stanMaszyny.zwrocWyjscie());
        }
        if(e.getActionCommand().equals("toBreakpoint")){
            this.interpreter.uruchomDoPunktu();
            this.ui.ustawWyjscie(this.stanMaszyny.zwrocWyjscie());
        }
        if(e.getActionCommand().equals("runAll")){
            this.interpreter.uruchomDoKonca();
            this.ui.ustawWyjscie(this.stanMaszyny.zwrocWyjscie());
        }
        this.ui.ustawTasme(this.stanMaszyny.zwrocTasme());
        // TODO akcja krok do przodu
        // TODO akcja edycja kodu
        // TODO akcja edycja wejścia
        // TODO akcja uruchom do breakpointa
        // TODO akcja uruchom
        // TODO akcja przerwij
        
    }

}
