package actions;

import java.util.ArrayList;

import extras.*;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import search.*;

public class Move extends SearchAction {

	private Nodo posicionSiguiente;
	
	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
		EstadoAgente estadoAgente = (EstadoAgente) s;
		Nodo posicionAgente = estadoAgente.getPosicion();
		ArrayList<Nodo> sucesores = posicionAgente.getSucesores();
		Double energiaAgente = estadoAgente.getEnergiaDisponible();
		
		ArrayList<Enemigo> enemigos = new ArrayList<Enemigo>();
		ArrayList<PuntoRecarga> puntosRecarga = new ArrayList<PuntoRecarga>();
		ArrayList<Nodo> vacios = new ArrayList<Nodo>();
		
		if(posicionAgente.getEstado() == EstadoEnum.PUNTORECARGA) {
			PuntoRecarga pr = obtenerPuntoRecarga(posicionAgente, estadoAgente.getPuntosRecargaConocidos());
			if(pr.getTurnosSinUsar() == 0) {
				// Estoy en un punto de recarga, pero ya lo use, asi que puedo avanzar
				for(Nodo nodo: sucesores) {
					if(nodo.hayPuntoRecarga()) {
						puntosRecarga.add(obtenerPuntoRecarga(nodo, estadoAgente.getPuntosRecargaConocidos()));
					}
					else if(nodo.estaVacio()) {
						vacios.add(nodo);
					}
					else if(nodo.hayEnemigo()) {
						enemigos.add(obtenerEnemigo(nodo, estadoAgente.getEnemigosConocidos()));
					}
				}
				if(puntosRecarga.size() > 0 && energiaAgente<=50) {
					posicionSiguiente = puntosRecarga.get(0).getPosicion();
					estadoAgente.setPosicion(posicionSiguiente);
				}
				else if(vacios.size() > 0) {
					posicionSiguiente = vacios.get(0);
					estadoAgente.setPosicion(posicionSiguiente);
				}
				else if(enemigos.size() > 0) {
					posicionSiguiente = obtenerEnemigoMasDebil(enemigos).getPosicion();
					estadoAgente.setPosicion(posicionSiguiente);
				}
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
		ArrayList<Nodo> sucesores = posicionAgente.getSucesores();
		Double energiaAgente = estadoAgente.getEnergiaDisponible();
		
		ArrayList<Enemigo> enemigos = new ArrayList<Enemigo>();
		ArrayList<PuntoRecarga> puntosRecarga = new ArrayList<PuntoRecarga>();
		ArrayList<Nodo> vacios = new ArrayList<Nodo>();
		
		if(posicionAgente.getEstado() == EstadoEnum.PUNTORECARGA) {
			PuntoRecarga pr = obtenerPuntoRecarga(posicionAgente, estadoAgente.getPuntosRecargaConocidos());
			if(pr.getTurnosSinUsar() == 0) {
				// Estoy en un punto de recarga, pero ya lo use, asi que puedo avanzar
				for(Nodo nodo: sucesores) {
					if(nodo.hayPuntoRecarga()) {
						puntosRecarga.add(obtenerPuntoRecarga(nodo, estadoAgente.getPuntosRecargaConocidos()));
					}
					else if(nodo.estaVacio()) {
						vacios.add(nodo);
					}
					else if(nodo.hayEnemigo()) {
						enemigos.add(obtenerEnemigo(nodo, estadoAgente.getEnemigosConocidos()));
					}
				}
				if(puntosRecarga.size() > 0 && energiaAgente<=50) {
					posicionSiguiente = puntosRecarga.get(0).getPosicion();
					estadoAgente.setPosicion(posicionSiguiente);
					estadoAmbiente.setUbicacionAgente(estadoAgente.getPosicion());
				}
				else if(vacios.size() > 0) {
					posicionSiguiente = vacios.get(0);
					estadoAgente.setPosicion(posicionSiguiente);
					estadoAmbiente.setUbicacionAgente(estadoAgente.getPosicion());
				}
				else if(enemigos.size() > 0) {
					posicionSiguiente = obtenerEnemigoMasDebil(enemigos).getPosicion();
					estadoAgente.setPosicion(posicionSiguiente);
					estadoAmbiente.setUbicacionAgente(estadoAgente.getPosicion());
				}
				estadoAgente.setPosicion(posicionSiguiente);
				return estadoAmbiente;
			}
		} else {
			
		}
		
		return null;
	}

	@Override
	public String toString() {
		return "Moverse a " + posicionSiguiente;
	}
	
	public PuntoRecarga obtenerPuntoRecarga(Nodo nodo, ArrayList<PuntoRecarga> puntos) {
		for(PuntoRecarga pr: puntos) {
			if(pr.getPosicion().getId() == nodo.getId()) {
				return pr;
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
	
	public Enemigo obtenerEnemigoMasDebil(ArrayList<Enemigo> enemigos) {
		Enemigo enemigo = null;
		Double energia = 100.0;
		for(Enemigo e: enemigos) {
			if(e.getEnergia() < energia ) {
				energia = e.getEnergia();
				enemigo = e;
			}
		}
		return enemigo;
	}

}
