package softuni.exam.models.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "stars")
public class Star {

    public Star() {};

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, name = "light_years")
    private double lightYears;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false, name = "star_type")
    private String starType;

    @Column(nullable = false)
    @OneToMany(mappedBy = "star")
    private List<Astronomer> observers;

    @ManyToOne()
    private Constellation constellation;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLightYears() {
        return lightYears;
    }

    public void setLightYears(double lightYears) {
        this.lightYears = lightYears;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStarType() {
        return starType;
    }

    public void setStarType(String starType) {
        this.starType = starType;
    }

    public List<Astronomer> getObservers() {
        return observers;
    }

    public void setObservers(List<Astronomer> observers) {
        this.observers = observers;
    }

    public Constellation getConstellation() {
        return constellation;
    }

    public void setConstellation(Constellation constellation) {
        this.constellation = constellation;
    }

    @Override
    public String toString() {
        return String.format("Star: %s\n" +
                        "   *Distance: %.2f light years\n" +
                        "   **Description: %s\n" +
                        "   ***Constellation: %s"
                , this.getName(), this.getLightYears(),
                this.getDescription(), this.getConstellation().getName());

    }

}
