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
    private Integer idDocumento;
    
    @Column(unique = true)
    private String nombreArchivo;
}