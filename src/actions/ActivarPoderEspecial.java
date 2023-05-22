package actions;

import extras.EstadoEnum;
import extras.Nodo;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import search.EstadoAgente;
import search.EstadoAmbiente;

public class ActivarPoderEspecial extends SearchAction {

	// Metodos
	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
		EstadoAgente estado = (EstadoAgente) s;
		EstadoAgente estadoAgente = estado.clone();
		Nodo posicionAgente = estadoAgente.getPosicion();
		Double energiaDisponible = estadoAgente.getEnergiaDisponible();
		Double energiaInicial = estadoAgente.getEnergiaInicial();
		
		/** El agente puede activar un poder si:
		 * 1) Se encuentra en un nodo VACIO
		 * 2) El contador de ataques es >= 4 (deben pasar 3 o mas turnos desde la ultima vez que se activo un poder)
		 * 3) Tiene alguno de sus poderes habilitado (supera su energia inicial en el porcentaje correspondiente al poder)
		 * Como el jefe comienza con 50 puntos de energia, se busca que el active un poder en caso de tener 50 puntos de energia o menos */
		if((posicionAgente.getEstado() == EstadoEnum.VACIO || posicionAgente.getEstado() == EstadoEnum.PUNTORECARGA)
				&& estadoAgente.getContadorAtaques() >= 4 && energiaDisponible<30.0) {
			// Poder especial 1: se activa cuando se supera en un 25% la energia inicial
			// Aumenta la energia del agente en un 20%
			if((energiaDisponible >= (energiaInicial*1.25)) && (energiaDisponible < (energiaInicial*1.75))) {	
				estadoAgente.setContadorAtaques(0); // Se actualiza el contador de ataques
				estadoAgente.aumentarEnergia(energiaDisponible*0.2); // Se actualiza la energia del agente
			
				//System.out.println("Activa primer ataque");
				
				return estadoAgente;
			}
			// Poder especial 2: se activa cuando se supera en un 75% la energia inicial
			// Aumenta la energia del agente en un 30%
			else if((energiaDisponible >= (energiaInicial*1.75)) && (energiaDisponible < (energiaInicial*2.2))) {
				estadoAgente.setContadorAtaques(0);
				estadoAgente.aumentarEnergia(energiaDisponible*0.3);
				
				//System.out.println("Activa segundo ataque");
				
				return estadoAgente;
			}
			// Poder especial 3: se activa cuando se supera en un 120% la energia inicial
			// Aumenta la energia del agente en un 50%
			else if(energiaDisponible >= (energiaInicial*2.2)) {
				estadoAgente.setContadorAtaques(0);
				estadoAgente.aumentarEnergia(energiaDisponible*0.5);

				//System.out.println("Activa tercer ataque");
				
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
		Double energiaInicial = estadoAgente.getEnergiaInicial();
		
		/** El agente puede activar un poder si:
		 * 1) Se encuentra en un nodo VACIO
		 * 2) El contador de ataques es >= 4 (deben pasar 3 o mas turnos desde la ultima vez que se activo un poder)
		 * 3) Tiene alguno de sus poderes habilitado (supera su energia inicial en el porcentaje correspondiente al poder)
		 * Como el jefe comienza con 50 puntos de energia, se busca que el active un poder en caso de tener 50 puntos de energia o menos */
		if((posicionAgente.getEstado() == EstadoEnum.VACIO || posicionAgente.getEstado() == EstadoEnum.PUNTORECARGA)
				&& estadoAgente.getContadorAtaques() >= 4 && energiaDisponible<30.0) {
			// Poder especial 1: se activa cuando se supera en un 25% la energia inicial
			// Aumenta la energia del agente en un 20%
			if((energiaDisponible >= (energiaInicial*1.25)) && (energiaDisponible < (energiaInicial*1.75))) {
				estadoAgente.setContadorAtaques(0); // Se actualiza el contador de ataques
				estadoAgente.aumentarEnergia(energiaDisponible*0.2); // Se actualiza la energia del agente
				// Actualizo la energia del agente en el estado del ambiente
				estadoAmbiente.setEnergiaAgente(estadoAgente.getEnergiaDisponible());
				
				System.out.println("Poder activado: Recuperacion");
				
				return estadoAmbiente;
			}
			// Poder especial 2: se activa cuando se supera en un 75% la energia inicial
			// Aumenta la energia del agente en un 30%
			else if((energiaDisponible >= (energiaInicial*1.75)) && (energiaDisponible < (energiaInicial*2.2))) {
				estadoAgente.setContadorAtaques(0);
				estadoAgente.aumentarEnergia(energiaDisponible*0.3);
				// Actualizo la energia del agente en el estado del ambiente
				estadoAmbiente.setEnergiaAgente(estadoAgente.getEnergiaDisponible());
				
				System.out.println("Poder activado: Super Recuperacion");
				
				return estadoAmbiente;
			}
			// Poder especial 3: se activa cuando se supera en un 120% la energia inicial
			// Aumenta la energia del agente en un 50%
			else if(energiaDisponible >= (energiaInicial*2.2)) {
				estadoAgente.setContadorAtaques(0);
				estadoAgente.aumentarEnergia(energiaDisponible*0.5);
				// Actualizo la energia del agente en el estado del ambiente
				estadoAmbiente.setEnergiaAgente(estadoAgente.getEnergiaDisponible());
				
				System.out.println("Poder activado: Mega Recuperacion");
				
				return estadoAmbiente;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "Activar poder especial ";
	}

}
