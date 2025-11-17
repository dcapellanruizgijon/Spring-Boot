package repaso_Ale;

import java.util.ArrayList;
import java.util.Collections;

public class repasoCompare {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<repasoCompareTo> repaso = new ArrayList<>();
		
		repaso.add(new repasoCompareTo(12413542, "Libro El quijote", "Cervantes", 1000));
		repaso.add(new repasoCompareTo(12413542, "Libro guapo", "Alguien", 100));
		repaso.add(new repasoCompareTo(12413542, "Libro raro", "Anonimo", 600));
		
		Collections.sort(repaso);
		
		for (repasoCompareTo num: repaso) {
			System.out.println(num);
		}
	}

}
