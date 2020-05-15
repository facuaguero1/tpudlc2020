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
@Table(name = "DOCUMENTOS")
public class Documento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_documento")
    private Integer idDocumento;
    
    @Column(unique = true, name = "nombre_archivo")
    private String nombreArchivo;

    public Integer getIdDocumento() {
        return idDocumento;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setIdDocumento(Integer idDocumento) {
        this.idDocumento = idDocumento;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public Documento(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public Documento() {
    }
    
    
    
    
}
