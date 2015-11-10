/**
 * 
 */
package model;

/**
 * @author gn
 *
 */
public class ProstyStanMaszyny implements StanMaszyny {
    private int dlugoscTasmy = 30000;
    private int tasma[];
    private int pozycjaTasmy;
    
    private String wejscie;
    private String wyjscie;
    private int pozycjaWejscia;
    
    private String kod;
    private int pozycjaKodu;
    
    public ProstyStanMaszyny(){
        this.resetuj();
    }
    
    public void resetuj(){
        this.pozycjaTasmy = 0;
        this.pozycjaWejscia = 0;
        this.pozycjaKodu = 0;
        this.tasma = new int[dlugoscTasmy];
        
        for(int i = 0; i < dlugoscTasmy; i++){
            tasma[i] = 0;
        }
        
        this.wejscie = new String("");
        this.wyjscie = new String("");
        this.kod = new String("");
    };
    
    public int wartoscTasmy(){
        return this.tasma[pozycjaTasmy];
    };
    public int pozycjaTasmy(){
        return this.pozycjaTasmy;
    };
    public void ustawPozycjeTasmy(int pozycjaTasmy){
        this.pozycjaTasmy = pozycjaTasmy;
    };
    public void ustawWartoscTasmy(int wartosc){
        this.tasma[this.pozycjaTasmy] = wartosc;
    };
    
    public int dlugoscTasmy(){
        return this.dlugoscTasmy;
    };
    
    public int[] zwrocTasme(){
        return this.tasma;
    }
    
    public char aktualneWejscie(){
        if(this.wejscie.length()<=this.pozycjaWejscia ||
                this.pozycjaWejscia<0){
            return 0;
        }
        return this.wejscie.charAt(this.pozycjaWejscia);
    };
    
    public int pozycjaWejscia(){
        return this.pozycjaWejscia;
    };
    
    public void ustawPozycjeWejscia(int pozycjaWejscia){
        // TODO ustawić sprawdzanie czy podana wartość jest ujemna
        this.pozycjaWejscia = pozycjaWejscia;
    };
    public String zwrocWejscie(){
        return this.wejscie;
    };
    public String zwrocWyjscie(){
        return this.wyjscie;
    }
    public void ustawWejscie(String wejscie){
        this.wejscie = wejscie;
    };
    public void ustawWyjscie(String wyjscie){
        this.wyjscie = wyjscie;
    };
    
    public void ustawKod(String kod){
        this.kod = kod;
    };
    public void ustawPozycjeKodu(int pozycjaKodu){
        this.pozycjaKodu = pozycjaKodu;
    };
    public int pozycjaKodu(){
        return this.pozycjaKodu;
    }
    public char aktualnaKomenda(){
        if(pozycjaKodu>=this.kod.length() || pozycjaKodu < 0){
            return 0;
        }
        return this.kod.charAt(this.pozycjaKodu);
    };
    
    // TODO boolean maKod, maWejscie
    
}
