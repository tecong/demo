package org.pepo.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findByModelContaining(String model);
}
