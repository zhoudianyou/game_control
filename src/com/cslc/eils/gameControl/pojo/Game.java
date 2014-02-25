/**
 * 
 */
package com.cslc.eils.gameControl.pojo;

/**
 * @author tianhao
 *
 */
public class Game {
	
	private int id;
	 
	private int gameType;
	
	private int status;
	
	private String 	gameName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGameType() {
		return gameType;
	}

	public void setGameType(int gameType) {
		this.gameType = gameType;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	
	

}
