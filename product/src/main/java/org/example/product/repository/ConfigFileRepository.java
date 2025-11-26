package org.example.product.repository;

import org.example.product.entity.ConfigFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ConfigFileRepository extends JpaRepository<ConfigFileEntity,Long> {

    ConfigFileEntity findFirstByOrderByVersionDesc();


    @Query(
            value = "SELECT * FROM config_file_table " +
                    "ORDER BY CAST(SUBSTRING(version, 2) AS UNSIGNED) DESC " +
                    "LIMIT 1",
            nativeQuery = true
    )
    ConfigFileEntity findLatestVersion();
}

