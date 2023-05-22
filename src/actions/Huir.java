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
		
		Enemigo enem = obtenerEnemigo(posicionActual, estadoAgente.getEnemigosConocidos());
		if(esSucesor(posicionHuir, posicionActual.getSucesores())
				&& energiaAgente > 0.0
				&& enem != null
				&& posicionActual.getId() != IdNodoEnum.FIN) {		
			if(energiaAgente <= enem.getEnergia()) {
				posicionHuir = obtenerPosicion(posicionHuir, estadoAgente.getMapaConocido().getMapa());
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
		
		Enemigo enem = obtenerEnemigo(posicionActual, estadoAgente.getEnemigosConocidos());
		if(esSucesor(posicionHuir, posicionActual.getSucesores())
				&& energiaAgente > 0.0
				&& enem != null
				&& posicionActual.getId() != IdNodoEnum.FIN) {
			if(energiaAgente <= enem.getEnergia()) {
				posicionHuir = obtenerPosicion(posicionHuir, estadoAgente.getMapaConocido().getMapa());
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
	
	public Enemigo obtenerEnemigo(Nodo nodo, ArrayList<Enemigo> enemigos) {
		//System.out.println("Posicion actual: " + nodo);
		//System.out.println("Enemigos conocidos:");
		for(Enemigo e: enemigos) {
			//System.out.println(e);
			if(e.getPosicion().getId() == nodo.getId()) {
				//System.out.println("COINCIDEN");
				return e;
			}
		}
		return null;
	}
	
}
