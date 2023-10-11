/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package racunopg;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import java.util.logging.Level;
import java.util.logging.Logger;
import racunopg.controller.Obrada;
import racunopg.controller.ObradaProizvod;
import racunopg.model.Kupac;
import racunopg.model.Proizvod;
import racunopg.util.HibernateUtil;
import racunopg.util.PocetniInsert;
import racunopg.util.RacunOpgException;
import java.math.BigDecimal;
import java.util.Date;
import racunopg.controller.ObradaKupac;
import racunopg.controller.ObradaRacun;
import racunopg.controller.ObradaUsluga;
import racunopg.model.Racun;
import racunopg.model.Usluga;
import racunopg.view.Izbornik;

/**
 *
 * @author pc
 */
public class RacunOpg {

    public static void main(String[] args) {
        
    
        
        new Izbornik().setVisible(true);
        
        
        
        
          //Provjera ručnog inserta            
//        ObradaRacun or = new ObradaRacun();
//        Racun r = new Racun();
//        
//            r.setNazivRacuna("1/11/2023");
//            r.setOpisPlacanja("Neki opis znači lorem ipmsun");
//            r.setPDV(new BigDecimal(25));
//            r.setVrijemeIzdavanja(new Date());
//          
//            or.setEntitet(r);
//            try {
//            or.create();
//        } catch (RacunOpgException e) {
//                System.out.println(e.getPoruka());
//        }
            
        
        //Proba za kontrole
//        ObradaProizvod op = new ObradaProizvod();
//        
//        Proizvod p = new Proizvod();
//        p.setNaziv("Prvi preko kontrola");
//        
//        op.setEntitet(p);  
//        try{
//            op.create();
//        }catch(RacunOpgException ex){
//            System.out.println(ex.getPoruka());
//        }
            
            
            
            //new PocetniInsert();
            
            //HibernateUtil.getSession();
            
//        Faker f = new Faker();
//        while(true){
//            System.out.println(f.beer().name());
//        }

    }
}

