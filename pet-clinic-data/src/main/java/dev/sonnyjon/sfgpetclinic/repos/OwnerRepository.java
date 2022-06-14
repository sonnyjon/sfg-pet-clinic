package dev.sonnyjon.sfgpetclinic.repos;

import dev.sonnyjon.sfgpetclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Sonny on 6/1/2022.
 */
public interface OwnerRepository extends CrudRepository<Owner, Long>
{
    Owner findByLastName(String lastName);

    List<Owner> findByLastNameContaining(String lastName);
}
