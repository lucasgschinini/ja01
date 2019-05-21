package ar.com.telecom.shiva.test.util;

import junit.framework.TestCase;

import org.junit.Test;
import ar.com.telecom.shiva.base.utils.Utilidad;
import org.springframework.ldap.filter.EqualsFilter;


public class UtilidadTest extends TestCase {
	
	@Test
	public void testEqualFilter() {
		EqualsFilter filter = new EqualsFilter("cn", "Hacker, Wiley)(|(objectclass=*)");
		System.out.println(filter.encode());
		 
	}
	@Test
	public void testArchivo() {
		
		String sistema 		= "Negocio.Net";
		String referencia 	= "";
		String codigosOperacionExterno  = "";

		String vArmarRefencia[] = Utilidad.armarReferencias(sistema, referencia, codigosOperacionExterno);

		System.out.println(vArmarRefencia[0]);
		System.out.println(vArmarRefencia[1]);
		System.out.println(vArmarRefencia[2]);
		
		System.out.println("-------------------");
		referencia 	= "codigo 1";
		
		vArmarRefencia = Utilidad.armarReferencias(sistema, referencia, codigosOperacionExterno);

		System.out.println(vArmarRefencia[0]);
		System.out.println(vArmarRefencia[1]);
		System.out.println(vArmarRefencia[2]);
	
		System.out.println("-------------------");
		
		referencia 	= "123456789x12...";
		vArmarRefencia = Utilidad.armarReferencias(sistema, referencia, codigosOperacionExterno);

		System.out.println(vArmarRefencia[0]);
		System.out.println(vArmarRefencia[1]);
		System.out.println(vArmarRefencia[2]);

		System.out.println("-------------------");
		
		referencia 	= "123456789x12...";
		codigosOperacionExterno  = "123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...";
		
		vArmarRefencia = Utilidad.armarReferencias(sistema, referencia, codigosOperacionExterno);
		
		System.out.println(vArmarRefencia[0]);
		System.out.println(vArmarRefencia[1]);
		System.out.println(vArmarRefencia[2]);
		
		
		System.out.println("-------------------");
		referencia 	= "123456789x12...";
		codigosOperacionExterno  = "123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...";
		
		vArmarRefencia = Utilidad.armarReferencias(sistema, referencia, codigosOperacionExterno);
		
		System.out.println(vArmarRefencia[0]);
		System.out.println(vArmarRefencia[1]);
		System.out.println(vArmarRefencia[2]);
		
		System.out.println("-------------------");
		referencia 	= "123456789x12...";
		codigosOperacionExterno  = "1,2,3,4,5,6,7,8,9,x,1,2,3,4,5,6,7,8,9,x,1,2,3,4,5,6,7,8,9,x,1,2,3,4,5,6,7,8,9,x,1,2,3,4,5,6,7,8,9,x,1,2,3,4,5,6,7,8,9,x,1,2,3,4,5,6,7,8,9,x,1,2,3,4,5,6,7,8,9,x,1,2,3,4,5,6,7,8,9,x,1,2,3,4,5,6,7,8,9,x1,2,3,4,5,6,7,8,9,x,1,2,3,4,5,6,7,8,9,x,1,2,3,4,5,6,7,8,9,x,1,2,3,4,5,6,7,8,9,x,1,2,3,4,5,6,7,8,9,x,";
		vArmarRefencia = Utilidad.armarReferencias(sistema, referencia, codigosOperacionExterno);
		
		System.out.println(vArmarRefencia[0]);
		System.out.println(vArmarRefencia[1]);
		System.out.println(vArmarRefencia[2]);
		System.out.println("-------------------");
		referencia 	= "123456789x12...";
		codigosOperacionExterno  = "1,2,3,123456789x12...";
		vArmarRefencia = Utilidad.armarReferencias(sistema, referencia, codigosOperacionExterno);
		
		System.out.println(vArmarRefencia[0]);
		System.out.println(vArmarRefencia[1]);
		System.out.println(vArmarRefencia[2]);
		System.out.println("-------------------");
		System.out.println("-------------------");
		referencia 	= "123456789x12...";
		codigosOperacionExterno  = "1212,2323,234234,46565,123sad,sdf234,sdf345,dfet34,3456,546456,54654645,4564,56,46459,123456789x12...";
		vArmarRefencia = Utilidad.armarReferencias(sistema, referencia, codigosOperacionExterno);
		
		System.out.println(vArmarRefencia[0]);
		System.out.println(vArmarRefencia[1]);
		System.out.println(vArmarRefencia[2]);
		System.out.println("-------------------");
		System.out.println("-------------------");
		referencia 	= "123456789x12...";
		codigosOperacionExterno  = "1212,2323,234234,46565,123sad,sdf234,sdf345,dfet34,3456,546456,54654645,4564,56,4645,123456789x12...";
		vArmarRefencia = Utilidad.armarReferencias(sistema, referencia, codigosOperacionExterno);
		
		System.out.println(vArmarRefencia[0]);
		System.out.println(vArmarRefencia[1]);
		System.out.println(vArmarRefencia[2]);
		System.out.println("-------------------");
		System.out.println("-------------------");
		referencia 	= "123456789x12...";
		codigosOperacionExterno  = "1212,2323,234234,46565,123sad,sdf234,s,sdf345,dfet34,3456,546456,546ss54,123456789x12...";
		vArmarRefencia = Utilidad.armarReferencias(sistema, referencia, codigosOperacionExterno);
		
		System.out.println(vArmarRefencia[0]);
		System.out.println(vArmarRefencia[1]);
		System.out.println(vArmarRefencia[2]);
		System.out.println("-------------------");
		System.out.println("-------------------");
		referencia 	= "123456789x12...";
		codigosOperacionExterno  = "1212,2323,234234,46565,123sad,sdf234,s,sdf345,dfet34,3456,546456,54sxs654,123456789x12...";
		vArmarRefencia = Utilidad.armarReferencias(sistema, referencia, codigosOperacionExterno);
		
		System.out.println(vArmarRefencia[0]);
		System.out.println(vArmarRefencia[1]);
		System.out.println(vArmarRefencia[2]);
		System.out.println("-------------------");
		
		
		
		
		//String codigosOperacionExterno  = "1,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...,123456789x12...";
				//String codigosOperacionExterno  = "";
				//
				//String codigosOperacionExterno  = "1,2,3,4,5,6,7,8,9,x,1,2,3,4,5,6,7,8,9,x,1,2,3,4,5,6,7,8,9,x,1,2,3,4,5,6,7,8,9,x,1,2,3,4,5,6,7,8,9,x,1,2,3,4,5,6,7,8,9,x,1,2,3,4,5,6,7,8,9,x,1,2,3,4,5,6,7,8,9,x,1,2,3,4,5,6,7,8,9,x,1,2,3,4,5,6,7,8,9,x1,2,3,4,5,6,7,8,9,x,1,2,3,4,5,6,7,8,9,x,1,2,3,4,5,6,7,8,9,x,1,2,3,4,5,6,7,8,9,x,1,2,3,4,5,6,7,8,9,x,";
			
	}

	
//	@Test
//	public void testArchivo() {
//		System.out.println(Utilidad.truncarCadena("123456789x1234567890", 12, "..."));
//	}
//	@Test
//	public void testGenerarDatosModificados() throws NegocioExcepcion{
//		try{
//			BoletaSinValorDto viejo = new BoletaSinValorDto();
//			BoletaSinValorDto nuevo = new BoletaSinValorDto();
//			List<String> lista = new ArrayList<String>();
//			List<String> lista2 = new ArrayList<String>();
//			lista.add("acuerdo");
//			lista.add("bancoDeposito");
//			lista2.add("empresa");
//			lista2.add("acuerdo");
//			viejo.setEmpresa("empresa");
//			viejo.setSegmento("segmento");
//			viejo.setAcuerdo("Acuerdo 33");
//			nuevo.setAcuerdo("Acuerdo 125");
//			viejo.setBancoDeposito("Citi");
//			nuevo.setBancoDeposito("Frances");
//			viejo.setNombreAnalista("Jose");
//			nuevo.setNombreAnalista("Maria");
//			System.out.println("empieza");
//			System.out.println("originales: "+Utilidad.datosOriginales(viejo,lista2));
//			String resultado = Utilidad.generarDatosModificados(viejo, nuevo, lista);
//			System.out.println(resultado);
//			System.out.println("ahora muestra");
//		
//			String resultadoMostrar = Utilidad.mostrarDatosModificados(resultado);
//			System.out.println(resultadoMostrar);
//			System.out.println("termino");
//		} catch (ShivaExcepcion e) {
//			throw new NegocioExcepcion(e.getMessage(),e);
//		} 
//	}
//	
//	@SuppressWarnings("unchecked")
//	@Test
//	public void testGuionesNull () throws NegocioExcepcion, ShivaExcepcion{
//		
//		List<TalonarioDto> listaDto = new ArrayList<TalonarioDto>();
//		TalonarioDto dto = new TalonarioDto();
//		dto.setRangoDesde("1");
//		dto.setRangoHasta("2");
//		dto.setEmpresa("TA");
//		listaDto.add(dto);
//		listaDto= (List<TalonarioDto>) Utilidad.guionesNull(listaDto);
//		System.out.println(listaDto.get(0).getEmpresa());
//		System.out.println(listaDto.get(0).getRangoDesde());
//		System.out.println(listaDto.get(0).getSegmento());
//		System.out.println(listaDto.get(0).getFechaAlta());
//	}
//	public List<String> getCamposOperacion(){
//		List<String> lista = new ArrayList<String>();
//		lista.add("idCaja");
//		lista.add("tipoOperacion");
//		return lista;
//	}
	
//	@Test
//	public void testGetCampoYDatos(){
//		try {
//			NotaCreditoCalipso nota = new NotaCreditoCalipso();
//			IdDocumento documento = new IdDocumento();
//			documento.setClaseComprobante("a");
//			documento.setTipoComprobante("tipo");
//			documento.setSucursalComprobante("suc");
//			documento.setNumeroComprobante("numero");
//			nota.setFechaEmision(123L);
//			nota.setIdClienteLegado(5252L);
//			nota.setImporte(new BigDecimal(15000));
//			nota.setIdDocumento(documento);
//			List<String> lista = new ArrayList<String>();
//			lista.add("fechaEmision");
//			lista.add("importe");
//			lista.add("claseComprobante");
//			lista.add("numeroComprobante");
//			lista.add("tipoComprobante");
//			String cadena = Utilidad.getCamposYDatos(nota, lista);
//			System.out.println(cadena);
//		} catch (NegocioExcepcion e) {
//		}
//	}
	
//	@Test
//	public void testCuit(){
//		String cadena = "esto no es un cuit 798846 y esto si 20346533863 y esto otro tampoco 45464633";
//		String cadenaFallo = "esto no es un cuit 798846 y esto si 23346533863, pero erroneo y este es posta 20346533863";
//		String cadenaFallo2 = "esto no es un cuit 798846 y esto si 27346533863 pero erroneo";
//		System.out.println(Utilidad.buscarCuit(cadena.toCharArray()));
//		System.out.println(Utilidad.buscarCuit(cadenaFallo.toCharArray()));
//		System.out.println(Utilidad.buscarCuit(cadenaFallo2.toCharArray()));
//		
//	}
//	
//	@Test
//	public void testNroLegalSap(){
//		NroDocumentoLegal nrodocleg = new NroDocumentoLegal();
//		nrodocleg = Utilidad.obtenerNroDocumentoLegalSap("0022 12345678");
//		System.out.println(nrodocleg.getNumero() + " " + nrodocleg.getSucursal() + " " + nrodocleg.getClase());
//		System.out.println(Utilidad.parcearNroDocumentoLegalSap(nrodocleg));
//		System.out.println(Utilidad.parcearNroDocumentoLegalShiva(Utilidad.parcearNroDocumentoLegalSap(nrodocleg)));
//	}

//	@Test
//	public void testFormatosFechas(){
//		System.out.println("Formato AAAAMMDD:" + Utilidad.formatDateAAAAMMDD(new Date()));
//		System.out.println("Formato HHMMSSTH:" + Utilidad.formatDateHHMMSSTH(new Date()));
//		DateFormat dateFormat = new SimpleDateFormat("HHmmssS");
//		//get current date time with Date()
//		Date date = new Date();
//		System.out.println(dateFormat.format(date));
//	 	//get current date time with Calendar()
//		Calendar cal = Calendar.getInstance();
//		System.out.println(dateFormat.format(cal.getTime()));
//	}

//	@Test
//	public void testJava2Xml(){
//		Cabecera cabecera = new Cabecera();
//		Detalle detalle = new Detalle();
//		
//		cabecera.setCliente("cliente");
//		cabecera.setComprobante("comprobante");
//		cabecera.setConcepto("concepto");
//		cabecera.setCuit("cuit");
//		cabecera.setDescripcion("descripcion");
//		cabecera.setDomicilio("domicilio");
//		cabecera.setFecha("fecha");
//		cabecera.setHora("hora");
//		cabecera.setIdOperacion("idOperacion");
//		cabecera.setRazonSocial("razonSocial");
//		cabecera.setResponsableIVA("responsableIVA");
//		cabecera.setTipo("tipo");
//		cabecera.setTotal("1");
//		
//		ChequeRechazado chequeRechazados = new ChequeRechazado();
//		Cheque cheque = new Cheque();
//		cheque.setDescripcion("descripcion");
//		cheque.setImporte("1");
//		cheque.setReferencia1("referencia1");
//		cheque.setReferencia2("referencia2");
//		cheque.setReferencia3("referencia3");
//		
//		Cheque cheque2 = new Cheque();
//		cheque2.setDescripcion("descripcion");
//		cheque2.setImporte("1");
//		cheque2.setReferencia1("referencia1");
//		cheque2.setReferencia2("referencia2");
//		cheque2.setReferencia3("referencia3");
//		
//		List<Cheque> listaCheques = new ArrayList<Cheque>();
//		
//		Factura factura = new Factura();
//		factura.setAcuerdo("1");
//		factura.setClase("clase");
//		factura.setComprobante("comprobante");
//		factura.setConsecuenciaCobro("consecuenciaCobro");
//		factura.setImporte("1");
//		factura.setImporteVencimiento("1");
//		factura.setIntereses("1");
//		factura.setInteresesBonificados("1");
//		factura.setMoneda("moneda");
//		factura.setNumero("numero");
//		factura.setOperacion("operacion");
//		factura.setOrigen("origen");
//		factura.setPago("pago");
//		factura.setReferencia("referencia");
//		factura.setSaldo("1");
//		factura.setSucursal("sucursal");
////		factura.setTipoCambio("tipoCambio");
//		factura.setVencimiento("vencimiento");
//		
//		Factura factura2 = new Factura();
//		factura2.setAcuerdo("1");
//		factura2.setClase("clase");
//		factura2.setComprobante("comprobante");
//		factura2.setConsecuenciaCobro("consecuenciaCobro");
//		factura2.setImporte("1");
//		factura2.setImporteVencimiento("1");
//		factura2.setIntereses("1");
//		factura2.setInteresesBonificados("1");
//		factura2.setMoneda("moneda");
//		factura2.setNumero("numero");
//		factura2.setOperacion("operacion");
//		factura2.setOrigen("origen");
//		factura2.setPago("pago");
//		factura2.setReferencia("referencia");
//		factura2.setSaldo("1");
//		factura2.setSucursal("sucursal");
//		factura2.setTipoCambio("tipoCambio");
//		factura2.setVencimiento("vencimiento");
//
//		List<Factura> listaFactura = new ArrayList<Factura>();
//		
//		Medio medio = new Medio();
//		medio.setDescripcion("descripcion");
//		medio.setImporte("1");
//		medio.setReferencia1("referencia1");
//		medio.setReferencia2("referencia2");
//		medio.setReferencia3("referencia3");
//		Medio medio2 = new Medio();
//		medio2.setDescripcion("descripcion");
//		medio2.setImporte("1");
//		medio2.setReferencia1("referencia1");
//		medio2.setReferencia2("referencia2");
//		medio2.setReferencia3("referencia3");
//		
//		List<Medio> listaMedio = new ArrayList<Medio>();
//		
//		listaMedio.add(medio);
//		listaMedio.add(medio2);
//		listaFactura.add(factura);
//		listaFactura.add(factura2);
//		listaCheques.add(cheque);
//		listaCheques.add(cheque2);
//		
//		Facturas facturas = new Facturas();
//		chequeRechazados.setCheque(listaCheques);
//		facturas.setCantidadFacturas("2");
//		facturas.setFactura(listaFactura);
//		facturas.setImporteTotal("1");
//		
//		detalle.setChequesRechazados(chequeRechazados);
//		detalle.setFacturas(facturas);
//		detalle.setMediosDePago(listaMedio);
//		
//		Documentos docs = new Documentos();
//		docs.setDocumento(new Documento());
//		docs.getDocumento().setCabecera(cabecera);
//		docs.getDocumento().setDetalle(detalle);
		
//		try {
//			ControlXml.marshal(Documentos.class, docs, "C:\\Temp\\Java2Xml\\", "testJava2Xml.xml");
//		} catch (ShivaExcepcion e) {
//			e.printStackTrace();
//		}
//	}
}
