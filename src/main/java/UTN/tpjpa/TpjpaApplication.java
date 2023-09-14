package UTN.tpjpa;

import UTN.tpjpa.Entidades.*;
import UTN.tpjpa.EntidadesEnumTypes.EstadoPedido;
import UTN.tpjpa.EntidadesEnumTypes.FormadePago;
import UTN.tpjpa.EntidadesEnumTypes.TipoEnvio;
import UTN.tpjpa.EntidadesEnumTypes.TipoProducto;
import UTN.tpjpa.Repositorios.*;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;


@SpringBootApplication
public class TpjpaApplication {
	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	DomicilioRepository domicilioRepository;
	@Autowired
	PedidoRepository pedidoRepository;
	@Autowired
	FacturaRepository facturaRepository;
	@Autowired
	DetallePedidoRepository detallePedidoRepository;
	@Autowired
	ProductoRepository productoRepository;
	@Autowired
	RubroRepository rubroRepository;

	public static void main(String[] args) {
		SpringApplication.run(TpjpaApplication.class, args);
		System.out.println("Funca");
	}

	@Bean
	CommandLineRunner initclidom(ClienteRepository clienteRepo, DomicilioRepository domicilioRepo, DetallePedidoRepository detallePedidoRepo, FacturaRepository facturaRepo, PedidoRepository pedidoRepo, ProductoRepository productoRepo, RubroRepository rubroRepo) {
		return args -> {
			System.out.println("Funcando");

			// Se crean las instancias de cliente y domicilios

			Domicillio domicillio1 = Domicillio.builder()
					.calle("Calle 1")
					.numero("123")
					.localidad("Localidad 1")
					.build();

			Domicillio domicillio2 = Domicillio.builder()
					.calle("Calle 2")
					.numero("456")
					.localidad("Localidad 2")
					.build();

			Cliente cliente = Cliente.builder()
					.nombre("Juan")
					.apellido("Perez")
					.telefono("1234567")
					.email("Juan@gmail")
					.build();

			LocalDate localDate1 = LocalDate.of(2023, Month.FEBRUARY, 02);
			Date date1 = Date.from(localDate1.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
			Pedido pedido1 = Pedido.builder()
					.estadoPedido(EstadoPedido.Iniciado)
					.fecha(date1)
					.TipoEnvio(TipoEnvio.Delivery)
					.total(4500.0)
					.build();

			Date date2 = Date.from(localDate1.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
			Pedido pedido2 = Pedido.builder()
					.estadoPedido(EstadoPedido.Preparacion)
					.fecha(date2)
					.TipoEnvio(TipoEnvio.Retira)
					.total(3000.0)
					.build();

			DetallePedido detallePedido1 = DetallePedido.builder()
					.cantidad(2)
					.subtotal(4000.0)
					.build();

			DetallePedido detallePedido2 = DetallePedido.builder()
					.cantidad(4)
					.subtotal(6000.0)
					.build();

			Factura factura = Factura.builder()
					.numero(4)
					.fecha(date1)
					.descuento(10.0)
					.Formadepago(FormadePago.Efectivo)
					.total(3500)
					.build();

			Producto producto = Producto.builder()
					.tipoproducto(TipoProducto.Insumo)
					.tiempoestimadoCocina(20)
					.denominacion("Hamburguesa")
					.precioventa(1000.0)
					.preciocompra(950.0)
					.stockactual(70)
					.stockminimo(2)
					.unidadvendida("Hamburguesa")
					.receta("Receta Hamburguesa")
					.build();


			Rubro rubro = Rubro.builder()
					.denominacion("Comida rapida")
					.build();


			cliente.agregarDomicilio(domicillio1);
			cliente.agregarDomicilio(domicillio2);
			cliente.agregarPedido(pedido1);
			cliente.agregarPedido(pedido2);
			pedido1.agregarDetallePedido(detallePedido1);
			pedido2.agregarDetallePedido(detallePedido2);
			pedido1.setFactura(factura);
			detallePedido1.agregarProducto(producto);
			detallePedido2.agregarProducto(producto);
			rubro.agregarProducto(producto);


			clienteRepository.save(cliente);
			pedidoRepository.save(pedido1);
			pedidoRepository.save(pedido2);
			detallePedidoRepository.save(detallePedido1);
			rubroRepository.save(rubro);

			// Se recupera el objeto cliente desde la base de datos

			Cliente clienteRecuperado = clienteRepository.findById(cliente.getId()).orElse(null);

			if (clienteRecuperado != null) {
				System.out.println("Nombre " + clienteRecuperado.getNombre());
				System.out.println("Apellido " + clienteRecuperado.getApellido());
				System.out.println("Telefono " + clienteRecuperado.getTelefono());
				System.out.println("email " + clienteRecuperado.getEmail());
			}
		};
	}
}