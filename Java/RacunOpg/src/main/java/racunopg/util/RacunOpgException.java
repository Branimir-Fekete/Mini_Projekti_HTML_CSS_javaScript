/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package racunopg.util;

/**
 *
 * @author pc
 */
public class RacunOpgException extends Exception {
    
    private String poruka;
    
    public RacunOpgException(String poruka){
        super(poruka);
        this.poruka = poruka;
    }

    public String getPoruka() {
        return poruka;
    }
}
