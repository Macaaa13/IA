package extras;

import java.util.ArrayList;

public class Nodo {
	
	//Atributos
	private EstadoEnum estado;
	private IdNodoEnum id;
	private ArrayList<Nodo> sucesores;
	
	//Constructores
	public Nodo() {
		this.sucesores = new ArrayList<>();
	}
	
	public Nodo(Nodo nodo) {
		this.estado = nodo.getEstado();
		this.id = nodo.getId();
		this.sucesores = (ArrayList<Nodo>) nodo.getSucesores().clone();
	}
	
	//Getters y Setters
	public Nodo(EstadoEnum e, IdNodoEnum i) {
		this.estado = e;
		this.id = i;
		this.sucesores = new ArrayList<>();
	}

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
		Nodo clon = new Nodo(this);
		return clon;
	}
}
