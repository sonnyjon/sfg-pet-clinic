package dev.sonnyjon.sfgpetclinic.model;

/**
 * Created by Sonny on 5/30/2022.
 */
public class Specialty extends BaseEntity
{
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
