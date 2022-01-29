package com.mycompany.a3;

public interface ISteerable {
	
	/**
	 * Moveable Steerable Objects need a value to change direction
	 * @param amount - value to change object direction
	 */
	public void steer(boolean turn);
}
