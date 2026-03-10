package adapteurs.mysql;

import adapteurs.entity.QuoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface QuoteRepository extends JpaRepository<QuoteEntity, UUID> {
}
