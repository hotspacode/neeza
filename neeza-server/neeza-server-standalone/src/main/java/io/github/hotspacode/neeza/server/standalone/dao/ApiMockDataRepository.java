package io.github.hotspacode.neeza.server.standalone.dao;

import io.github.hotspacode.neeza.server.standalone.model.ApiMockData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiMockDataRepository extends JpaRepository<ApiMockData, Long> {

}
