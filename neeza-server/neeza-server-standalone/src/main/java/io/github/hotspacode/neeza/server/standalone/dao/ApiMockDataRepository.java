package io.github.hotspacode.neeza.server.standalone.dao;

import io.github.hotspacode.neeza.server.standalone.model.ApiMockData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiMockDataRepository extends JpaRepository<ApiMockData, Long> {
    @Modifying
    @Query("update ApiMockData a set a.api_data=?2 where  m.id=?1")
    void updateContentById(Long id, String content);
}
