package extras;

public class Enemigo {

	//Atributos
	private String id;
	private Double energia;
	private Nodo posicion;
	private Integer turnosDetenido;
	private Integer turnosAEsperar;
	
	//Constructores
	public Enemigo() {
		turnosDetenido = 0;
		turnosAEsperar = 3;
	}
	
	public Enemigo(String id, Double energia, Nodo posicion) {
		this.id = id;
		this.energia = energia;
		this.posicion = posicion;
		this.turnosDetenido = 0;
		this.turnosAEsperar = 3;
	}

	//Getters y Setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Double getEnergia() {
		return energia;
	}

	public void setEnergia(Double energia) {
		this.energia = energia;
	}

	public Nodo getPosicion() {
		return posicion;
	}

	public void setPosicion(Nodo posicion) {
		this.posicion = posicion;
	}

	public Integer getTurnosDetenido() {
		return turnosDetenido;
	}

	public void setTurnosDetenido(Integer turnosDetenido) {
		this.turnosDetenido = turnosDetenido;
	}

	public Integer getTurnosAEsperar() {
		return turnosAEsperar;
	}

	public void setTurnosAEsperar(Integer turnosAEsperar) {
		this.turnosAEsperar = turnosAEsperar;
	}
	
	//Metodos
	@Override
	public String toString() {
		return id + "/" + posicion + " - " + energia;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj.getClass() == this.getClass()) {
			Enemigo e = (Enemigo) obj;
			if(e.getId() == id
					&& e.getEnergia()  == energia
					&& e.getPosicion().equals(posicion)
					&& e.getTurnosDetenido() == turnosDetenido
					&& e.getTurnosAEsperar() == turnosAEsperar) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	
	public void aumentarTurnosDetenido() {
		turnosDetenido++;
	}
	
	public void actualizarEnemigo(Enemigo enem) {
		this.id = enem.getId();
		this.energia = enem.getEnergia();
		this.posicion = enem.getPosicion();
		this.turnosAEsperar = enem.getTurnosAEsperar();
		this.turnosDetenido = enem.getTurnosDetenido();
	}
	
	public Enemigo clone() {
		Enemigo enem = new Enemigo();
		enem.setId(id);
		enem.setEnergia(energia);
		enem.setPosicion(posicion.clone());
		enem.setTurnosAEsperar(turnosAEsperar);
		enem.setTurnosDetenido(turnosDetenido);
		return enem;
	}
	
}
