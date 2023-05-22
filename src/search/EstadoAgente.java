package search;

import java.util.ArrayList;


import extras.*;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;

public class EstadoAgente extends SearchBasedAgentState {

	// Atributos
	private Nodo posicion;
	private Double energiaDisponible;
	private Double energiaInicial;
	private Integer cantidadEnemigos;
	private Mapa mapaConocido;
	private ArrayList<Enemigo> enemigosConocidos;
	private ArrayList<PuntoRecarga> puntosRecargaConocidos;
	private Integer contadorAtaques;
	
	// Constructor
	public EstadoAgente(Integer cantidadEnemigos) {
		this.cantidadEnemigos = cantidadEnemigos;
		this.initState();
	}
	
	//Constructor para el clone
	public EstadoAgente(Nodo pos, Double ed, Double ei, Integer ce, Mapa map, ArrayList<Enemigo> enems, ArrayList<PuntoRecarga> puntos, Integer ca) {
		this.posicion = pos;
		this.energiaDisponible = ed;
		this.energiaInicial = ei;
		this.cantidadEnemigos = ce;
		this.mapaConocido = map;
		this.enemigosConocidos = enems;
		this.puntosRecargaConocidos = puntos;
		this.contadorAtaques = ca;
	}

	// Metodos
	@Override
	public void initState() {
		mapaConocido = new Mapa();
		posicion = mapaConocido.getNodo(IdNodoEnum.AM1);
		energiaInicial = 20.0; // Random entre 10 - 20
		energiaDisponible = energiaInicial;
		enemigosConocidos = new ArrayList<Enemigo>();
		puntosRecargaConocidos = new ArrayList<PuntoRecarga>();
		contadorAtaques = 3; // Empieza en 3 para que el agente pueda usar el poder en el momento en que se habilite
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj.getClass() == this.getClass()) {
			EstadoAgente estado = (EstadoAgente) obj;
			if(estado.getPosicion().equals(posicion) // Misma posicion
					&& estado.getCantidadEnemigos() == cantidadEnemigos // Mismos enemigos
					&& estado.getContadorAtaques() == contadorAtaques // Mismo contador de atauqes
					&& estado.getEnergiaDisponible() == energiaDisponible // Misma energia disponible
					&& estado.getEnergiaInicial() == energiaInicial // Misma energia inicial
					&& estado.getMapaConocido().equals(mapaConocido) // Mismo mapa conocido
					// Misma lista de enemigos conocidos
					&& estado.getEnemigosConocidos().containsAll(enemigosConocidos)
					&& enemigosConocidos.containsAll(estado.getEnemigosConocidos())
					// Misma lista de puntos de recarga conocidos
					&& estado.getPuntosRecargaConocidos().containsAll(puntosRecargaConocidos)
					&& puntosRecargaConocidos.containsAll(estado.getPuntosRecargaConocidos())) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	@Override
	public EstadoAgente clone() {
		EstadoAgente nuevoEstadoAgente = new EstadoAgente(this.getPosicion().clone(),
														  this.getEnergiaDisponible(),
														  this.getEnergiaInicial(),
														  this.getCantidadEnemigos(),
														  this.getMapaConocido().clone(),
														  (ArrayList<Enemigo>) this.getEnemigosConocidos().clone(),
														  (ArrayList<PuntoRecarga>) this.getPuntosRecargaConocidos().clone(),
														  this.getContadorAtaques());
		return nuevoEstadoAgente;
	}

	@Override
	public void updateState(Perception p) {
		PercepcionAgente percepcion = (PercepcionAgente) p;
		
		contadorAtaques++;
		
		// Si el satelite esta habilitado, se actualiza el estado del agente con el satelite
		if(percepcion.getSatelite().isHabilitado()) { 
			System.out.println("Se usa el satelite");
			//this.mapaConocido = percepcion.getSatelite().getMapaSatelite().clone();
			//this.enemigosConocidos = (ArrayList<Enemigo>) percepcion.getSatelite().getEnemigosSatelite().clone();
			//this.puntosRecargaConocidos = (ArrayList<PuntoRecarga>) percepcion.getSatelite().getPuntosRecargaSatelite().clone();
			actualizarMapaConocido(percepcion.getSatelite().getMapaSatelite().clone());
			actualizarEnemigosConocidos((ArrayList<Enemigo>) percepcion.getSatelite().getEnemigosSatelite().clone());
			actualizarPuntosRecargaConocidos((ArrayList<PuntoRecarga>) percepcion.getSatelite().getPuntosRecargaSatelite().clone());
			

			System.out.println("Mapa");
			for(Nodo nodo: mapaConocido.getMapa()) {
				System.out.println(nodo);
			}

			
		}
		// Si el satelite no esta habilitado, se actualiza el estado del agente con la percepcion
		else {
			

			System.out.println("Mapa");
			for(Nodo nodo: mapaConocido.getMapa()) {
				System.out.println(nodo);
			}
			
			actualizarMapaConocido(percepcion.getMapaPercibido().clone());
			actualizarEnemigosConocidos((ArrayList<Enemigo>) percepcion.getEnemigosPercibidos().clone());
			actualizarPuntosRecargaConocidos((ArrayList<PuntoRecarga>) percepcion.getPuntosRecargaPercibidos().clone());
		}
	}
	
	public void actualizarMapaConocido(Mapa mapaPercibido) {
		ArrayList<Nodo> mapa = this.getMapaConocido().getMapa();	
		for(Nodo percibido: mapaPercibido.getMapa()) {
			for(int i=0; i<mapa.size(); i++) {
				// Verifica si el nodo percibido ya era conocido
				if(mapa.get(i).getId() == percibido.getId()
						&& percibido.getEstado() != EstadoEnum.DESCONOCIDO) {
					this.getMapaConocido().getMapa().get(i).actualizarNodo(percibido); // Actualizo el nodo
					i = mapa.size(); // Termino el loop
				}
			}
		}
	}
	
	public void actualizarEnemigosConocidos(ArrayList<Enemigo> enemigosPercibidos){
		boolean banderaEnemigo;
		for(Enemigo percibido: enemigosPercibidos) {
			banderaEnemigo = false;
			for(int i=0; i<enemigosConocidos.size(); i++) {
				// Verifica si el enemigo percibido ya era conocido
				if(enemigosConocidos.get(i).getId() == percibido.getId()) {
					enemigosConocidos.get(i).actualizarEnemigo(percibido); // Actualizo el enemigo
					i = enemigosConocidos.size(); // Termino el loop
					banderaEnemigo = true; // Indico que ya se conocia, por lo que no hay que agregarlo
				}
			}
			if(!banderaEnemigo) { // Si el enemigo no se conocia, se agrega a la lista de enemigos conocidos
				enemigosConocidos.add(percibido);
			}
		}
	}
	
public void actualizarPuntosRecargaConocidos(ArrayList<PuntoRecarga> puntosPercibidos){
		boolean banderaPuntoRecarga;
		for(PuntoRecarga percibido: puntosPercibidos) {
			banderaPuntoRecarga = false;
			for(int i=0; i<puntosRecargaConocidos.size(); i++) {
				// Verifica si el punto percibido ya era conocido
				if(puntosRecargaConocidos.get(i).getPosicion().getId() == percibido.getPosicion().getId()) {
					puntosRecargaConocidos.get(i).actualizarPuntoRecarga(percibido); // Actualizo el punto
					i = puntosRecargaConocidos.size(); // Termino el loop
					banderaPuntoRecarga = true; // Indico que ya se conocia, por lo que no hay que agregarlo
				}
			}
			if(!banderaPuntoRecarga) { // Si el punto no se conocia, se agrega a la lista de puntos conocidos
				puntosRecargaConocidos.add(percibido);
			}
		}
	}

	@Override
	public String toString() {
		String mensaje = "\n";
		
		mensaje += "Posicion: " + posicion.getId() + "\n";
		mensaje += "Energia disponible: " + energiaDisponible + "\n";
		mensaje += "Contador de Ataques: " + contadorAtaques + "\n";
		mensaje += "Enemigos conocidos: [ ";
		for(Enemigo e: enemigosConocidos) {
			mensaje += e.getId() + "/" + e.getPosicion().getId() + ", ";
		}
		mensaje += "]\n";
		mensaje += "Puntos de Recarga conocidos: [ ";
		for(PuntoRecarga pr: puntosRecargaConocidos) {
			mensaje += pr.getPosicion().getId() + ", ";
		}
		mensaje += "]\n";
		mensaje += "Mapa conocido: [ ";
		for(Nodo nodo: mapaConocido.getMapa()) {
			mensaje += nodo.getId() + "/" + nodo.getEstado() + ", ";
		}
		mensaje += "]\n";
		
		return mensaje;
	}
	
	public void aumentarEnergia(Double energia) {
		energiaDisponible += energia;
	}

	//Getters y Setters
	public Nodo getPosicion() {
		return posicion;
	}

	public void setPosicion(Nodo posicion) {
		this.posicion = posicion;
	}

	public Double getEnergiaDisponible() {
		return energiaDisponible;
	}

	public void setEnergiaDisponible(Double energiaDisponible) {
		this.energiaDisponible = energiaDisponible;
	}

	public Double getEnergiaInicial() {
		return energiaInicial;
	}

	public void setEnergiaInicial(Double energiaInicial) {
		this.energiaInicial = energiaInicial;
	}

	public Integer getCantidadEnemigos() {
		return cantidadEnemigos;
	}

	public void setCantidadEnemigos(Integer cantidadEnemigos) {
		this.cantidadEnemigos = cantidadEnemigos;
	}

	public Mapa getMapaConocido() {
		return mapaConocido;
	}

	public void setMapaConocido(Mapa mapaPercibido) {
		this.mapaConocido = mapaPercibido;
	}

	public ArrayList<Enemigo> getEnemigosConocidos() {
		return enemigosConocidos;
	}

	public void setEnemigosConocidos(ArrayList<Enemigo> enemigosConocidos) {
		this.enemigosConocidos = enemigosConocidos;
	}

	public ArrayList<PuntoRecarga> getPuntosRecargaConocidos() {
		return puntosRecargaConocidos;
	}

	public void setPuntosRecargaConocidos(ArrayList<PuntoRecarga> puntosRecargaConocidos) {
		this.puntosRecargaConocidos = puntosRecargaConocidos;
	}

	public Integer getContadorAtaques() {
		return contadorAtaques;
	}

	public void setContadorAtaques(Integer contadorAtaques) {
		this.contadorAtaques = contadorAtaques;
	}
}
