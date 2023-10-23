/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package racunopg.util;

import com.github.javafaker.Faker;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import racunopg.model.Kupac;
import racunopg.model.Proizvod;
import racunopg.model.Racun;
import racunopg.model.Usluga;

/**
 *
 * @author pc
 */
public class PocetniInsert {
    
    public static final int BROJ_PROIZVODA=10;
    public static final int BROJ_USLUGA=10;
    public static final int BROJ_KUPACA=10;

    
    
    private Faker faker;
    private Session session;
    private List<Usluga>usluge;
    private List<Proizvod>proizvodi;
    private List<Kupac>kupci;
    
    
    public PocetniInsert() {
    faker = new Faker();
    session = HibernateUtil.getSession();
    usluge = new ArrayList<>();
    proizvodi = new ArrayList<>();
    kupci = new ArrayList<>();
    session.getTransaction().begin();
    kreirajProizvode();
    kreirajUsluge();
    kreirajKupce();
    
    
    
    session.getTransaction().commit();
    }

    private void kreirajProizvode() {
        
        Proizvod p;
        for(int i=0;i<BROJ_PROIZVODA;i++){
            p=new Proizvod();
            p.setNaziv(faker.beer().name());
            p.setCijena(new BigDecimal(faker.number().numberBetween(50, 1000)));
            p.setKolicina(new BigDecimal(faker.number().numberBetween(1, 300)));
            p.setOpis(faker.lorem().sentence(10));
            session.persist(p);
            proizvodi.add(p);
        }
        
    }
    
    private void kreirajUsluge(){
        
        Usluga u;
        for(int i=0;i<BROJ_USLUGA;i++){
            u=new Usluga();
            u.setNaziv(faker.beer().name());
            u.setCijenaPoHa(new BigDecimal(faker.number().numberBetween(50, 100)));
            u.setKolicina(new BigDecimal(faker.number().numberBetween(1, 500)));
            u.setOpis(faker.lorem().sentence(10));
            session.persist(u);
            usluge.add(u);
        }
            
    }
    
    private void kreirajKupce(){
        Kupac k;
        for(int i=0;i<BROJ_KUPACA;i++){
            k=new Kupac();
            k.setNazivSubjekta(faker.company().name());
            k.setIBAN(faker.business().creditCardNumber());
            k.setOIB(Alati.getOib());
            k.setAdresa(faker.address().fullAddress());
            session.persist(k);
            kupci.add(k);
        }
    }
    
    private void kreirajRacune(){
        Racun r;
        Faker faker = new Faker();
        
    }
    
}
