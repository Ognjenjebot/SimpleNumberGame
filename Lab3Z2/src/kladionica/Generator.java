package kladionica;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Generator {

	public static int generateNum(int x, int y) {
		/*
		Random random = new Random();
	    return random.nextInt(x - y) + x;
		*/
		int randomNum = ThreadLocalRandom.current().nextInt(x, y + 1);
		return randomNum;//Ukljucene su i granice, ovo gore u slucaju da nije dozvoljen ovaj nacin
	}
	
	public static void main(String[] args) {
		System.out.println(Generator.generateNum(3, 8));  
	}
}
