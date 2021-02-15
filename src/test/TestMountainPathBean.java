package test;

import static org.junit.Assert.*;
import org.junit.Test;
import logic.bean.MountainPathBean;
import logic.model.enums.GroundEnum;

public class TestMountainPathBean {
	
	@Test
	public void testGetGroundAsText() {
		MountainPathBean bean = new MountainPathBean();
		String[] ground = {GroundEnum.GRASS.toString(),GroundEnum.ROCK.toString()};
		bean.setGround(ground);
		assertEquals("GRASS ROCK ", bean.getGroundAsText());
	}
	
	@Test
	public void testGetAltitudeAsTextNotAvailable() {
		MountainPathBean bean = new MountainPathBean();
		assertEquals("Not available", bean.getAltitudeAsText());
	}
	
	@Test
	public void testGetAltitudeAsText() {
		MountainPathBean bean = new MountainPathBean();
		bean.setAltitude(1200);
		assertEquals("1200 m", bean.getAltitudeAsText());
	}

}
