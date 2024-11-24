package apptive.study.repository;

import java.util.Optional;

import apptive.study.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);
    boolean existsByName(String name);

}


