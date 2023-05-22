package extras;

import java.util.ArrayList;

public class Nodo {
	
	//Atributos
	private EstadoEnum estado;
	private IdNodoEnum id;
	private ArrayList<Nodo> sucesores;
	private Integer costo;
	
	//Constructores
	public Nodo() {
		this.sucesores = new ArrayList<>();
	}
	
	//Getters y Setters
	public EstadoEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadoEnum estado) {
		this.estado = estado;
	}

	public IdNodoEnum getId() {
		return id;
	}

	public void setId(IdNodoEnum id) {
		this.id = id;
	}

	public ArrayList<Nodo> getSucesores() {
		return sucesores;
	}

	public void setSucesores(ArrayList<Nodo> sucesores) {
		this.sucesores = sucesores;
	}

	public Integer getCosto() {
		return costo;
	}

	public void setCosto(Integer costo) {
		this.costo = costo;
	}

	//Metodos
	public boolean hayPuntoRecarga() {
		return this.estado == EstadoEnum.PUNTORECARGA;
	}
	
	public boolean hayEnemigo() {
		return this.estado == EstadoEnum.ENEMIGO;
	}
	
	public boolean estaVacio() {
		return this.estado == EstadoEnum.VACIO;
	}
	
	public void addSucesor(Nodo sucesor) {
		sucesores.add(sucesor);
	}
	
	public void addSucesor(ArrayList<Nodo> sucesores) {
		sucesores.addAll(sucesores);
	}
	
	//Metodos
	@Override
	public String toString() {
		return id + "/" + estado;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj.getClass() == this.getClass()) {
			Nodo nodo = (Nodo) obj;
			if(nodo.getId() == id
					&& nodo.getEstado() == estado) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	
	public void actualizarNodo(Nodo n) {
		this.estado = n.getEstado();
	}
	
	public Nodo clone() {
		Nodo clon = new Nodo();
		clon.setEstado(estado);
		clon.setId(id);
		ArrayList<Nodo> nodosSucesores = (ArrayList<Nodo>) sucesores.clone();
		clon.setSucesores(nodosSucesores);
		return clon;
	}
}
