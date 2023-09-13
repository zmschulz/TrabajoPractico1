package UTN.tpjpa.Entidades;

import UTN.tpjpa.EntidadesEnumTypes.EstadoPedido;
import UTN.tpjpa.EntidadesEnumTypes.TipoEnvio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date fecha;
    private Double total;

    @Enumerated(EnumType.STRING)
    @Column(name = "EstadoPedido",nullable = false)
    private EstadoPedido estadoPedido;

    @Enumerated(EnumType.STRING)
    @Column(name = "TipoEnvio",nullable = false)
    private TipoEnvio TipoEnvio;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "factura_id")
    private Factura factura;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    @JoinColumn(name = "pedido_id")
    @Builder.Default
    private List<DetallePedido>  detallePedidos = new ArrayList<>();

    public void agregarDetallePedido(DetallePedido DetaPedi){
        detallePedidos.add(DetaPedi);
    }
    public void mostrarDetallesPedidos() {
        System.out.println("Dia de la factura " + fecha + "Estado del pedido " + estadoPedido + "Tipo del envio: " + TipoEnvio + "Con un coste total de " + total );
        for (DetallePedido detallePedido : detallePedidos)
            System.out.println("Cantidad: " + detallePedido.getCantidad() + "Subtotal: " + detallePedido.getSubtotal());
    }

}
