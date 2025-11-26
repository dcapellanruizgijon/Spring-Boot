package repaso_Ale;

public class repasoEnum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		provincia miProvinciaFavorita = provincia.Cádiz;
		System.out.println(miProvinciaFavorita);
		
		// java lo ve como un numero, no como 'cadiz', si hacemos:
		System.out.println(miProvinciaFavorita.ordinal());
		// vemos el numero que le tiene asignado en el enum.
		// con esto podemos hacer cosas como una condicional que vaya desde cadiz a granada o algo asi
		// asi la maquina entiende lo que hay que hacer, si simplemente ponemos un String
		// con malaga o cadiz, no lo comprende.
		// con esto podemos hacer estudios, como "Que provincia es la que mas se visita"
	}

}
