/**
 * 
 */
package ui;


import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.*;

import javax.swing.table.AbstractTableModel;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import model.StanMaszyny;
/**
 * @author gn
 *
 */
public class InterfejsGraficzny extends JFrame implements InterfejsUzytkownika{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
 
    
    private StanMaszyny stanMaszyny;
    private boolean test;
    private ActionListener sluchacz;
    private ActionListener aktywator;
    private boolean aktywne;
    private JButton krok;
    private JButton doPunktu;
    private JButton wszystko;
    private JButton uruchomZakoncz;

    private JTextArea kod;
    private JScrollPane przewijanieKodu;

    private JTextArea wejscie;
    private JScrollPane przewijanieWejscia;

    private JTextArea wyjscie;
    private JScrollPane przewijanieWyjscia;

    
    
    private JTable tabela;
    public InterfejsGraficzny(ActionListener sluchacz){
        this.test = true;
        this.sluchacz = sluchacz;
        this.aktywator = new Aktywator();
        // ustawienie porzadkowania komponetow
        GridBagLayout uklad = new GridBagLayout();
        this.setLayout(uklad);
        GridBagConstraints wytyczne = new GridBagConstraints();
            // komponenty nieistotne
        JPanel panelWeWy = new JPanel();
        JPanel panelPrzyciskow = new JPanel();
        aktywne = false;
        krok = new JButton("Single step");
        krok.setActionCommand("step");
        krok.setEnabled(false);
        krok.addActionListener(sluchacz);
        krok.addActionListener(aktywator);
        doPunktu = new JButton("Run to #");
        doPunktu.setActionCommand("toBreakpoint");
        doPunktu.setEnabled(false);
        doPunktu.addActionListener(sluchacz);
        doPunktu.addActionListener(aktywator);
        wszystko = new JButton("Run all");
        wszystko.setActionCommand("runAll");
        wszystko.setEnabled(false);
        wszystko.addActionListener(sluchacz);
        wszystko.addActionListener(aktywator);
        uruchomZakoncz = new JButton("Run debugger");
        uruchomZakoncz.setActionCommand("uruchomZakoncz");
        uruchomZakoncz.addActionListener(aktywator);
        uruchomZakoncz.addActionListener(sluchacz);
    
        panelPrzyciskow.add(krok);
        panelPrzyciskow.add(doPunktu);
        panelPrzyciskow.add(wszystko);
        panelPrzyciskow.add(uruchomZakoncz);
            //==== INITIAL SETTINGS ======
            
            this.setSize(500, 400);
        this.setTitle("Brainfuck debugger");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        //==== putting components ======
        this.kod = new JTextArea(8,40);
            //kod.setPreferredSize(new Dimension(100, 100));
        this.przewijanieKodu = new JScrollPane(this.kod);
    
    
    
        this.wejscie = new JTextArea(4,20);
        this.przewijanieWejscia = new JScrollPane(this.wejscie);
        
    
        this.wyjscie = new JTextArea(4,20);
        this.wyjscie.setEnabled(false);
        this.przewijanieWyjscia = new JScrollPane(this.wyjscie);
            panelWeWy.add(this.przewijanieWejscia, BorderLayout.NORTH);
            panelWeWy.add(this.przewijanieWyjscia, BorderLayout.SOUTH);
     
        // Wkładanie wszystkiego do ramki przy użyciu grid bag constraints
        wytyczne.weightx = 100;
        wytyczne.weighty = 100;
        wytyczne.gridx = GridBagConstraints.RELATIVE;
        wytyczne.gridy = GridBagConstraints.RELATIVE;
        wytyczne.gridwidth = 1;
        wytyczne.gridheight = 2;
        
        this.add(this.przewijanieKodu, wytyczne);     
    
        wytyczne.weightx = 100;
        wytyczne.weighty = 100;
        wytyczne.gridx = GridBagConstraints.RELATIVE;
        wytyczne.gridy = GridBagConstraints.RELATIVE;
        wytyczne.gridwidth = GridBagConstraints.REMAINDER;
        wytyczne.gridheight = 1;
        this.add(przewijanieWejscia, wytyczne);
    
        wytyczne.weightx = 100;
        wytyczne.weighty = 100;
        wytyczne.gridx = GridBagConstraints.RELATIVE;
        wytyczne.gridy = GridBagConstraints.RELATIVE;
        wytyczne.gridwidth = GridBagConstraints.REMAINDER;
        wytyczne.gridheight = 1;
        this.add(przewijanieWyjscia, wytyczne);
    
        wytyczne.weightx = 100;
        wytyczne.weighty = 100;
        wytyczne.gridx = GridBagConstraints.RELATIVE;
        wytyczne.gridy = GridBagConstraints.RELATIVE;
        wytyczne.gridwidth = GridBagConstraints.REMAINDER;
        wytyczne.gridheight = 1;
        tabela = new JTable(new ModelDanych());
        JScrollPane przewijanieTabelki = new JScrollPane(tabela);
        //http://stackoverflow.com/questions/6523974/shrink-jscroll-pane-to-same-height-as-jtable
        Dimension d = tabela.getPreferredSize();
        przewijanieTabelki.setPreferredSize(
             new Dimension(d.width,tabela.getRowHeight()*4/*rows+1*/));
        this.add(przewijanieTabelki, wytyczne);
    
            wytyczne.weightx = 100;
        wytyczne.weighty = 100;
        wytyczne.gridx = GridBagConstraints.RELATIVE;
        wytyczne.gridy = GridBagConstraints.RELATIVE;
        wytyczne.gridwidth = GridBagConstraints.REMAINDER;
        wytyczne.gridheight = GridBagConstraints.REMAINDER;
        this.add(panelPrzyciskow, wytyczne);
     
        this.pack();
            //==== Positioning the frame ====
        Dimension screenSize = new Dimension(
            Toolkit.getDefaultToolkit().getScreenSize()
                             );
        Dimension prefferedSize = new Dimension(this.getSize());
        int wdwLeft = screenSize.width / 2 - prefferedSize.width /2;
        int wdwTop = screenSize.height /2 - prefferedSize.height /2;

        this.setLocation(wdwLeft, wdwTop);
    
    }

