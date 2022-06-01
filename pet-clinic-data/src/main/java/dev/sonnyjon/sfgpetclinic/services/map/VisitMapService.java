package dev.sonnyjon.sfgpetclinic.services.map;

import dev.sonnyjon.sfgpetclinic.model.Owner;
import dev.sonnyjon.sfgpetclinic.model.Pet;
import dev.sonnyjon.sfgpetclinic.model.Visit;
import dev.sonnyjon.sfgpetclinic.services.VisitService;

import java.util.Set;

/**
 * Created by Sonny on 6/1/2022.
 */
public class VisitMapService
    extends AbstractMapService<Visit, Long>
    implements VisitService
{

    @Override
    public Set<Visit> findAll()
    {
        return super.findAll();
    }

    @Override
    public Visit findById(Long id)
    {
        return super.findById(id);
    }

    @Override
    public Visit save(Visit object)
    {
        Pet pet = object.getPet();
        Owner owner = object.getPet().getOwner();

        if (pet == null || owner == null || pet.getId() == null || owner.getId() == null)
        {
            throw new RuntimeException("Invalid visit");
        }

        return super.save(object);
    }

    @Override
    public void delete(Visit object)
    {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id)
    {
        super.deleteById(id);
    }
}
