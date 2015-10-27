/**
 * 
 */
package interpreter;

//import java.util.List;
//import java.util.ArrayList;

import model.StanMaszyny;
/**
 * @author gn
 *
 */
public class ProstyInterpreter implements Interpreter{
    private StanMaszyny stanMaszyny;
    private int wartoscZawinieta;
//    private int licznikZagniezdzenia;
    // private List<Integer> pozycjeOtwierajace;
    
    public ProstyInterpreter(StanMaszyny stanMaszyny){
        this.stanMaszyny = stanMaszyny;
        this.wartoscZawinieta = 255;
 //       this.licznikZagniezdzenia = 0;
        
        //this.pozycjeOtwierajace = new ArrayList<Integer>();
    }
    
    public void krok(){
        int temp;
        char znak;
        char komenda = this.stanMaszyny.aktualnaKomenda();
        if(komenda == 0){
            return;
        }

        switch(komenda){
        
        case '<':
            temp = this.stanMaszyny.pozycjaTasmy()-1;
            if(temp<0){
                temp = this.stanMaszyny.dlugoscTasmy()-1;
            }
            this.stanMaszyny.ustawPozycjeTasmy(temp);
            this.stanMaszyny.ustawPozycjeKodu(this.stanMaszyny.pozycjaKodu()+1);
            break;
            
        case '>':
            temp = this.stanMaszyny.pozycjaTasmy()+1;
            if(temp>=this.stanMaszyny.dlugoscTasmy()){
                temp = 0;
            }
            this.stanMaszyny.ustawPozycjeTasmy(temp);
            this.stanMaszyny.ustawPozycjeKodu(this.stanMaszyny.pozycjaKodu()+1);
            break;
            
        case '+':
            temp = this.stanMaszyny.wartoscTasmy()+1;
            if(temp>this.wartoscZawinieta){
                temp = 0;
            }
            this.stanMaszyny.ustawWartoscTasmy(temp);
            this.stanMaszyny.ustawPozycjeKodu(this.stanMaszyny.pozycjaKodu()+1);
            break;
            
        case '-':
            temp = this.stanMaszyny.wartoscTasmy()-1;
            if(temp<0){
                temp = this.wartoscZawinieta;
            }
            this.stanMaszyny.ustawWartoscTasmy(temp);
            this.stanMaszyny.ustawPozycjeKodu(this.stanMaszyny.pozycjaKodu()+1);
            break;
        case '.':
            znak = (char) this.stanMaszyny.wartoscTasmy();
            this.stanMaszyny.ustawWyjscie(
                    this.stanMaszyny.zwrocWyjscie() + znak
            );
            this.stanMaszyny.ustawPozycjeKodu(this.stanMaszyny.pozycjaKodu()+1);
            break;
        case ',':
            if(this.stanMaszyny.zwrocWejscie().length()
                    >this.stanMaszyny.pozycjaWejscia()){
                znak  = this.stanMaszyny.zwrocWejscie().
                        charAt(this.stanMaszyny.pozycjaWejscia()
                );
                this.stanMaszyny.ustawPozycjeWejscia(
                        this.stanMaszyny.pozycjaWejscia()+1
                );
            } else{
                znak = 0;
            }
            this.stanMaszyny.ustawWartoscTasmy((int) znak);
            this.stanMaszyny.ustawPozycjeKodu(this.stanMaszyny.pozycjaKodu()+1);
            break;
        case '[':
            if(this.stanMaszyny.wartoscTasmy()==0){
                przejdzDoZamykajacego();
            }    
            this.stanMaszyny.ustawPozycjeKodu(this.stanMaszyny.pozycjaKodu()+1);
            break;
            
        case ']':
            wrocDoOtwierajacego();
            break;
        default:
            break;
        }
        
        
    }
    
    private void dodajOtwierajacy(){

        //this.pozycjeOtwierajace.add(this.stanMaszyny.pozycjaKodu());
    }
    private void przejdzDoZamykajacego(){
        int temp = 1;
        while(temp != 0){
            this.stanMaszyny.ustawPozycjeKodu(
                    this.stanMaszyny.pozycjaKodu()+1);
            if(this.stanMaszyny.aktualnaKomenda()=='['){
                temp++;
            }
            if(this.stanMaszyny.aktualnaKomenda()==']'){
                temp--;
            }
        }

    }
    private void wrocDoOtwierajacego(){
        int temp = 1;
        while(temp != 0){
            this.stanMaszyny.ustawPozycjeKodu(
                    this.stanMaszyny.pozycjaKodu()-1);
            if(this.stanMaszyny.aktualnaKomenda()=='['){
                temp--;
            }
            if(this.stanMaszyny.aktualnaKomenda()==']'){
                temp++;
            }
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
