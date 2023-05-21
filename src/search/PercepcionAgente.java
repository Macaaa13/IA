package search;

import java.util.ArrayList;

import extras.*;
import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

public class PercepcionAgente extends Perception {

	//Atributos
	private Mapa mapaPercibido;
	private ArrayList<Enemigo> enemigosPercibidos;
	private ArrayList<PuntoRecarga> puntosRecargaPercibidos;
	private Satelite satelite;
	
	//Constructores
	public PercepcionAgente() {
		this.mapaPercibido = new Mapa(); // Mapa desconocido
		this.enemigosPercibidos = new ArrayList<Enemigo>();
		this.puntosRecargaPercibidos = new ArrayList<PuntoRecarga>();
		this.satelite = new Satelite();
	}
	
	public PercepcionAgente(Agent agent, Environment env) {
		super(agent, env);
		this.mapaPercibido = new Mapa(); // Mapa desconocido
		this.enemigosPercibidos = new ArrayList<Enemigo>();
		this.puntosRecargaPercibidos = new ArrayList<PuntoRecarga>();
		this.satelite = new Satelite();
	}
	
	// Getters y Setters
	public Mapa getMapaPercibido() {
		return mapaPercibido;
	}

	public void setMapaPercibido(Mapa mapaPercibido) {
		this.mapaPercibido = mapaPercibido;
	}

	public ArrayList<Enemigo> getEnemigosPercibidos() {
		return enemigosPercibidos;
	}

	public void setEnemigosPercibidos(ArrayList<Enemigo> enemigosPercibidos) {
		this.enemigosPercibidos = enemigosPercibidos;
	}

	public ArrayList<PuntoRecarga> getPuntosRecargaPercibidos() {
		return puntosRecargaPercibidos;
	}

	public void setPuntosRecargaPercibidos(ArrayList<PuntoRecarga> puntosRecargaPercibidos) {
		this.puntosRecargaPercibidos = puntosRecargaPercibidos;
	}

	public Satelite getSatelite() {
		return satelite;
	}

	public void setSatelite(Satelite satelite) {
		this.satelite = satelite;
	}

	// Metodos
	@Override
	public void initPerception(Agent agent, Environment environment) {
		Ambiente ambiente = (Ambiente) environment;
		EstadoAmbiente estadoAmbiente = ambiente.getEnvironmentState();
		
		// Debe percibirse la posicion actual del agente y sus posiciones adyacentes
		percibirMapa(estadoAmbiente);

		//Actualizo el satelite con el satelite del ambiente
		this.satelite = estadoAmbiente.getSatelite();
	}
	
	public void percibirMapa(EstadoAmbiente estadoAmbiente) {
		Nodo posicionAgente = estadoAmbiente.getUbicacionAgente();
		ArrayList<Nodo> sucesores = posicionAgente.getSucesores();
		
		//Percibo el nodo actual
		percibirNodo(posicionAgente, estadoAmbiente);
		
		//Percibo los nodos sucesores al nodo actual
		for(Nodo nodo: sucesores) {
			percibirNodo(nodo, estadoAmbiente);
		}
	}
	
	public void percibirNodo(Nodo nodo, EstadoAmbiente estadoAmbiente) {
		ArrayList<Enemigo> enemigosAmbiente = estadoAmbiente.getEnemigos();
		ArrayList<PuntoRecarga> puntosRecargaAmbiente = estadoAmbiente.getPuntosRecarga();
		Mapa mapaAmbiente = estadoAmbiente.getMapaAmbiente();
		Nodo posicion = mapaAmbiente.getNodo(nodo.getId()); 
		boolean banderaEnemigo = false; // Verdadera si el enemigo estaba en la lista de enemigos percibidos
		boolean banderaPuntoRecarga = false; // Verdadera si el punto de recarga estaba en la lista de enemigos percibidos
		
		if(posicion.getEstado() == EstadoEnum.ENEMIGO) { // Posicion con enemigo
			// Actualizo el estado del nodo en el mapa percibido
			mapaPercibido.getNodo(posicion.getId()).setEstado(EstadoEnum.ENEMIGO); 
			Enemigo enemigo = obtenerEnemigo(posicion, enemigosAmbiente); // Obtengo el enemigo del ambiente
			for(int i=0; i < enemigosPercibidos.size(); i++) {
				// Verifica si ya estaba dentro de los enemigos percibidos
				if(enemigosPercibidos.get(i).getId() == enemigo.getId()) { 
					enemigosPercibidos.get(i).actualizarEnemigo(enemigo); // Actualizo al enemigo
					i = enemigosPercibidos.size(); // Termino el loop
					banderaEnemigo = true; // Indico que ya fue percibido
				}
			}
			if(!banderaEnemigo) { // Si el enemigo no habia sido percibido, se agrega a la lista de enemigos percibidos
				enemigosPercibidos.add(enemigo);
			}
		}
		else if(posicion.getEstado() == EstadoEnum.PUNTORECARGA) { // Posicion con Punto de recarga
			// Actualizo el estado del nodo en el mapa percibido
			mapaPercibido.getNodo(posicion.getId()).setEstado(EstadoEnum.PUNTORECARGA);
			PuntoRecarga pr = obtenerPuntoRecarga(posicion, puntosRecargaAmbiente); // Obtengo el punto del ambiente
			for(int i=0; i < puntosRecargaPercibidos.size(); i++) { 
				// Verifica si ya estaba dentro de los puntos de recarga percibidos
				if(puntosRecargaPercibidos.get(i).getPosicion().getId() == pr.getPosicion().getId()) {
					puntosRecargaPercibidos.get(i).actualizarPuntoRecarga(pr); // Actualizo al punto
					i = puntosRecargaPercibidos.size(); // Termino el loop
					banderaPuntoRecarga = true; // Indico que ya fue percibido
				}
			}
			if(!banderaPuntoRecarga) { // Si el punto no habia sido percibido, se agrega a la lista de puntos percibidos
				puntosRecargaPercibidos.add(pr);
			}
		}
		else if(posicion.getEstado() == EstadoEnum.VACIO) { // Posicion vacia
			// Actualizo el estado del nodo en el mapa percibido
			mapaPercibido.getNodo(posicion.getId()).setEstado(EstadoEnum.VACIO);
		}
	}
	
	public Enemigo obtenerEnemigo(Nodo nodo, ArrayList<Enemigo> enemigos) {
		for(Enemigo e: enemigos) {
			if(e.getPosicion().getId() == nodo.getId()) {
				return e;
			}
		}
		return null;
	}
	
	public PuntoRecarga obtenerPuntoRecarga(Nodo nodo, ArrayList<PuntoRecarga> puntos) {
		for(PuntoRecarga pr: puntos) {
			if(pr.getPosicion().getId() == nodo.getId()) {
				return pr;
			}
		}
		return null;
	}
	
	@Override
	public String toString() {
		String mensaje = "\n";
		mensaje += "Enemigos percibidos: [ ";
		for(Enemigo e: enemigosPercibidos) {
			mensaje += e.getId() + ", ";
		}
		mensaje += "]\n";
		mensaje += "Puntos de Recarga percibidos: [";
		for(PuntoRecarga pr: puntosRecargaPercibidos) {
			mensaje += pr.getPosicion().getId() + ", ";
		}
		mensaje += "]\n";
		mensaje += "Mapa percibido: [";
		for(Nodo nodo: mapaPercibido.getMapa()) {
			mensaje += nodo.getId() + "/" + nodo.getEstado() + ", ";
		}
		mensaje += "]\n";
		return mensaje;
	}
}
