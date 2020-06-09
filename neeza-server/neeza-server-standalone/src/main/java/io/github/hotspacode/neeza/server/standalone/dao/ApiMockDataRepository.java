package io.github.hotspacode.neeza.server.standalone.dao;

import io.github.hotspacode.neeza.server.standalone.model.ApiMockData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ApiMockDataRepository extends JpaRepository<ApiMockData, Long> {
    @Transactional
    @Modifying
    @Query("update ApiMockData a set a.api_data=?2 where a.id=?1")
    void updateContentById(Long id, String content);
}
