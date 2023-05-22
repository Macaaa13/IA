package extras;

public class PuntoRecarga {

	//Atributos
	private Nodo posicion;
	private Integer turnosSinUsar;
	
	//Constructores
	public PuntoRecarga() {
		turnosSinUsar = 1;
	}
	
	public PuntoRecarga(Nodo posicion) {
		super();
		this.posicion = posicion;
		turnosSinUsar = 1;
	}

	//Getters y Setters
	public Nodo getPosicion() {
		return posicion;
	}

	public void setPosicion(Nodo posicion) {
		this.posicion = posicion;
	}

	public Integer getTurnosSinUsar() {
		return turnosSinUsar;
	}

	public void setTurnosSinUsar(Integer turnosSinUsar) {
		this.turnosSinUsar = turnosSinUsar;
	}
	
	//Metodos
	public void aumentarTurnosSinUsar() {
		turnosSinUsar++;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj.getClass() == this.getClass()) {
			PuntoRecarga pr = (PuntoRecarga) obj;
			if (pr.getPosicion().equals(posicion) // Misma posicion
					// Mismos turnos sin usar
					&& pr.getTurnosSinUsar() == turnosSinUsar) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	
	public void actualizarPuntoRecarga(PuntoRecarga punto) {
		this.posicion = punto.getPosicion();
		this.turnosSinUsar = punto.getTurnosSinUsar();
	}
	
	public PuntoRecarga clone() {
		PuntoRecarga pr = new PuntoRecarga();
		pr.setPosicion(posicion.clone());
		pr.setTurnosSinUsar(turnosSinUsar);
		return pr;
	}
	
}
