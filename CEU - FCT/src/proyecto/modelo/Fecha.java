package proyecto.modelo;


import java.time.LocalDate;

public class Fecha {

	private LocalDate fecha;
	private Integer anho;
	private Integer evaluación;
	private Boolean disponible;
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public Integer getanho() {
		return anho;
	}
	public void setanho(Integer anho) {
		this.anho = anho;
	}
	public Integer getEvaluación() {
		return evaluación;
	}
	public void setEvaluación(Integer evaluación) {
		this.evaluación = evaluación;
	}
	public Boolean getdisponible() {
		return disponible;
	}
	public void setdisponible(Boolean disponible) {
		this.disponible = disponible;
	}
	
	
	

}