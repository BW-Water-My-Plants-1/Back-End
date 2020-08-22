package com.dgbuild.watermyplants.repositories;

import com.dgbuild.watermyplants.models.Plant;
import org.springframework.data.repository.CrudRepository;

public interface PlantRepository extends CrudRepository<Plant, Long> {
}
