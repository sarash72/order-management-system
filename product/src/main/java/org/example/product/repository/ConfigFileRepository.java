package org.example.product.repository;

import org.example.product.entity.ConfigFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigFileRepository extends JpaRepository<ConfigFileEntity,Long> {

    ConfigFileEntity findFirstByOrderByVersionDesc();

}
