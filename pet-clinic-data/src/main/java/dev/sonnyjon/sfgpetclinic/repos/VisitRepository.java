package dev.sonnyjon.sfgpetclinic.repos;

import dev.sonnyjon.sfgpetclinic.model.Visit;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Sonny on 6/1/2022.
 */
public interface VisitRepository extends CrudRepository<Visit, Long>
{
}
