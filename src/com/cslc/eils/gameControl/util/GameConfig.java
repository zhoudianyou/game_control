/**
 * 
 */
package com.cslc.eils.gameControl.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.io.FileUtils;

import com.cslc.eils.gameControl.pojo.Game;

/**
 * @author Administrator
 *
 */
public class GameConfig {
	
	private  Map<Integer,Game> map = new ConcurrentHashMap<Integer,Game>();
	
	

	public GameConfig(){

	}
	
	/**
	 * 判断是否支持此游戏
	 * @param gameId
	 * @return
	 */
	public  boolean contains(int gameId){
		return map.containsKey(gameId);
	}


	/**
	 *获得游戏
	 * @param gameId
	 * @return
	 */
	public  Game getGame(int gameId){
		return map.get(gameId);
	}
	
	/**
	 * 增加游戏,并同步到配置文件中
	 * @param gameId
	 * @param game
	 * @return
	 */
	public  boolean putGame(int gameId,Game game){

		boolean flag = true;
		//数据同步到入内存
		map.put(gameId, game);
		
		try{
			updataConfFile(map);
		}catch(Exception e){
			
			// TODO EXP1 删除失败
			// TODO EXP2 重写失败
		}
		return flag;
		
	}

	

	
	/**
	 * 删除游戏，并同步到配置文件中
	 * @param gameId
	 * @return
	 */
	public  boolean removeGame(int gameId){
		boolean flag = true;
		//数据同步到入内存
		map.remove(gameId);
		try{
			updataConfFile(map);
		}catch(Exception e){
			
			// TODO EXP1 删除失败
			// TODO EXP2 重写失败
		}
		return flag;
		
	}

	/**
	 * 获得有些配置列表
	 * @param filePath
	 * @return
	 */
	public  Map<Integer, Game> loadGame(){
    	File file = new File(System.getProperty("user.dir")+File.separator
    			+ "conf"+File.separator
    			+ "gameInfo.properties");
    	try {
			List<String> gameInfoStringList = FileUtils.readLines(file, "gbk");
			for(String str : gameInfoStringList){
				Game game = JsonUtil.jsonToPojo(str, Game.class);
				map.put(game.getId(), game);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	
	/**
	 * 数据同步到配置文件
	 * @param map
	 */
	private  void updataConfFile(Map<Integer, Game> map) {
		// 数据同步到配置文件
		List<String> gameList = new ArrayList<String>();
		for (Integer key : map.keySet()) {
			Game g = new Game();
			g.setId(key);
			g.setGameName(map.get(key).getGameName());
			g.setGameType(map.get(key).getGameType());
			g.setStatus(map.get(key).getStatus());

			gameList.add(JsonUtil.pojoToJson(g));
		}
		File file = new File(System.getProperty("user.dir")
				+ "\\conf\\gameInfo.properties");

		if (file.exists()) {
			try {
				FileUtils.forceDelete(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		try {
			FileUtils.writeLines(file, "gbk", gameList, true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public  Map<Integer, Game> getMap() {
		return map;
	}

	public  void setMap(Map<Integer, Game> map) {
		this.map = map;
	}
	
}
