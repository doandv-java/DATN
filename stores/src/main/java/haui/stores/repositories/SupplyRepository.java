package haui.stores.repositories;

import haui.stores.data.Supply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplyRepository extends JpaRepository<Supply, Long> {
    List<Supply> findSuppliesByDeleteStatusIs(int delete);

    Supply findSupplyByNameAndDeleteStatus(String name, int delete);

    Supply findSupplyByIdIs(Long id);
}
