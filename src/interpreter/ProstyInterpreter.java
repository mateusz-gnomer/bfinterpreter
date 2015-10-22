/**
 * 
 */
package interpreter;

import model.StanMaszyny;
/**
 * @author gn
 *
 */
public class ProstyInterpreter implements Interpreter {
    private StanMaszyny stanMaszyny;
    private int wartoscZawinieta;
    
    
    public ProstyInterpreter(StanMaszyny stanMaszyny){
        this.stanMaszyny = stanMaszyny;
        this.wartoscZawinieta = 255;
    }
    
    public void krok(){
        int temp;
        char komenda = this.stanMaszyny.aktualnaKomenda();
        if(komenda == 0){
            return;
        }
        this.stanMaszyny.ustawPozycjeKodu(this.stanMaszyny.pozycjaKodu()+1);
        switch(komenda){
        
        case '<':
            temp = this.stanMaszyny.pozycjaTasmy()-1;
            if(temp<0){
                temp = this.stanMaszyny.dlugoscTasmy()-1;
            }
            this.stanMaszyny.ustawPozycjeTasmy(temp);
            break;
            
        case '>':
            temp = this.stanMaszyny.pozycjaTasmy()+1;
            if(temp>=this.stanMaszyny.dlugoscTasmy()){
                temp = 0;
            }
            this.stanMaszyny.ustawPozycjeTasmy(temp);
            break;
            
        case '+':
            temp = this.stanMaszyny.wartoscTasmy()+1;
            if(temp>this.wartoscZawinieta){
                temp = 0;
            }
            this.stanMaszyny.ustawWartoscTasmy(temp);
            break;
            
        case '-':
            temp = this.stanMaszyny.wartoscTasmy()-1;
            if(temp<0){
                temp = this.wartoscZawinieta;
            }
            this.stanMaszyny.ustawWartoscTasmy(temp);
            break;
        }
        
    }
    public void uruchomDoPunktu(){
        
    }
    public void uruchomDoKonca(){
        
    }
    
    public int wartoscZawinieta(){
        return this.wartoscZawinieta;
    }
}
