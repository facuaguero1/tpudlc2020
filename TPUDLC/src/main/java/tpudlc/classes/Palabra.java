/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpudlc.classes;

import javax.persistence.*;


/**
 *
 * @author facundo
 */
@Entity
@Table(name = "PALABRAS")
public class Palabra {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPalabra;
    
    @Column(unique = true)
    private String palabra;
    
    @Column
    private Integer nr;
    
    @Column
    private Integer maxtf;

    public Long getIdPalabra() {
        return idPalabra;
    }

    public String getPalabra() {
        return palabra;
    }

    public Integer getNr() {
        return nr;
    }

    public Integer getMaxtf() {
        return maxtf;
    }

    public void setIdPalabra(Long idPalabra) {
        this.idPalabra = idPalabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public void setNr(Integer nr) {
        this.nr = nr;
    }

    public void setMaxtf(Integer maxtf) {
        this.maxtf = maxtf;
    }
    
}
