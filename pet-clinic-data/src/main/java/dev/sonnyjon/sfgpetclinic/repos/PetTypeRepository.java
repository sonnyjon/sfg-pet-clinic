package dev.sonnyjon.sfgpetclinic.repos;

import dev.sonnyjon.sfgpetclinic.model.PetType;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Sonny on 6/1/2022.
 */
public interface PetTypeRepository extends CrudRepository<PetType, Long>
{
}
