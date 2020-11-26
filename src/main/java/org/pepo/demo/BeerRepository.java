package org.pepo.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BeerRepository extends JpaRepository<Beer, Long> {

    List<Beer> findByNameContaining(String name);
}
