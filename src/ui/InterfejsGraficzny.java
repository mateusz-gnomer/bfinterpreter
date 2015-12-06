/**
 * 
 */
package ui;


import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JEditorPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.*;

import javax.swing.table.AbstractTableModel;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import java.awt.Color;

import javax.swing.text.html.HTMLDocument;


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
    private int pozKodu;
    private String tempKod; //używane przy zamienianiu typu zawartości kodu
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

    private JEditorPane kodHtml;
    private JScrollPane przewijanieKoduHtml;

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
        
        this.kodHtml = new JEditorPane();

        this.kodHtml.setContentType("text/plain");

        this.kodHtml.getDocument().addDocumentListener(new DocumentListener(){

            @Override
            public void insertUpdate(DocumentEvent e) {
                // TODO Auto-generated method stub
                if(kodHtml.isEditable()){
                    tempKod = kodHtml.getText();
                    System.out.println("insert"+tempKod);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                // TODO Auto-generated method stub
                if(kodHtml.isEditable()){
                    tempKod = kodHtml.getText();
                    System.out.println("removed"+tempKod);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // TODO Auto-generated method stub
                if(kodHtml.isEditable()){
                    tempKod = kodHtml.getText();
                    System.out.println("changed"+tempKod);
                }
            }
            
        });
        // TODO ustawienie zamiast JTextArea editor pane'a i pogrubianie aktualnej komendy
         
        

            //kod.setPreferredSize(new Dimension(100, 100));
        //this.przewijanieKodu = new JScrollPane(this.kod);
        this.kodHtml.setBorder(BorderFactory.createLoweredBevelBorder());
        this.przewijanieKoduHtml = new JScrollPane(this.kodHtml);
        this.przewijanieKoduHtml.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.przewijanieKoduHtml.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.black),"Brainfuck code"));
       
        this.wejscie = new JTextArea(4,20);
        this.wejscie.setBorder(BorderFactory.createLoweredBevelBorder());
        this.przewijanieWejscia = new JScrollPane(this.wejscie);
        this.przewijanieWejscia.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.przewijanieWejscia.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.black),"Input"));
    
        this.wyjscie = new JTextArea(4,20);
        this.wyjscie.setBorder(BorderFactory.createLoweredBevelBorder());
        this.wyjscie.setEnabled(false);
        this.przewijanieWyjscia = new JScrollPane(this.wyjscie);
        this.przewijanieWyjscia.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.przewijanieWyjscia.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.black),"Output"));
        
            panelWeWy.add(this.przewijanieWejscia, BorderLayout.NORTH);
            panelWeWy.add(this.przewijanieWyjscia, BorderLayout.SOUTH);
     
            
        
        // Wkładanie wszystkiego do ramki przy użyciu grid bag constraints
        wytyczne.fill = GridBagConstraints.BOTH;
        wytyczne.weightx = 1;
        wytyczne.weighty = 1;
        wytyczne.gridx = GridBagConstraints.RELATIVE;
        wytyczne.gridy = GridBagConstraints.RELATIVE;
        wytyczne.gridwidth = 1;
        wytyczne.gridheight = 2;
        
        this.add(this.przewijanieKoduHtml, wytyczne);     
    
        wytyczne.weightx = 0.5;
        wytyczne.weighty = 0.5;
        wytyczne.gridx = GridBagConstraints.RELATIVE;
        wytyczne.gridy = GridBagConstraints.RELATIVE;
        wytyczne.gridwidth = GridBagConstraints.REMAINDER;
        wytyczne.gridheight = 1;
        this.add(przewijanieWejscia, wytyczne);
    
        wytyczne.weightx = 0.5;
        wytyczne.weighty = 0.5;
        wytyczne.gridx = GridBagConstraints.RELATIVE;
        wytyczne.gridy = GridBagConstraints.RELATIVE;
        wytyczne.gridwidth = GridBagConstraints.REMAINDER;
        wytyczne.gridheight = 1;
        this.add(przewijanieWyjscia, wytyczne);
    
        wytyczne.fill = GridBagConstraints.NONE;
        wytyczne.weightx = 0;
        wytyczne.weighty = 0;
        wytyczne.gridx = GridBagConstraints.RELATIVE;
        wytyczne.gridy = GridBagConstraints.RELATIVE;
        wytyczne.gridwidth = GridBagConstraints.REMAINDER;
        wytyczne.gridheight = 1;
        tabela = new JTable(new ModelDanych());
       
        tabela.setTableHeader(null);
        JScrollPane przewijanieTabelki = new JScrollPane(tabela);
        //http://stackoverflow.com/questions/6523974/shrink-jscroll-pane-to-same-height-as-jtable
        Dimension d = tabela.getPreferredSize();
        przewijanieTabelki.setPreferredSize(
             new Dimension(d.width,tabela.getRowHeight()*4/*rows+1*/));
        
        przewijanieTabelki.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.black),"Memory status"));
        this.add(przewijanieTabelki, wytyczne);
        
        
        wytyczne.weightx = 0;
        wytyczne.weighty = 0;
        wytyczne.gridx = GridBagConstraints.RELATIVE;
        wytyczne.gridy = GridBagConstraints.RELATIVE;
        wytyczne.gridwidth = GridBagConstraints.REMAINDER;
        wytyczne.gridheight = 1;
        this.add(panelPrzewijania, wytyczne);
    
        wytyczne.weightx = 0;
        wytyczne.weighty = 0;
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
        this.setMinimumSize(prefferedSize);
        int wdwLeft = screenSize.width / 2 - prefferedSize.width /2;
        int wdwTop = screenSize.height /2 - prefferedSize.height /2;

        this.setLocation(wdwLeft, wdwTop);
    
    }

    @Override
    public void wyswietlInterfejs() {
        this.setVisible(true);
    }
    
    private String otocz(String wejscie, int indeks, String lewo, String prawo){
        int dl = wejscie.length();
        String pogrubiane;

        char znak;
        if(indeks>(dl-1) || indeks<0){
            return zamienLtGt(wejscie);
        }
        if(dl==0){
            return "";
        }
        znak = wejscie.charAt(indeks);
        if(znak == '<'){
            pogrubiane = "&lt;";
        }else if(znak == '>'){
            pogrubiane = "&gt;";
        }else if(znak == '\n'){
            pogrubiane = "\\n<br />";
        }
        else{
            pogrubiane = new String(""+znak+"");
        }
        if(dl==1){
            return lewo + pogrubiane + prawo;
        }
        if(indeks==0){
            return lewo + pogrubiane + prawo + zamienLtGt(wejscie.substring(1));
        }
        if(indeks==(dl-1)){
            return zamienLtGt(wejscie.substring(0, indeks)) + lewo + 
                    pogrubiane + prawo;
        }
        return zamienLtGt(wejscie.substring(0, indeks)) + lewo + 
                pogrubiane + prawo + zamienLtGt(wejscie.substring(indeks+1));
   
    }
    
    private String zamienLtGt(String zrodlo){
        return zrodlo.replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br />");
    }
 


    
    class Aktywator implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(e.getActionCommand().equals("uruchom")
                ||e.getActionCommand().equals("zakoncz")){
                if(aktywne==true){
                    aktywne = false;
                    uruchomZakoncz.setText("Run debugger");
                    uruchomZakoncz.setActionCommand("uruchom");
                    //Odgrubianie aktualnej komendy:
                    kodHtml.setEditable(!aktywne);
                    kodHtml.setContentType("text/plain");
                    kodHtml.setText(tempKod);
                    
                }else {
                    aktywne = true;
                    wyjscie.setText("");
                    uruchomZakoncz.setText("Stop debugger");
                    uruchomZakoncz.setActionCommand("zakoncz");
                    //Pogrubianie aktualnej komendy:
                    kodHtml.setEditable(!aktywne);
                    tempKod = kodHtml.getText();
                    Font f;
                    f = kodHtml.getFont();
                    kodHtml.setContentType("text/html");
                    String bodyRule = "body { font-family: " + f.getFamily() + "; " +
                            "font-size: " + f.getSize() + "pt;"
                                    + " }strong {color: white; background-color:black;}";
                    ((HTMLDocument)kodHtml.getDocument()).getStyleSheet().addRule(bodyRule);
                    pozKodu = 0;
                    kodHtml.setText(otocz(tempKod,
                            pozKodu, "<strong>", "</strong>"));
                }
                krok.setEnabled(aktywne);
                doPunktu.setEnabled(aktywne);
                wszystko.setEnabled(aktywne);
                wejscie.setEditable(!aktywne);
            }else if(e.getActionCommand().equals("step")){
                pozKodu++;
                kodHtml.setText(otocz(tempKod, 
                        pozKodu, "<strong>", "</strong>"));
            }else if(e.getActionCommand().equals("toBreakpoint")){
                while(pozKodu<tempKod.length() && tempKod.charAt(pozKodu++)!='#');
                kodHtml.setText(otocz(tempKod, 
                        pozKodu, "<strong>", "</strong>"));
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


    @Override
    public String zwrocKod() {
        //Żeby przypadkiem nie zwróciło kodu ze znakami html
        return this.tempKod;
        //return this.kod.getText();
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
    
    
    public static void main(String [] args){
        InterfejsGraficzny okno = new InterfejsGraficzny(null);
        
        //Testy funkcji otocz
        if(!("<>a</>").equals(okno.otocz("a",0,"<>","</>")))
                 System.out.println("Bład funkcji otocz dla <>a</>");
        
        if(!("").equals(okno.otocz("",0,"<>","</>")))
            System.out.println("Bład funkcji otocz dla pustego");
        
        if(!("asdf").equals(okno.otocz("asdf",17,"<>","</>")))
            System.out.println("Bład funkcji otocz dla asdf indeks 17");
        
        if(!("a<>s</>df").equals(okno.otocz("asdf",1,"<>","</>")))
            System.out.println("Bład funkcji otocz dla a<>s</>df");
        
        if(!("<>a</>sdf").equals(okno.otocz("asdf",0,"<>","</>")))
            System.out.println("Bład funkcji otocz dla <>a</>sdf");
        
        if(!("asd<>f</>").equals(okno.otocz("asdf",3,"<>","</>")))
            System.out.println("Bład funkcji otocz dla asd<>f</>");
        if(!("<>&lt;</>&gt;&lt;&gt;").equals(okno.otocz("<><>",0,"<>","</>")))
            System.out.println("Bład funkcji otocz dla <>&lt;</>><>");
        
        okno.wyswietlInterfejs();
    }

    //public JEditorPane("text/html", tekst kodu + znaczniki html <strong>wokół aktualnej komendy</strong>);
 
    
    

}
