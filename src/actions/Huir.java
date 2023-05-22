package actions;

import java.util.ArrayList;

import extras.Enemigo;
import extras.EstadoEnum;
import extras.IdNodoEnum;
import extras.Nodo;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import search.EstadoAgente;
import search.EstadoAmbiente;

public class Huir extends SearchAction {

	// Atributos
	private Nodo posicionHuir;
	
	// Constructor
	public Huir(Nodo posHuir) {
		posicionHuir = posHuir;
	}
	
	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
		EstadoAgente estado = (EstadoAgente) s;
		EstadoAgente estadoAgente = estado.clone();
		Nodo posicionActual = estadoAgente.getPosicion();
		Double energiaAgente = estadoAgente.getEnergiaDisponible();
		
		posicionHuir = obtenerPosicion(posicionHuir, estadoAgente.getMapaConocido().getMapa());
		Enemigo enem = obtenerEnemigo(posicionActual, estadoAgente.getEnemigosConocidos());
		
		if(enem != null
				&& posicionActual.getSucesores().contains(posicionHuir)
				&& posicionActual.getId() != IdNodoEnum.FIN
				&& energiaAgente > 0.0) {		
			if(energiaAgente <= enem.getEnergia()) {
				estadoAgente.setPosicion(posicionHuir);
				estadoAgente.aumentarEnergia(-enem.getEnergia()/4);
				return estadoAgente;
			}
		}
		return null;
	}

	@Override
	public Double getCost() {
		return 1.5;
	}

	@Override
	public EnvironmentState execute(AgentState ast, EnvironmentState est) {
		EstadoAgente estadoAgente = (EstadoAgente) ast;
		EstadoAmbiente estadoAmbiente = (EstadoAmbiente) est;
		Nodo posicionActual = estadoAgente.getPosicion();
		Double energiaAgente = estadoAgente.getEnergiaDisponible();
		
		posicionHuir = obtenerPosicion(posicionHuir, estadoAgente.getMapaConocido().getMapa());
		Enemigo enem = obtenerEnemigo(posicionActual, estadoAgente.getEnemigosConocidos());
		
		if(posicionActual.getSucesores().contains(posicionHuir)
				&& energiaAgente > 0.0
				&& enem != null
				&& posicionActual.getId() != IdNodoEnum.FIN) {
			if(energiaAgente <= enem.getEnergia()) {
				estadoAgente.setPosicion(posicionHuir);
				estadoAmbiente.setUbicacionAgente(estadoAgente.getPosicion());
				estadoAgente.aumentarEnergia(-enem.getEnergia()/4);
				estadoAmbiente.setEnergiaAgente(estadoAgente.getEnergiaDisponible());
				return estadoAmbiente;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "Huir a " + posicionHuir.getId();
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
