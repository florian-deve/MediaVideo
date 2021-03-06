package fr.gdvd.media_manager.daoMysql;

import fr.gdvd.media_manager.entitiesMysql.VideoKeyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface VideoKeywordRepository extends JpaRepository<VideoKeyword, Long> {

    Optional<VideoKeyword> findByKeywordEn(String keywordEn);
}
