package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;
import org.junit.Test;
import logic.bean.MountainPathBean;
import logic.model.MountainPath;
import logic.model.enums.GroundEnum;
import logic.model.enums.LandscapeEnum;
import logic.model.utils.MountainPathConverter;

public class TestMountainPathConverter {

	@Test
	public void testGetMountainPathBeanLandscapeField() {
		MountainPath mountainPath = new MountainPath();
		mountainPath.setLandscape(Arrays.asList(LandscapeEnum.LAKE));
		
		MountainPathBean bean = MountainPathConverter.getMountainPathBean(mountainPath);
		
		String[] expected = new String[1];
		expected[0] = "LAKE";
		
		assertEquals(expected[0], bean.getLandscape()[0]);
	}
	
	@Test
	public void testGetMountainPathBeanGroundFieldSize() {
		MountainPath mountainPath = new MountainPath();
		mountainPath.setGround(Arrays.asList(GroundEnum.GRASS, GroundEnum.ROCK));
		
		MountainPathBean bean = MountainPathConverter.getMountainPathBean(mountainPath);
		
		assertEquals(2, bean.getGround().length);
	}
}
