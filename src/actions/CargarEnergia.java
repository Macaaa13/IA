package actions;

import java.util.ArrayList;
import java.util.Random;

import extras.EstadoEnum;
import extras.Nodo;
import extras.PuntoRecarga;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import search.EstadoAgente;
import search.EstadoAmbiente;

public class CargarEnergia extends SearchAction {

	//Atributos
	private Double energiaCargada;
	
	//Metodos
	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
		EstadoAgente estadoAgente = (EstadoAgente) s;
		Nodo posicionAgente = estadoAgente.getPosicion();
		Double energiaAgente = estadoAgente.getEnergiaDisponible();
		
		if(posicionAgente.getEstado() == EstadoEnum.PUNTORECARGA 
				) {
			
			// Busco el punto de recarga en la lista de puntos conocidos por el agente
			PuntoRecarga puntoRecarga = obtenerPuntoRecarga(posicionAgente, estadoAgente.getPuntosRecargaConocidos());
			// Verifico que haya pasado mas de un turno sin usar el punto de recarga
			if(puntoRecarga.getTurnosSinUsar() > 1) {	
				Random rand = new Random();
				energiaCargada = (double) (rand.nextInt(5, 11));
				estadoAgente.aumentarEnergia(energiaCargada); // Se actualiza la energia disponible del agente
				// Se actualizan los turnos sin usar del punto de recarga en el mapa conocido por el agente
				for(PuntoRecarga pr: estadoAgente.getPuntosRecargaConocidos()) {
					if(pr.getPosicion().getId() == puntoRecarga.getPosicion().getId()) {
						pr.setTurnosSinUsar(0); // Actualizo la informacion del punto
					}
				}
				return estadoAgente;
			}
		}
		return null;
	}

	@Override
	public Double getCost() {
		return 0.5;
	}

	@Override
	public EnvironmentState execute(AgentState ast, EnvironmentState est) {
		EstadoAgente estadoAgente = (EstadoAgente) ast;
		EstadoAmbiente estadoAmbiente = (EstadoAmbiente) est;
		Nodo posicionAgente = estadoAgente.getPosicion();
		Double energiaAgente = estadoAgente.getEnergiaDisponible();
		
		if(posicionAgente.getEstado() == EstadoEnum.PUNTORECARGA 
				) {
			
			// Busco el punto de recarga en la lista de puntos conocidos por el agente
			PuntoRecarga puntoRecarga = obtenerPuntoRecarga(posicionAgente, estadoAgente.getPuntosRecargaConocidos());
			// Verifico que haya pasado mas de un turno sin usar el punto de recarga
			if(puntoRecarga.getTurnosSinUsar() > 1) {
				Random rand = new Random();
				energiaCargada = (double) (rand.nextInt(5, 11));
				estadoAgente.aumentarEnergia(energiaCargada); // Se actualiza la energia disponible del agente
				// Se actualizan los turnos sin usar del punto de recarga en el mapa conocido por el agente
				
				/** PODRIA FALLAR: NO SE SI CON ACTUALIZAR PR ACTUALIZO EL PUNTO EN LA LISTA */
				
				for(PuntoRecarga pr: estadoAgente.getPuntosRecargaConocidos()) {
					if(pr.getPosicion().getId() == puntoRecarga.getPosicion().getId()) {
						pr.setTurnosSinUsar(0); // Actualizo la informacion del punto
					}
				}
				// Se actualiza la energia disponible del agente en el estado del ambiente
				estadoAmbiente.setEnergiaAgente(estadoAgente.getEnergiaDisponible());
				// Se actualizan los turnos sin usar del punto de recarga en el mapa del ambiente
				
				/** PODRIA FALLAR: NO SE SI CON ACTUALIZAR PRA ACTUALIZO EL PUNTO EN LA LISTA */
				
				for(PuntoRecarga pra: estadoAmbiente.getPuntosRecarga()) {
					if(pra.getPosicion().getId() == puntoRecarga.getPosicion().getId()) {
						pra.setTurnosSinUsar(0);
					}
				}
				
				System.out.println("Puntos cargados: " + energiaCargada);
				
				return estadoAmbiente;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "Cargar energia";
	}
	
	public PuntoRecarga obtenerPuntoRecarga(Nodo nodo, ArrayList<PuntoRecarga> puntos) {
		for(PuntoRecarga pr: puntos) {
			if(pr.getPosicion().getId() == nodo.getId()) {
				return pr;
			}
		}
		return null;
	}

}
