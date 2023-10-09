/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package racunopg.controller;

import java.util.List;
import racunopg.model.Kupac;
import racunopg.util.Alati;
import racunopg.util.RacunOpgException;

/**
 *
 * @author pc
 */
public class ObradaKupac extends Obrada<Kupac>{

    @Override
    public List<Kupac> read() {
  
        return session.createQuery("from Polaznik",Kupac.class).list();
  
    }

    @Override
    protected void kontrolaUnos() throws RacunOpgException {
      kontrolaNazivSubjekta();
      kontrolaOIB();
      kontrolaIBAN();
    }

    @Override
    protected void kontrolaPromjena() throws RacunOpgException {
  
    }

    @Override
    protected void kontrolaBrisanje() throws RacunOpgException {
   
    }
    
      private void kontrolaNazivSubjekta() throws RacunOpgException {
        if (entitet.getNazivSubjekta() == null || entitet.getNazivSubjekta().isEmpty()) {
            throw new RacunOpgException("Naziv subjekta je obavezan");
        }
    }
    
    protected void kontrolaOIB() throws RacunOpgException {
       
       if(!Alati.isValjanOIB(entitet.getOIB())){
           throw new RacunOpgException("OIB nije valjan");
       }  

    }
    
    private void kontrolaIBAN() throws RacunOpgException {
    String iban = entitet.getIBAN();
    if (iban == null || iban.isEmpty()) {
        throw new RacunOpgException("IBAN je obavezan");
    }
  }
    
}
