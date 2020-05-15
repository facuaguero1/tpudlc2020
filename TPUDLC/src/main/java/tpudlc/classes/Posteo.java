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
@Table(name = "POSTEOS")
public class Posteo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idPosteo;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idPalabra")
    private Palabra palabra;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idDocumento")
    private Documento documento;
    
    @Column
    private Integer tf;

    public Integer getIdPosteo() {
        return idPosteo;
    }

    public Palabra getPalabra() {
        return palabra;
    }

    public Documento getDocumento() {
        return documento;
    }

    public Integer getTf() {
        return tf;
    }

    public void setPalabra(Palabra palabra) {
        this.palabra = palabra;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public void setTf(Integer tf) {
        this.tf = tf;
    }
    
}
