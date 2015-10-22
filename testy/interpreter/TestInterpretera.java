package interpreter;

import static org.junit.Assert.*;

import model.StanMaszyny;
import model.ProstyStanMaszyny;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestInterpretera {

    private StanMaszyny stanMaszyny;
    private Interpreter interpreter;
    @Before
    public void setUp() throws Exception {
        this.stanMaszyny = new ProstyStanMaszyny();
        this.interpreter = new ProstyInterpreter(stanMaszyny);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testKrokKomenda1() {
        this.stanMaszyny.ustawKod(">");
        this.interpreter.krok();
        assertTrue(this.stanMaszyny.pozycjaKodu()==1);
        assertTrue(this.stanMaszyny.pozycjaTasmy()==1);
        assertTrue(this.stanMaszyny.aktualnaKomenda()==0);
    }
    @Test
    public void testKrokKomenda2() {
        this.stanMaszyny.ustawKod("<");
        this.interpreter.krok();
        assertTrue(this.stanMaszyny.pozycjaKodu()==1);
        assertTrue(this.stanMaszyny.pozycjaTasmy()==
                this.stanMaszyny.dlugoscTasmy()-1);
        assertTrue(this.stanMaszyny.aktualnaKomenda()==0);
    }
    @Test
    public void testKrokKomenda1i2() {
        this.stanMaszyny.ustawKod("<>");
        this.interpreter.krok();

        assertTrue(this.stanMaszyny.pozycjaKodu()==1);
        assertTrue(this.stanMaszyny.pozycjaTasmy()==
                this.stanMaszyny.dlugoscTasmy()-1);
        assertTrue(this.stanMaszyny.aktualnaKomenda()=='>');
        
        this.interpreter.krok();
        assertTrue(this.stanMaszyny.pozycjaKodu()==2);
        assertTrue(this.stanMaszyny.pozycjaTasmy()==0);
    }
    
    @Test
    public void testKrokKomenda3() {
        this.stanMaszyny.ustawKod("+");
        this.interpreter.krok();

        assertTrue(this.stanMaszyny.pozycjaKodu()==1);
        assertTrue(this.stanMaszyny.pozycjaTasmy()==0);
        assertTrue(this.stanMaszyny.wartoscTasmy()==1);
    }
    
    @Test
    public void testKrokKomenda4() {
        this.stanMaszyny.ustawKod("--");
        this.interpreter.krok();

        assertTrue(this.stanMaszyny.pozycjaKodu()==1);
        assertTrue(this.stanMaszyny.pozycjaTasmy()==0);
        assertTrue(this.stanMaszyny.wartoscTasmy()==
                this.interpreter.wartoscZawinieta());
        
        this.interpreter.krok();
        if(this.interpreter.wartoscZawinieta()==0){
            assertTrue(this.stanMaszyny.wartoscTasmy()==0);
        }else{
            assertTrue(this.stanMaszyny.wartoscTasmy()==
                    this.interpreter.wartoscZawinieta()-1);
        }
    }
    
    @Test
    public void testKrokKomenda4i3(){
        this.stanMaszyny.ustawKod("-+");
        this.interpreter.krok();
        this.interpreter.krok();
        
        if(this.interpreter.wartoscZawinieta()==0){
            assertTrue(this.stanMaszyny.wartoscTasmy()==1);
        }else{
            assertTrue(this.stanMaszyny.wartoscTasmy()==0);
        }
    }
    

}
