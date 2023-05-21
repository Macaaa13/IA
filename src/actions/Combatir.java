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

public class Combatir extends SearchAction {
	
	// Metodos
	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
		EstadoAgente estado = (EstadoAgente) s;
		EstadoAgente estadoAgente = estado.clone();
		Nodo posicionActual = estadoAgente.getPosicion();
		Double energiaAgente = estadoAgente.getEnergiaDisponible();
		ArrayList<Enemigo> enemigos = estadoAgente.getEnemigosConocidos();
		ArrayList<Nodo> mapaAgente = estadoAgente.getMapaConocido().getMapa();
		
		if(posicionActual.hayEnemigo()) {
			Enemigo enem = obtenerEnemigo(posicionActual, enemigos);
			if(energiaAgente > enem.getEnergia()) {
				//System.out.println("Combate y gana contra " + enem.getId());
				
				estadoAgente.aumentarEnergia(-0.8*enem.getEnergia()); // Actualizo la energia
				if(enem.getPosicion().getId() != IdNodoEnum.FIN) { // Actualizo la cantidad de enemigos
					estadoAgente.setCantidadEnemigos(estadoAgente.getCantidadEnemigos()-1);
				}
				for(int i=0; i<enemigos.size(); i++) { // Elimino al enemigo de la lista de enemigos conocidos
					if(enem.getId() == enemigos.get(i).getId()) {
						enemigos.remove(i);
						i = enemigos.size();
					}
				}
				estadoAgente.setEnemigosConocidos(enemigos);
				for(int i=0; i<mapaAgente.size(); i++) { // Actualizo el mapa conocido (elimino al enemigo)
					if(enem.getPosicion().getId() == mapaAgente.get(i).getId()) {
						estadoAgente.getMapaConocido().getMapa().get(i).setEstado(EstadoEnum.VACIO);
						i = mapaAgente.size();
					}
				}
				posicionActual.setEstado(EstadoEnum.VACIO);
				return estadoAgente;
			} 
		}
		return null;
	}

	@Override
	public Double getCost() {
		return 2.0;
	}

	@Override
	public EnvironmentState execute(AgentState ast, EnvironmentState est) {
		EstadoAgente estadoAgente = (EstadoAgente) ast;
		EstadoAmbiente estadoAmbiente = (EstadoAmbiente) est;
		Nodo posicionActual = estadoAgente.getPosicion();
		Double energiaAgente = estadoAgente.getEnergiaDisponible();
		ArrayList<Enemigo> enemigos = estadoAgente.getEnemigosConocidos();
		ArrayList<Enemigo> enemigosAmbiente = estadoAmbiente.getEnemigos();
		ArrayList<Nodo> mapaAgente = estadoAgente.getMapaConocido().getMapa();
		ArrayList<Nodo> mapaAmbiente = estadoAmbiente.getMapaAmbiente().getMapa();
		
		Enemigo enemigo = obtenerEnemigo(posicionActual, estadoAgente.getEnemigosConocidos());
		if(energiaAgente > enemigo.getEnergia()) {			
			estadoAgente.aumentarEnergia(-0.8*enemigo.getEnergia());
			estadoAmbiente.setEnergiaAgente(estadoAgente.getEnergiaDisponible());
			if(enemigo.getPosicion().getId() != IdNodoEnum.FIN) {
				estadoAgente.setCantidadEnemigos(estadoAgente.getCantidadEnemigos()-1);
				estadoAmbiente.setCantidadEnemigos(estadoAgente.getCantidadEnemigos());
			}
			for(int i=0; i<enemigos.size(); i++) {
				if(enemigo.getId() == enemigos.get(i).getId()) {
					enemigos.remove(i);
					i = enemigos.size();
				}
			}
			estadoAgente.setEnemigosConocidos(enemigos);
			for(int j=0;j<enemigosAmbiente.size(); j++) {
				if(enemigo.getId() == enemigosAmbiente.get(j).getId()) {
					enemigosAmbiente.remove(j);
					j = enemigosAmbiente.size();
				}
			}
			estadoAmbiente.setEnemigos(enemigosAmbiente);
			for(int i=0; i<mapaAgente.size(); i++) {
				if(enemigo.getPosicion().getId() == mapaAgente.get(i).getId()) {
					estadoAgente.getMapaConocido().getMapa().get(i).setEstado(EstadoEnum.VACIO);
					i = mapaAgente.size();
				}
			}
			for(int j=0; j<mapaAmbiente.size(); j++) {
				if(enemigo.getPosicion().getId() == mapaAmbiente.get(j).getId()) {
					estadoAmbiente.getMapaAmbiente().getMapa().get(j).setEstado(EstadoEnum.VACIO);
					j = mapaAmbiente.size();
				}
			}
			estadoAgente.getPosicion().setEstado(EstadoEnum.VACIO);
			estadoAmbiente.setUbicacionAgente(estadoAgente.getPosicion());
			
			System.out.println("Vence a: " + enemigo.getId() + "/" + enemigo.getEnergia());
			System.out.println("Pierde " + 0.8*enemigo.getEnergia());
			
			return estadoAmbiente;
		}
		return null;

	}

	@Override
	public String toString() {
		return "Combate";
	}
	
	public Enemigo obtenerEnemigo(Nodo nodo, ArrayList<Enemigo> enemigos) {
		//System.out.println("Enemigo en: " + nodo.getId());
		for(Enemigo e: enemigos) {
			//System.out.println("Enemigo conocido: " + e.getId() + "/" + e.getPosicion().getId());
			if(e.getPosicion().getId() == nodo.getId()) {
				return e;
			}
		}
		return null;
	}

}
