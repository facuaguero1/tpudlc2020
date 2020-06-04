package tpudlc.classes;

import javax.persistence.*;
import tpudlc.dao.DalEntity;


@Entity
@Table(name = "DOCUMENTOS")
@NamedQueries(
        {
            @NamedQuery(name = "Documento.findAll", query = "SELECT d FROM Documento d"),
            @NamedQuery(name = "Documento.findById", query = "SELECT d FROM Documento d WHERE d.idDocumento = :idDocumento"),
            @NamedQuery(name = "Documento.findByNombreArchivo", query = "SELECT d FROM Documento d WHERE d.nombreArchivo = :nombreArchivo"),
            @NamedQuery(name = "Documento.findMaxId", query = "SELECT d FROM Documento d ORDER BY d.idDocumento DESC")
        }
)
public class Documento implements DalEntity {  

   
   
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_documento")
    private Integer idDocumento;
    
    @Column(unique = true, name = "nombre_archivo")
    private String nombreArchivo;
    
    @Column(unique = false, name = "primeras_lineas")
    private String primerasLineas;
    
    @Column(unique = true, name = "path")
    private String path;
    
    

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
    
    public String getPrimerasLineas() {
        return primerasLineas;
    }

    public void setPrimerasLineas(String primerasLineas) {
        this.primerasLineas = primerasLineas;
    }
    
     public String getPath() {
        return path;
    }
 
    public void setPath(String path) {
        this.path = path;
    }

    public Documento() {
    }
    
    @Override
    public int hashCode() { return this.idDocumento.hashCode(); }
    
}
