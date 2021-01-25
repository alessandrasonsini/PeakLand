package logic.controller;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import logic.model.MountainPath;
import logic.model.bean.MountainPathBean;
import logic.model.dao.MountainPathDao;

public class AssistedResearchController extends Controller {

	MountainPathDao mountainPathDao;

	public AssistedResearchController() {
		super();
		this.mountainPathDao = new MountainPathDao();

	}

	@SuppressWarnings("unchecked")
	public List<MountainPath> searchMountainPathByFilter(MountainPathBean wishPath) {
		List<List<MountainPath>> results = new ArrayList<List<MountainPath>>();
		// Chiama il metodo della bean che restituisce tutti i suoi Fields tramite la reflection
		Field[] fields = wishPath.getFields();

		for (Field f : fields) {
			f.setAccessible(true);

			try {
				Object obj = f.get(wishPath);
				if (obj != null) {
					// Prende il tipo del Field
					Class<?> type = f.getType();
					if (type == String[].class) {
						searchFilterInAList((Object[]) obj, f.getName(), 3);
					} else {
						// Prende il metodo del mountainPathDao per effettuare la ricerca per filtro
						Method m = mountainPathDao.getClass().getDeclaredMethod("searchMountainPathbyFilter",
								new Class<?>[] { String.class, type });
						// Invoca il metodo
						Object returnValue = m.invoke(mountainPathDao, new Object[] { f.getName(), obj });
						results.add((List<MountainPath>) returnValue);
					}
				}
			} catch (NoSuchMethodException | SecurityException | IllegalArgumentException | IllegalAccessException
					| InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.print("Exception");
			}
		}
		return mountainPathDao.searchMountainPathByPartialName("Nuovo");
	}

	private List<MountainPath> searchFilterInAList(Object[] list, String filterName, int positionSize) {
		List<MountainPath> results = new ArrayList<>();
		for (Object obj : list) {
			for (int i = 0; i < positionSize; i++) {
				results.addAll(mountainPathDao.searchMountainPathbyFilter(filterName + "/" + String.valueOf(i),
						obj.toString()));
			}
		}
		return results;
	}

	@Override
	public void setNextPageId(String action) {
		String nextPageId;
		switch (action) {
		case "init":
			nextPageId = "Assisted research";
			break;
		default:
			nextPageId = null;
		}
		this.nextPageId = nextPageId;

	}

}
