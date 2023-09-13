package UTN.tpjpa.Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rubro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String denominacion;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    @JoinColumn(name = "Rubro_id")
    @Builder.Default
    private List<Producto> productos = new ArrayList<>();
    public void agregarProducto(Producto produ){
        productos.add(produ);
    }
    public void mostrarproductos() {
        System.out.println("Denominacion" + denominacion );
        for (Producto producto : productos) {
            System.out.println("Tipo de produccion: " + producto.getTipoproducto() + "Tiempo estimado de Cocina: " + producto.getTiempoestimadoCocina() +
                    "denominacion: " + producto.getDenominacion() +
                    "Precio de Venta: " + producto.getPrecioventa() + "Precio de Compra: " + producto.getPreciocompra() +
                    "Stock Actual: " + producto.getStockactual() + "Stock Minimo: " + producto.getStockminimo() + "Unidad vendida: " + producto.getUnidadvendida() +
                    "Receta: " + producto.getReceta());
        }
    }
}
