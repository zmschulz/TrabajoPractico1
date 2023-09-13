package UTN.tpjpa.Entidades;

import UTN.tpjpa.EntidadesEnumTypes.FormadePago;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer numero;
    private Date fecha;
    private Double descuento;
    private Integer total;

    @Enumerated(EnumType.STRING)
    @Column(name = "FormaDePago",nullable = false)
    private FormadePago Formadepago;

}
