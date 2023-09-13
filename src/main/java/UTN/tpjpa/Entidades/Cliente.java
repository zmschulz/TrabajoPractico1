package UTN.tpjpa.Entidades;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id")
    @Builder.Default
    private List<Domicillio> domicillios = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id")
    @Builder.Default
    private List<Pedido> pedidos = new ArrayList<>();

    public void agregarDomicilio(Domicillio domi){
domicillios.add(domi);
    }
    public void agregarPedido (Pedido pedi) {
        pedidos.add(pedi);
    }
    public void mostrarDomicilios() {
        System.out.println("Domicilios de" + nombre + " " + apellido + ":" );
        for (Domicillio domicillio : domicillios) {
            System.out.println("Calle: " + domicillio.getCalle() + "Numero: " + domicillio.getNumero());
        }
    }
    public void mostrarPedidos() {
        System.out.println("Pedidos de" + nombre + " " + apellido + ":" );
        for (Pedido pedido : pedidos) {
            System.out.println("Estado del pedido: " + pedido.getEstadoPedido() + "Fecha: " + pedido.getFecha() + "Tipo de Envio: " +pedido.getTipoEnvio()
            + "Total: " + pedido.getTotal());
        }
    }
}




