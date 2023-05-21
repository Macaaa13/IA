package extras;

import java.util.ArrayList;

public class Satelite {

	//Atributos
	private Mapa mapaSatelite;
	private ArrayList<Enemigo> enemigosSatelite;
	private ArrayList<PuntoRecarga> puntosRecargaSatelite;
	private Integer ciclosSinUsar;
	private Integer ciclosAEsperar;
	private boolean habilitado;
	
	//Constructores
	public Satelite() {
		mapaSatelite = new Mapa();
		enemigosSatelite = new ArrayList<Enemigo>();
		puntosRecargaSatelite = new ArrayList<PuntoRecarga>();
		ciclosSinUsar = 0;
		ciclosAEsperar = 3;
		habilitado = false;
	}
	
	// Getters y Setters
	public Mapa getMapaSatelite() {
		return mapaSatelite;
	}

	public void setMapaSatelite(Mapa mapaSatelite) {
		this.mapaSatelite = mapaSatelite;
	}

	public Integer getCiclosSinUsar() {
		return ciclosSinUsar;
	}

	public void setCiclosSinUsar(Integer ciclosSinUsar) {
		this.ciclosSinUsar = ciclosSinUsar;
	}

	public Integer getCiclosAEsperar() {
		return ciclosAEsperar;
	}

	public void setCiclosAEsperar(Integer ciclosAEsperar) {
		this.ciclosAEsperar = ciclosAEsperar;
	}

	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}
	
	public ArrayList<Enemigo> getEnemigosSatelite() {
		return enemigosSatelite;
	}

	public void setEnemigosSatelite(ArrayList<Enemigo> enemigosSatelite) {
		this.enemigosSatelite = enemigosSatelite;
	}

	public ArrayList<PuntoRecarga> getPuntosRecargaSatelite() {
		return puntosRecargaSatelite;
	}

	public void setPuntosRecargaSatelite(ArrayList<PuntoRecarga> puntosRecargaSatelite) {
		this.puntosRecargaSatelite = puntosRecargaSatelite;
	}

	// Metodos
	public void aumentarCiclosSinUsar() {
		ciclosSinUsar++;
	}
	
}
