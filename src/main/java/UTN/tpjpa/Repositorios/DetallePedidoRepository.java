package UTN.tpjpa.Repositorios;

import UTN.tpjpa.Entidades.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetallePedidoRepository extends JpaRepository <DetallePedido,Long> {
}
