package com.dl.img;

import java.io.IOException;

import org.junit.Test;

import net.coobird.thumbnailator.Thumbnails;
//import net.coobird.thumbnailator.geometry.Positions;

public class TestImg {
	@Test
	public void run() throws IOException {
		Thumbnails.of("001.jpg").size(200, 300).toFile("C:/image_200x300.jpg");
	}
}
