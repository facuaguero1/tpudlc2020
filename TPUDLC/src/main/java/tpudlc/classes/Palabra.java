package tpudlc.classes;

import javax.persistence.*;
import tpudlc.dao.DalEntity;


@Entity
@Table(name = "PALABRAS")
@NamedQueries(
        {
            @NamedQuery(name = "Palabra.findAll", query = "SELECT p from Palabra p"),
            @NamedQuery(name = "Palabra.findById", query = "SELECT p from Palabra p WHERE p.idPalabra = :idPalabra"),
            @NamedQuery(name = "Palabra.findByPalabra", query = "SELECT p from Palabra p WHERE p.palabra = :palabra"),
            @NamedQuery(name = "Palabra.findMaxId", query = "SELECT p FROM Palabra p ORDER BY p.idPalabra DESC")
        }
)
public class Palabra implements DalEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_palabra")
    private Integer idPalabra;
    
    @Column(unique = true)
    private String palabra;
    
    @Column
    private Integer nr;
    
    @Column
    private Integer maxtf;

    public Palabra(String palabra) {
        this.palabra = palabra;
        this.nr = 0;
        this.maxtf = 0;
    }
    
    public Palabra(Integer id, String palabra) {
        this.idPalabra = id;
        this.palabra = palabra;
        this.nr = 0;
        this.maxtf = 0;
    }
    
    public Palabra() {
    }

    public Integer getIdPalabra() {
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

    public void setIdPalabra(Integer idPalabra) {
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
    
    public void increaseNr() {
        this.nr++;
    }
    
}
