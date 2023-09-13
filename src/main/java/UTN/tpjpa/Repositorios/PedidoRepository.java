package UTN.tpjpa.Repositorios;

import UTN.tpjpa.Entidades.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository <Pedido,Long> {
}
