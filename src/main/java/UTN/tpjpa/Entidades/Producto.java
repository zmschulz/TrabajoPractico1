package UTN.tpjpa.Entidades;

import UTN.tpjpa.EntidadesEnumTypes.TipoProducto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer tiempoestimadoCocina;
    private String denominacion;
    private Double precioventa;
    private Double preciocompra;
    private Integer stockactual;
    private Integer stockminimo;
    private String unidadvendida;
    private String receta;

    @Enumerated(EnumType.STRING)
    @Column(name = "TipoProducto",nullable = false)
    private TipoProducto tipoproducto;


}
