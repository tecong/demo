package org.pepo.demo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "beers")
public class Beer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private Long id;
    private String name;
    private String brewery;
    private String type;

    public Beer() {

    }

    public Beer(String name, String brewery, String type) {
        this.name = name;
        this.brewery = brewery;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrewery() {
        return brewery;
    }

    public void setBrewery(String brewery) {
        this.brewery = brewery;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("Beer[id=%d, name='%s', type='%s', brewery='%s']", id, name, type, brewery);
    }
}
