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
	CommandLineRunner initclidom(ClienteRepository clienteRepo, DomicilioRepository domicilioRepo) {
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


			//Se le añaden los domicilios al Cliente
			cliente.agregarDomicilio(domicillio1);
			cliente.agregarDomicilio(domicillio2);

			// Se guarda el objeto cliente en la base de datos

			clienteRepository.save(cliente);
			
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
	@Bean
	CommandLineRunner initcliped(ClienteRepository clienteRepo, PedidoRepository pedidoRepo) {
		return args -> {

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

			Factura factura = Factura.builder()
					.numero(4)
					.fecha(date1)
					.descuento(10.0)
					.Formadepago(FormadePago.Efectivo)
					.total(3500)
					.build();

			cliente.agregarPedido(pedido1);
			cliente.agregarPedido(pedido2);

			clienteRepository.save(cliente);

			Cliente clienteRecuperado = clienteRepository.findById(cliente.getId()).orElse(null);

			if (clienteRecuperado != null) {
				System.out.println("Nombre " + clienteRecuperado.getNombre());
				System.out.println("Apellido " + clienteRecuperado.getApellido());
				System.out.println("Telefono " + clienteRecuperado.getTelefono());
				System.out.println("email " + clienteRecuperado.getEmail());
			}
		};
	}

	@Bean
	CommandLineRunner initpeddetalleped(PedidoRepository pedidoRepo,DetallePedidoRepository detallePedidoRepo) {
		return args -> {

			LocalDate localDate1 = LocalDate.of(2023, Month.FEBRUARY, 02);
			Date date1 = Date.from(localDate1.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
			Pedido pedido = Pedido.builder()
					.estadoPedido(EstadoPedido.Iniciado)
					.fecha(date1)
					.TipoEnvio(TipoEnvio.Delivery)
					.total(4500.0)
					.build();

			DetallePedido detallePedido1 = DetallePedido.builder()
					.cantidad(2)
					.subtotal(4000.0)
					.build();

			DetallePedido detallePedido2 = DetallePedido.builder()
					.cantidad(4)
					.subtotal(6000.0)
					.build();

			pedido.agregarDetallePedido(detallePedido1);
			pedido.agregarDetallePedido(detallePedido2);

			pedidoRepository.save(pedido);

			Pedido pedidoRecuperado = pedidoRepository.findById(pedido.getId()).orElse(null);

			if (pedidoRecuperado != null) {
				System.out.println("Estado Pedido " + pedidoRecuperado.getEstadoPedido());
				System.out.println("Fecha " + pedidoRecuperado.getFecha());
				System.out.println("Tipo de Envio " + pedidoRecuperado.getTipoEnvio());
				System.out.println("Total " + pedidoRecuperado.getTotal());
			}
		};
	}
	@Bean
	CommandLineRunner initpedfac(PedidoRepository pedidoRepo,FacturaRepository facturaRepo) {
		return args -> {

			LocalDate localDate1 = LocalDate.of(2023, Month.FEBRUARY, 02);
			Date date1 = Date.from(localDate1.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
			Pedido pedido = Pedido.builder()
					.estadoPedido(EstadoPedido.Iniciado)
					.fecha(date1)
					.TipoEnvio(TipoEnvio.Delivery)
					.total(4500.0)
					.build();

			Factura factura = Factura.builder()
					.numero(4)
					.fecha(date1)
					.descuento(10.0)
					.Formadepago(FormadePago.Efectivo)
					.total(3500)
					.build();

			pedido.setFactura(factura);

			pedidoRepository.save(pedido);

			Pedido pedidoRecuperado = pedidoRepository.findById(pedido.getId()).orElse(null);

			if (pedidoRecuperado != null) {
				System.out.println("Estado Pedido " + pedidoRecuperado.getEstadoPedido());
				System.out.println("Fecha " + pedidoRecuperado.getFecha());
				System.out.println("Tipo de Envio " + pedidoRecuperado.getTipoEnvio());
				System.out.println("Total " + pedidoRecuperado.getTotal());
			}
		};
	}
	@Bean
	CommandLineRunner initdetalleprod(DetallePedidoRepository detallePedidoRepo,ProductoRepository productoRepo) {
		return args -> {

			DetallePedido detallePedido1 = DetallePedido.builder()
					.cantidad(2)
					.subtotal(4000.0)
					.build();

			DetallePedido detallePedido2 = DetallePedido.builder()
					.cantidad(4)
					.subtotal(6000.0)
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
			detallePedido1.agregarProducto(producto);
			detallePedido2.agregarProducto(producto);


			detallePedidoRepository.save(detallePedido1);

			DetallePedido detallepedidoRecuperado = detallePedidoRepository.findById(detallePedido1.getId()).orElse(null);

			if (detallepedidoRecuperado != null) {
				System.out.println("Cantidad " + detallepedidoRecuperado.getCantidad());
				System.out.println("Subtotal " + detallepedidoRecuperado.getSubtotal());

			}
		};
	}
	@Bean
	CommandLineRunner initRubrosprod(RubroRepository rubroRepo,ProductoRepository productoRepo) {
		return args -> {

			Producto producto1 = Producto.builder()
					.tipoproducto(TipoProducto.Insumo)
					.tiempoestimadoCocina(2)
					.denominacion("Papas fritas")
					.precioventa(1000.0)
					.preciocompra(950.0)
					.stockactual(70)
					.stockminimo(2)
					.unidadvendida("Hamburguesa")
					.receta("Receta Hamburguesa")
					.build();

			Producto producto2 = Producto.builder()
					.tipoproducto(TipoProducto.Insumo)
					.tiempoestimadoCocina(2)
					.denominacion("Lomito")
					.precioventa(1200.0)
					.preciocompra(1100.0)
					.stockactual(50)
					.stockminimo(1)
					.unidadvendida("Lomito")
					.receta("Receta Lomito")
					.build();


			Rubro rubro = Rubro.builder()
					.denominacion("Comida rapida")
					.build();

			rubro.agregarProducto(producto1);
			rubro.agregarProducto(producto2);

			rubroRepository.save(rubro);

			Rubro rubroRecuperado = rubroRepository.findById(rubro.getId()).orElse(null);

			if (rubroRecuperado != null) {
				System.out.println("Nombre: " + rubroRecuperado.getDenominacion());
				rubroRecuperado.mostrarproductos();

			}
		};
	}
}


