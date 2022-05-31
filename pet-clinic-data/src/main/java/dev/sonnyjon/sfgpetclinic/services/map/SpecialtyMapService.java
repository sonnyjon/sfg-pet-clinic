package dev.sonnyjon.sfgpetclinic.services.map;

import dev.sonnyjon.sfgpetclinic.model.Specialty;
import dev.sonnyjon.sfgpetclinic.services.SpecialtyService;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by Sonny on 5/31/2022.
 */
@Service
public class SpecialtyMapService
    extends AbstractMapService<Specialty, Long>
    implements SpecialtyService
{
    @Override
    public Set<Specialty> findAll()
    {
        return super.findAll();
    }

    @Override
    public Specialty findById(Long id)
    {
        return super.findById(id);
    }

    @Override
    public Specialty save(Specialty object)
    {
        return super.save(object);
    }

    @Override
    public void delete(Specialty object)
    {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id)
    {
        super.deleteById(id);
    }
}
