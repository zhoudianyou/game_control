/**
 * 
 */
package com.cslc.eils.gameControl.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cslc.eils.gameControl.core.BootStrap;
import com.cslc.eils.gameControl.core.InitSystem;
import com.cslc.eils.gameControl.exception.UnSupprotedGameException;
import com.cslc.eils.gameControl.util.GameConfig;




/**
 * @author tianhao
 *
 */
public abstract class BaseAction extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Log log = LogFactory.getLog(BaseAction.class);
	
	private   GameConfig gameConfig = (GameConfig) InitSystem.getBean("gameConfig");;
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.execute(req,resp);
	}
	
	public abstract String execute(HttpServletRequest req, HttpServletResponse resp);
	
	/**
	 * 检查游戏是否在售
	 * @param gameId
	 * @return 
	 * @throws Exception
	 */
	public void check(int gameId) throws UnSupprotedGameException {
		if(gameConfig!=null){
			if(!gameConfig.contains(gameId)){
				log.info("系统不支持此玩法！ 玩法id："+gameId);
				throw new UnSupprotedGameException();
			}
		}else{
			log.error("未获得游戏配置，请检查游戏配置是否正确加载！");
		}
			
		
	};
	
	

	

	

}
