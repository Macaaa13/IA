package search;

import extras.EstadoEnum;
import extras.IdNodoEnum;
import extras.Nodo;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

public class Ambiente extends Environment {

	private Integer numPercept;

	//Contructor
	public Ambiente(Integer cantidadEnemigos) {
		this.numPercept = 0;
		this.environmentState = new EstadoAmbiente(cantidadEnemigos);
	}
	
	//Metodos
	public EstadoAmbiente getEnvironmentState() {
		return (EstadoAmbiente) super.getEnvironmentState();
	}
	
	public String toString() {
		return environmentState.toString();
	}
	
	@Override
	public Perception getPercept() {
		numPercept++;
		System.out.println(" --- Percepcion " + numPercept + " ---");
		
		PercepcionAgente percepcion = new PercepcionAgente();
		EstadoAmbiente estado = this.getEnvironmentState();
		
		/** LLEGA BIEN
		System.out.println("Mapa");
		for(Nodo nodo: estado.getMapaAmbiente().getMapa()) {
			System.out.println(nodo);
		}
		*/
		
		estado.comportamientoEnemigos(); // Se verifica si los enemigos se mueven
		estado.comportamientoPuntosRecarga(); // Se actualizan los ciclos sin usar
		estado.comportamientoSatelite(); // Se habilita/deshabilita
		
		this.setEnvironmentState(estado);
		
		percepcion.initPerception(null, this);
		
		return percepcion;
	}
	
	public boolean agentFailed(Action actionReturned) {
		
		EstadoAmbiente estado = this.getEnvironmentState();
		Double energiaAgente = estado.getEnergiaAgente();
		Nodo posicionAgente = estado.getUbicacionAgente();
		
		if(energiaAgente <= 0.0) { 
				//||
				//posicionAgente.getId() == IdNodoEnum.FIN
				//&& posicionAgente.getEstado() != EstadoEnum.VACIO) {
			return true;
		} 
		else {
			return false;
		}
	}
	
}
