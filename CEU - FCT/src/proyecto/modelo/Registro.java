package proyecto.modelo;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

public class Registro {

	private Long idRegistro;
	private Long idUsuario;
	private LocalDate fecha;
	private BigDecimal numHoras;
	private String descripcion;
	
	
	public Long getIdRegistro() {
		return idRegistro;
	}
	public void setIdRegistro(Long id_registro) {
		this.idRegistro = id_registro;
	}
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long id_usuario) {
		this.idUsuario = id_usuario;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate localDate) {
		this.fecha = localDate;
	}
	public BigDecimal getNumHoras() {
		return numHoras;
	}
	public void setNumHoras(BigDecimal num_horas) {
		this.numHoras = num_horas;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