    @Override
    public void wyswietlInterfejs() {
        this.setVisible(true);
    }

    @Override
    public void uaktualnijStanMaszyny() {
        
        // TODO Auto-generated method stub
       if(this.test == false){
           this.stanMaszyny.resetuj();
           this.stanMaszyny.ustawKod(this.kod.getText());
           this.stanMaszyny.ustawWejscie(this.wejscie.getText());
       }
       wyswietlStanMaszyny();
    }
    

    @Override
    public void wyswietlStanMaszyny() {
        // TODO Auto-generated method stub
        // pozycja kodu
        // pozycja wejscia
        // wyjscie
        if(this.test == false){
            this.wyjscie.setText(this.stanMaszyny.zwrocWyjscie());
        }

        // tabelka
    }
    
    @Override
    public void przypiszStanMaszyny(StanMaszyny stan) {
        // TODO Auto-generated method stub
        this.test = false;
        this.stanMaszyny = stan;
    }
    
    class Aktywator implements ActionListener{
        public void actionPerformed(ActionEvent e){
            wyswietlStanMaszyny();
            
            if(e.getActionCommand().equals("uruchomZakoncz")){
                if(aktywne==true){
                    aktywne = false;
                    uruchomZakoncz.setText("Run debugger");
                    
                }else {
                    aktywne = true;
                    uruchomZakoncz.setText("Stop debugger");
                    uaktualnijStanMaszyny();
                }
                krok.setEnabled(aktywne);
                doPunktu.setEnabled(aktywne);
                wszystko.setEnabled(aktywne);
                kod.setEnabled(!aktywne);
                wejscie.setEnabled(!aktywne);
            }
        }
    }

    class ModelDanych extends AbstractTableModel{
        /**
             * 
             */
        private static final long serialVersionUID = 1L;
        public int getRowCount(){
            return 2;
        }
        public int getColumnCount(){
            return 10;
        }
        public Object getValueAt(int r, int c){
            return r+c;
        }
    }
    
    public static void main(String [] args){
        InterfejsGraficzny okno = new InterfejsGraficzny(null);
        okno.wyswietlInterfejs();
    }

 
    
    

}
