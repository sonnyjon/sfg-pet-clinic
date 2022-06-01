package dev.sonnyjon.sfgpetclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Sonny on 5/30/2022.
 */
@Entity
@Table(name = "specialties")
public class Specialty extends BaseEntity
{
    @Column(name = "description")
    private String description;

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}
