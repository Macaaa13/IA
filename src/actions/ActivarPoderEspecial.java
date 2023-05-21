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

	// Atributos
	private String poderEspecial;
	
	// Metodos
	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
		EstadoAgente estadoAgente = (EstadoAgente) s;
		Nodo posicionAgente = estadoAgente.getPosicion();
		Double energiaDisponible = estadoAgente.getEnergiaDisponible();
		Double energiaInicial = estadoAgente.getEnergiaInicial();
		
		System.out.println("--- ActivarPoderEspecial ---");
		System.out.println("Posicion actual: " + posicionAgente);
		
		/** El agente puede activar un poder si:
		 * 1) Se encuentra en un nodo VACIO
		 * 2) El contador de ataques es >= 4 (deben pasar 3 o mas turnos desde la ultima vez que se activo un poder)
		 * 3) Tiene alguno de sus poderes habilitado (supera su energia inicial en el porcentaje correspondiente al poder)
		 * Como el jefe comienza con 50 puntos de energia, se busca que el active un poder en caso de tener 50 puntos de energia o menos */
		if(posicionAgente.getEstado() == EstadoEnum.VACIO 
				&& energiaDisponible <= 50.0
				&& energiaDisponible > 1) {
			
			if(estadoAgente.getContadorAtaques() >= 4) {
				// Poder especial 1: se activa cuando se supera en un 25% la energia inicial
				// Aumenta la energia del agente en un 20%
				if((energiaDisponible >= (energiaInicial*1.25)) && (energiaDisponible < (energiaInicial*1.75))) {	
					estadoAgente.setContadorAtaques(0); // Se actualiza el contador de ataques
					poderEspecial = "1 - Recuperacion"; // Se indica el poder utilizado
					estadoAgente.aumentarEnergia(energiaDisponible*1.2); // Se actualiza la energia del agente
					
					System.out.println("Activa primer ataque");
					
					return estadoAgente;
				}
				// Poder especial 2: se activa cuando se supera en un 75% la energia inicial
				// Aumenta la energia del agente en un 30%
				else if((energiaDisponible >= (energiaInicial*1.75)) && (energiaDisponible < (energiaInicial*2.2))) {
					estadoAgente.setContadorAtaques(0);
					poderEspecial = "2 - Super Recuperacion";
					estadoAgente.aumentarEnergia(energiaDisponible*1.3);

					System.out.println("Activa segundo ataque");
					
					return estadoAgente;
				}
				// Poder especial 3: se activa cuando se supera en un 120% la energia inicial
				// Aumenta la energia del agente en un 50%
				else if(energiaDisponible >= (energiaInicial*2.2)) {
					estadoAgente.setContadorAtaques(0);
					poderEspecial = "3 - Mega Recuperacion";
					estadoAgente.aumentarEnergia(energiaDisponible*1.5);

					System.out.println("Activa tercer ataque");
					
					return estadoAgente;
				}
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
		if(posicionAgente.getEstado() == EstadoEnum.VACIO 
				&& estadoAgente.getEnergiaDisponible()<=50.0
				&& energiaDisponible > 1) {
			
			if(estadoAgente.getContadorAtaques() >= 4) {
				// Poder especial 1: se activa cuando se supera en un 25% la energia inicial
				// Aumenta la energia del agente en un 20%
				if((energiaDisponible >= (energiaInicial*1.25)) && (energiaDisponible < (energiaInicial*1.75))) {
					estadoAgente.setContadorAtaques(0); // Se actualiza el contador de ataques
					poderEspecial = "1 - Recuperacion"; // Se indica el poder utilizado
					estadoAgente.aumentarEnergia(energiaDisponible*1.2); // Se actualiza la energia del agente
					// Actualizo la energia del agente en el estado del ambiente
					estadoAmbiente.setEnergiaAgente(estadoAgente.getEnergiaDisponible());
					return estadoAmbiente;
				}
				// Poder especial 2: se activa cuando se supera en un 75% la energia inicial
				// Aumenta la energia del agente en un 30%
				else if((energiaDisponible >= (energiaInicial*1.75)) && (energiaDisponible < (energiaInicial*2.2))) {
					estadoAgente.setContadorAtaques(0);
					poderEspecial = "2 - Super Recuperacion";
					estadoAgente.aumentarEnergia(energiaDisponible*1.3);
					// Actualizo la energia del agente en el estado del ambiente
					estadoAmbiente.setEnergiaAgente(estadoAgente.getEnergiaDisponible());
					return estadoAmbiente;
				}
				// Poder especial 3: se activa cuando se supera en un 120% la energia inicial
				// Aumenta la energia del agente en un 50%
				else if(energiaDisponible >= (energiaInicial*2.2)) {
					estadoAgente.setContadorAtaques(0);
					poderEspecial = "3 - Mega Recuperacion";
					estadoAgente.aumentarEnergia(energiaDisponible*1.5);
					// Actualizo la energia del agente en el estado del ambiente
					estadoAmbiente.setEnergiaAgente(estadoAgente.getEnergiaDisponible());
					return estadoAmbiente;
				}
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "Activar poder especial " + poderEspecial;
	}

}
