package extras;

import java.util.Random;

public enum IdEnemigosEnum {

	/**
	 * Puede haber como maximo 23 enemigos, por lo que seleccionamos 23 nombres de
	 * pokemons al azar
	 */
	BULBASAUR, CHARMANDER, SQUIRTLE, PIKACHU, GLOOM, MANKEY, MACHOP, HAUNTER, MAROWAK, KRABBY, GOLEM, KADABRA,
	POLIWRATH, TENTACRUEL, RAPIDASH, CHARIZARD, LAPRAS, RHYHORN, KABUTOPS, SNORLAX, DRAGONITE, AERODACTYL, GYARADOS;

	private static final Random rand = new Random();

	public static IdEnemigosEnum randomId() {
		IdEnemigosEnum[] ids = values();
		return ids[rand.nextInt(ids.length)];
	}
	
}
