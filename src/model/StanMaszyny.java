package model;

public interface StanMaszyny {
    
    public int wartoscTasmy();
    public int pozycjaTasmy();
    public void ustawPozycjeTasmy(int pozycjaTasmy);
    public void ustawWartoscTasmy(int wartosc);
    public int[] zwrocTasme();
    
    public int dlugoscTasmy();
    
    public char aktualneWejscie();
    public int pozycjaWejscia();
    public void ustawPozycjeWejscia(int pozycjaWejscia);
    public String zwrocWejscie();
    public String zwrocWyjscie();
    public void ustawWejscie(String wejscie);
    public void ustawWyjscie(String wyjscie);
    
    public void ustawKod(String kod);
    public void ustawPozycjeKodu(int pozycjaKodu);
    public int pozycjaKodu();
    public char aktualnaKomenda();
    
    public void resetuj();
}
