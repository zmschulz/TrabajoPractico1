package UTN.tpjpa.Repositorios;

import UTN.tpjpa.Entidades.Rubro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RubroRepository extends JpaRepository <Rubro,Long> {
}
