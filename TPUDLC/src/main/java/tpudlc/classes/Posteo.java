package tpudlc.classes;

import javax.persistence.*;
import tpudlc.dao.DalEntity;


@Entity
@Table(name = "POSTEOS")
@NamedQueries(
        {
            @NamedQuery(name = "Posteo.findAll", query = "SELECT p FROM Posteo p"),
            @NamedQuery(name = "Posteo.findById", query = "SELECT p FROM Posteo p WHERE p.idPosteo = :idPosteo"),
            @NamedQuery(name = "Posteo.findByPalabra", query = "SELECT p FROM Posteo p WHERE p.palabra = :palabra"),
            @NamedQuery(name = "Posteo.findByPalabraOrderedByTf", query = "SELECT p FROM Posteo p WHERE p.palabra = :palabra ORDER BY p.tf DESC"),
            @NamedQuery(name = "Posteo.findByDocumento", query = "SELECT p FROM Posteo p WHERE p.documento = :documento"),
            @NamedQuery(name = "Posteo.findByPalabraAndDocumento", query = "SELECT p FROM Posteo p WHERE p.palabra = :palabra AND p.documento = :documento"),
            @NamedQuery(name = "Posteo.findMaxId", query = "SELECT p FROM Posteo p ORDER BY p.idPosteo DESC")
        }
)
public class Posteo implements DalEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_posteo")
    private Integer idPosteo;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_palabra")
    private Palabra palabra;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_documento")
    private Documento documento;
    
    @Column
    private Integer tf;

    public Posteo(Palabra palabra, Documento documento) {
        this.palabra = palabra;
        this.documento = documento;
        this.tf = 0;
    }
    
    public Posteo(Integer id, Palabra palabra, Documento documento) {
        this.idPosteo = id;
        this.palabra = palabra;
        this.documento = documento;
        this.tf = 0;
    }
    
    public Posteo() {
    }

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

    public void setIdPosteo(Integer idPosteo) {
        this.idPosteo = idPosteo;
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
    
    public void increaseTf() { this.tf++; }
    
}
