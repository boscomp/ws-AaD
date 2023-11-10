package proyecto.test;


import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

import proyecto.modelo.Registro;


import proyecto.service.FCTException;
import proyecto.service.RegistroService;


public class Test {

	public static void main(String[] args) {
//		Usuario us = new Usuario();
//		UsuarioService user = new UsuarioService(); 
//		
//		
//		us.setEmail("bosco1");
//		us.setContraseña("aaa");
//		us.setNombre("bosco");
//		us.setApellidos("BETIS");
//		us.setCiclo("A");
//		us.setActivo(true);
//		System.out.println(LocalDate.now().getMonthValue());
//		try {
//			user.altaUsuario(us);
//			
//		} catch (FCTException e) {
//			
//			e.printStackTrace();
//		}
		
		Registro r = new Registro();
		RegistroService rs = new RegistroService();
		r.setDescripcion("ey");
		LocalDate localDate = LocalDate.of(2023, 9, 20);
		String fecha = localDate.toString();
		
		System.out.println(localDate);
		System.out.println(fecha);
		
		r.setFecha(localDate);
		r.setIdUsuario((long)5);
		r.setNumHoras(new BigDecimal(1));
		
		try {
			rs.crearRegistro(r);
		} catch (FCTException e) {
			
			e.printStackTrace();
		}
		
	}

}
