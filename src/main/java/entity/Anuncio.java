package entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name="anuncio")
@NamedQueries({
        @NamedQuery(name = "Anuncio.getAllAnuncios", query = "select a from anuncio a"),
        @NamedQuery(name = "Anuncio.getAnuncio", query = "select a from anuncio a where a.id=:id")
})


public class Anuncio implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String tituloAnuncio;
    @Column
    private Integer valor;
    @Column
    private String marcaKey;
    @Column
    private Integer ano;
    @Column
    private String keyWords;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTituloAnuncio() {
        return tituloAnuncio;
    }

    public void setTituloAnuncio(String tituloAnuncio) {
        this.tituloAnuncio = tituloAnuncio;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public String getMarcaKey() {
        return marcaKey;
    }

    public void setMarcaKey(String marcaKey) {
        this.marcaKey = marcaKey;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }
}
