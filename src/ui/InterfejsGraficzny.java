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


/**
 * @author gn
 *
 */
public class InterfejsGraficzny extends JFrame implements InterfejsUzytkownika{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
 
    
 //   private StanMaszyny stanMaszyny
    private ActionListener sluchacz;
    private int [] tasma;
    private int pozLewaTasmy;
    private int iloscKolumnTabeli;
    private ActionListener aktywator;
    private ActionListener przewijacz;
    private boolean aktywne;
    private JButton krok;
    private JButton doPunktu;
    private JButton wszystko;
    private JButton uruchomZakoncz;
    
    private JButton jedenLewo;
    private JButton jedenPrawo;
    private JButton dziesiecLewo;
    private JButton dziesiecPrawo;

    private JTextArea kod;
    private JScrollPane przewijanieKodu;

    private JTextArea wejscie;
    private JScrollPane przewijanieWejscia;

    private JTextArea wyjscie;
    private JScrollPane przewijanieWyjscia;

    
    
    private JTable tabela;
    public InterfejsGraficzny(ActionListener sluchacz){
        this.pozLewaTasmy = 0;
        this.iloscKolumnTabeli = 10;

        this.sluchacz = sluchacz;
        this.aktywator = new Aktywator();
        this.przewijacz = new Przewijacz();
        this.tasma = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        // ustawienie porzadkowania komponetow
        GridBagLayout uklad = new GridBagLayout();
        this.setLayout(uklad);
        GridBagConstraints wytyczne = new GridBagConstraints();
            // komponenty nieistotne
        JPanel panelWeWy = new JPanel();
        JPanel panelPrzyciskow = new JPanel();
        
        jedenLewo = new JButton("<");
        jedenLewo.setActionCommand("oneLeft");
        jedenLewo.addActionListener(przewijacz);
        
        jedenPrawo = new JButton(">");
        jedenPrawo.setActionCommand("oneRight");
        jedenPrawo.addActionListener(przewijacz);
        
        dziesiecLewo = new JButton("<<");
        dziesiecLewo.setActionCommand("tenLeft");
        dziesiecLewo.addActionListener(przewijacz);
        
        dziesiecPrawo = new JButton(">>");
        dziesiecPrawo.setActionCommand("tenRight");
        dziesiecPrawo.addActionListener(przewijacz);
        
        JPanel panelPrzewijania = new JPanel();
        panelPrzewijania.add(dziesiecLewo);
        panelPrzewijania.add(jedenLewo);
        panelPrzewijania.add(jedenPrawo);
        panelPrzewijania.add(dziesiecPrawo);

        
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
        uruchomZakoncz.setActionCommand("uruchom");
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
        wytyczne.weightx = 1;
        wytyczne.weighty = 1;
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
        wytyczne.gridheight = 1;
        this.add(panelPrzewijania, wytyczne);
    
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


    
    class Aktywator implements ActionListener{
        public void actionPerformed(ActionEvent e){
            
            if(e.getActionCommand().equals("uruchom")
                ||e.getActionCommand().equals("zakoncz")){
                if(aktywne==true){
                    aktywne = false;
                    uruchomZakoncz.setText("Run debugger");
                    uruchomZakoncz.setActionCommand("uruchom");
                    
                }else {
                    aktywne = true;
                    wyjscie.setText("");
                    uruchomZakoncz.setText("Stop debugger");
                    uruchomZakoncz.setActionCommand("zakoncz");
                }
                krok.setEnabled(aktywne);
                doPunktu.setEnabled(aktywne);
                wszystko.setEnabled(aktywne);
                kod.setEnabled(!aktywne);
                wejscie.setEnabled(!aktywne);
            }

        }
    }
    
    class Przewijacz implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(e.getActionCommand().equals("oneLeft")){
                pozLewaTasmy--;
                if(pozLewaTasmy<0){
                    pozLewaTasmy = pozLewaTasmy+tasma.length;
                }
            }
            if(e.getActionCommand().equals("tenLeft")){
                pozLewaTasmy = pozLewaTasmy-10;
                if(pozLewaTasmy<0){
                    pozLewaTasmy = pozLewaTasmy+tasma.length;
                }
            }
            if(e.getActionCommand().equals("oneRight")){
                pozLewaTasmy++;
                if(pozLewaTasmy >= tasma.length){
                    pozLewaTasmy = pozLewaTasmy-tasma.length;
                }
            }
            if(e.getActionCommand().equals("tenRight")){
                pozLewaTasmy = pozLewaTasmy + 10;
                if(pozLewaTasmy >= tasma.length){
                    pozLewaTasmy = pozLewaTasmy-tasma.length;
                }
            }
            aktualizujModelTabeli();
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

    @Override
    public String zwrocKod() {
        return this.kod.getText();
    }

    @Override
    public String zwrocWejscie() {
        return this.wejscie.getText();
    }

    @Override
    public void ustawWyjscie(String wyjscie) {
        this.wyjscie.setText(wyjscie);
    }

    @Override
    public void ustawTasme(int[] t) {
        this.tasma = t;
        // TODO zrob model z tej tasmy i przypisz tabeli
       this.aktualizujModelTabeli();
    }
    
    private void aktualizujModelTabeli(){
        tabela.setModel(new AbstractTableModel(){
            int dlTasmy = tasma.length;
            /**
             * 
             */
            private static final long serialVersionUID = 1L;
            public int getColumnCount(){
                return iloscKolumnTabeli;
            }
            public int getRowCount(){
                return 2;
            }
            public Object getValueAt(int r, int c){
                int indeks = pozLewaTasmy+c;
                if(indeks >= this.dlTasmy){
                    indeks = indeks - this.dlTasmy;
                }
                if(r==0){
                   return indeks;
                }
                return tasma[indeks];
            }
        });
    }

 
    
    

}
