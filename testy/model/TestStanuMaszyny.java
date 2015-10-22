package model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestStanuMaszyny {
    
    private StanMaszyny stanMaszyny;
    @Before
    public void setUp() throws Exception {
        stanMaszyny = new ProstyStanMaszyny();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testInicjalizacji() {
        assertTrue(stanMaszyny.wartoscTasmy()==0);
        assertTrue(stanMaszyny.pozycjaTasmy()==0);
        
        assertTrue(stanMaszyny.dlugoscTasmy()==30000);
        
        
        assertTrue(stanMaszyny.aktualneWejscie()==0);
        assertTrue(stanMaszyny.pozycjaWejscia()==0);
        assertTrue(stanMaszyny.zwrocWejscie().length()==0);
        
        assertTrue(stanMaszyny.zwrocWyjscie().length()==0);
        
        assertTrue(stanMaszyny.pozycjaKodu() == 0);
        assertTrue(stanMaszyny.aktualnaKomenda() == 0);
    }
    
    @Test 
    public void testProstejManipulacjiTasma(){
        stanMaszyny.ustawPozycjeTasmy(5);
        stanMaszyny.ustawWartoscTasmy(17);
        
        assertTrue(stanMaszyny.wartoscTasmy()==17);
        assertTrue(stanMaszyny.pozycjaTasmy()==5);
    }
    
    @Test
    public void testProstejManipulacjiWejsciem(){
        stanMaszyny.ustawWejscie("abcdefgh");
        stanMaszyny.ustawPozycjeWejscia(3);
        
        assertTrue(stanMaszyny.aktualneWejscie()=='d');
        assertTrue(stanMaszyny.pozycjaWejscia()==3);
        assertTrue("abcdefgh".equals(stanMaszyny.zwrocWejscie()));
    }
    
    @Test
    public void testWyjscia(){
        stanMaszyny.ustawWyjscie("xyz");
        assertTrue("xyz".equals(stanMaszyny.zwrocWyjscie()));
    }
    
    @Test
    public void testPoprawnegoKodu(){
        stanMaszyny.ustawKod("><>");
        stanMaszyny.ustawPozycjeKodu(1);
        assertTrue(stanMaszyny.pozycjaKodu()==1);
        assertTrue(stanMaszyny.aktualnaKomenda()=='<');
        
        stanMaszyny.ustawPozycjeKodu(3);
        
        // TODO //if pozycja kodu >=dlkodu return 0
        assertTrue(stanMaszyny.pozycjaKodu()==3); 
        assertTrue(stanMaszyny.aktualnaKomenda()==0);
    }
    
    @Test
    public void testResetowania(){
        this.stanMaszyny.ustawKod(">++");
        this.stanMaszyny.ustawWyjscie("wyjscie");
        this.stanMaszyny.ustawWejscie("wejscie");
        this.stanMaszyny.ustawPozycjeKodu(1);
        
        assertTrue(this.stanMaszyny.aktualnaKomenda()=='+');
        assertTrue("wyjscie".equals(this.stanMaszyny.zwrocWyjscie()));
        assertTrue("wejscie".equals(this.stanMaszyny.zwrocWejscie()));
        assertTrue(this.stanMaszyny.pozycjaKodu()==1);
        
        this.stanMaszyny.resetuj();
        
        assertTrue(this.stanMaszyny.aktualnaKomenda()==0);
        assertTrue("".equals(this.stanMaszyny.zwrocWyjscie()));
        assertTrue("".equals(this.stanMaszyny.zwrocWejscie()));
        assertTrue(this.stanMaszyny.pozycjaKodu()==0);
    }

}
