package actions;

import java.util.ArrayList;

import extras.EstadoEnum;

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
		
		if(esSucesor(posicionSiguiente, posicionAgente.getSucesores())
				&& !posicionAgente.hayEnemigo()) {
			
			estadoAgente.setPosicion(posicionSiguiente);
			return estadoAgente;
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
		
		if(esSucesor(posicionSiguiente, posicionAgente.getSucesores())
				) {
			
			/** Llega bien ANTES de usar el satelite
			System.out.println("Mapa");
			for(Nodo nodo: estadoAgente.getMapaConocido().getMapa()) {
				System.out.println(nodo);
			}
			*/
			
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

	public boolean esSucesor(Nodo pos, ArrayList<Nodo> sucesores) {
		for(Nodo nodo: sucesores) {
			if(pos.getId() == nodo.getId()) {
				return true;
			}
		}
		return false;
	}
	
	public Nodo obtenerPosicion(Nodo nodo, ArrayList<Nodo> mapaConocido) {
		for(Nodo n: mapaConocido) {
			if(nodo.getId() == n.getId()) {
				return n;
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
	
	public boolean puntoRecargaUsado(EstadoAgente estado) {
		Nodo posAgente = estado.getPosicion();
		boolean bandera = false;
		if(posAgente.getEstado() == EstadoEnum.PUNTORECARGA) {
			PuntoRecarga pr = obtenerPuntoRecarga(posAgente, estado.getPuntosRecargaConocidos());
			if(pr.getTurnosSinUsar()==0) {
				bandera = true;
			}
		}
		return bandera;
	}
	
}
