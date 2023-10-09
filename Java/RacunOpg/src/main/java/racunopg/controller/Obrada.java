/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package racunopg.controller;

import java.util.List;
import org.hibernate.Session;
import racunopg.model.Entitet;
import racunopg.util.HibernateUtil;
import racunopg.util.RacunOpgException;

/**
 *
 * @author pc
 */
public abstract class Obrada<T extends Entitet>{
    
    protected T entitet;
    protected Session session;
    public abstract List<T> read();
    protected abstract void kontrolaUnos() throws RacunOpgException;
    protected abstract void kontrolaPromjena() throws RacunOpgException;
    protected abstract void kontrolaBrisanje() throws RacunOpgException;
    
    public Obrada(){
        session = HibernateUtil.getSession();
    }
    
    public Obrada(T entitet){
        this();
        this.entitet=entitet;
    }
    
    public void create() throws RacunOpgException{
        kontrolaNull();
        entitet.setSifra(null);
        kontrolaUnos();
        persist();
    }
    
    public void update() throws RacunOpgException{
        kontrolaNull();
        kontrolaPromjena();
        persist();
    }
    
    public void delete() throws RacunOpgException{
        kontrolaNull();
        kontrolaBrisanje();
        session.beginTransaction();
        session.remove(entitet);
        session.getTransaction().commit();
    }
    
    private void persist(){
        session.beginTransaction();
        session.persist(entitet);
        session.getTransaction().commit();
    }
    
    private void kontrolaNull() throws RacunOpgException{
       if(entitet==null){
            throw new RacunOpgException("Entitet je null");
        } 
       
    }
    

    public T getEntitet() {
        return entitet;
    }

    public void setEntitet(T entitet) {
        this.entitet = entitet;
    }
    
    
    public void refresh(){
        if(entitet!=null){
            session.refresh(entitet);
        }
    }
   
}