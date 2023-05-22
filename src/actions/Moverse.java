package actions;

import java.util.ArrayList;

import extras.Enemigo;
import extras.EstadoEnum;
import extras.IdNodoEnum;
import extras.Nodo;
import extras.PuntoRecarga;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import search.EstadoAgente;
import search.EstadoAmbiente;

public class Moverse extends SearchAction {

	// Atributos
	private Nodo posicionSiguiente;
	
	// Constructor
	public Moverse(Nodo posSig) {
		posicionSiguiente = posSig;
	}
	
	// Metodos
	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
		EstadoAgente estado = (EstadoAgente) s;
		EstadoAgente estadoAgente = estado.clone();
		Nodo posicionAgente = estadoAgente.getPosicion();
		

		posicionSiguiente = obtenerPosicion(posicionSiguiente, estadoAgente.getMapaConocido().getMapa());
		Enemigo e = obtenerEnemigo(posicionAgente, estadoAgente.getEnemigosConocidos());
		
		if(e == null) {
			if(posicionAgente.getSucesores().contains(posicionSiguiente)) {
				estadoAgente.setPosicion(posicionSiguiente);
				return estadoAgente;
			}
		} 
		
		return null;
	}

	@Override
	public Double getCost() {
		return 1.0;
	}

	@Override
	public EnvironmentState execute(AgentState ast, EnvironmentState est) {
		EstadoAgente estadoAgente = (EstadoAgente) ast;
		EstadoAmbiente estadoAmbiente = (EstadoAmbiente) est;
		Nodo posicionAgente = estadoAgente.getPosicion();
		Double energiaDisponible = estadoAgente.getEnergiaDisponible();
		
		posicionSiguiente = obtenerPosicion(posicionSiguiente, estadoAmbiente.getMapaAmbiente().getMapa());
		
		if(posicionAgente.getSucesores().contains(posicionSiguiente)) {
			estadoAgente.setPosicion(posicionSiguiente);
			estadoAmbiente.setUbicacionAgente(estadoAgente.getPosicion());
			estadoAmbiente.setEnergiaAgente(energiaDisponible);
			return estadoAmbiente;
		}
		
		return null;
	}

	@Override
	public String toString() {
		return "Moverse a " + posicionSiguiente.getId();
	}
	
	public Nodo obtenerPosicion(Nodo nodo, ArrayList<Nodo> mapaConocido) {
		for(Nodo n: mapaConocido) {
			if(nodo.getId() == n.getId()) {
				return n;
			}
		}
		return null;
	}
	
	public Enemigo obtenerEnemigo(Nodo nodo, ArrayList<Enemigo> enemigos) {
		for(Enemigo e: enemigos) {
			if(e.getPosicion().getId() == nodo.getId()) {
				return e;
			}
		}
		return null;
	}
	
}
