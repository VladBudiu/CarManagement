package VladBudiu.proiectPj.Entities.Masini;

import VladBudiu.proiectPj.Constants.Culoare;
import VladBudiu.proiectPj.Constants.TipCombustibil;
import VladBudiu.proiectPj.Entities.User.User;
import jakarta.persistence.*;

@Entity
@Table(name="masini")
public class Masina {
    @Id
    @Column(name="id")
    private String numarInmatriculare;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    @Column(name="marca")
    private String brand;
    @Column(name="model")
    private String model;
    @Column(name="culoare")
    private Culoare culoare;
    @Column(name="anFabricare")
    private int anFabricare;
    @Column(name="capacitateCilindrica")
    private int capacitateCilindrica;
    @Column(name="tipCombustibil")
    private TipCombustibil tipCombustibil;
    @Column(name="putere")
    private int putere;
    @Column(name="cuplu")
    private int cuplu;
    @Column(name="volumPortbagaj")
    private int volumPortbagaj;
    @Column(name="pret")
    private float pret;


    public Masina() {
    }

    public String getNumarInmatriculare() {
        return numarInmatriculare;
    }

    public void setNumarInmatriculare(String numarInmatriculare) {
        this.numarInmatriculare = numarInmatriculare;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Culoare getCuloare() {
        return culoare;
    }

    public void setCuloare(Culoare culoare) {
        this.culoare = culoare;
    }

    public int getAnFabricare() {
        return anFabricare;
    }

    public void setAnFabricare(int anFabricare) {
        this.anFabricare = anFabricare;
    }

    public int getCapacitateCilindrica() {
        return capacitateCilindrica;
    }

    public void setCapacitateCilindrica(int capacitateCilindrica) {
        this.capacitateCilindrica = capacitateCilindrica;
    }

    public TipCombustibil getTipCombustibil() {
        return tipCombustibil;
    }

    public void setTipCombustibil(TipCombustibil tipCombustibil) {
        this.tipCombustibil = tipCombustibil;
    }

    public int getPutere() {
        return putere;
    }

    public void setPutere(int putere) {
        this.putere = putere;
    }

    public int getCuplu() {
        return cuplu;
    }

    public void setCuplu(int cuplu) {
        this.cuplu = cuplu;
    }

    public int getVolumPortbagaj() {
        return volumPortbagaj;
    }

    public void setVolumPortbagaj(int volumPortbagaj) {
        this.volumPortbagaj = volumPortbagaj;
    }

    public float getPret() {
        return pret;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }
}
