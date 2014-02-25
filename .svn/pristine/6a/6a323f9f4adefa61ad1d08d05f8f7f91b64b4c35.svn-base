/**
 * 
 */
package com.cslc.eils.gameControl.netInterface.jetty;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.ContextHandlerCollection;
import org.mortbay.jetty.handler.StatisticsHandler;
import org.mortbay.jetty.nio.SelectChannelConnector;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.jetty.servlet.ServletHolder;
import com.cslc.eils.gameControl.action.BetAction;
import com.cslc.eils.gameControl.action.PayAction;
import com.cslc.eils.gameControl.action.QueryAction;
import com.cslc.eils.gameControl.action.QueryUserAction;





/**
 * @author tianhao
 *
 */
public class JettyServer {
	
	private static final Log log = LogFactory.getLog(JettyServer.class);

	/***/
	private static JettyServer instance = new JettyServer();
	
	private Server server = new Server();
	
	/**
	* 获取单例对象实例
	* @return 单例对象
	*/
	public static JettyServer getInstance() {
		return instance;
	}
	
	/**
	 * 
	 * 启动jetty server
	 * @return Boolean
	 */
	public boolean start(){
		try {
			Connector connector = new SelectChannelConnector();
			connector.setPort(7980);
			server.setConnectors(new Connector[] { connector });

			ContextHandlerCollection contexts = new ContextHandlerCollection();
			server.setHandler(contexts);

			Context root = new Context(contexts, "/", Context.SESSIONS);
			root.addServlet(new ServletHolder(new BetAction()), "/bet.action");
			root.addServlet(new ServletHolder(new QueryAction()), "/query.action");
			root.addServlet(new ServletHolder(new PayAction()), "/pay.action");
			root.addServlet(new ServletHolder(new QueryUserAction()), "/queryUser.action");
			StatisticsHandler stats = new StatisticsHandler();
			contexts.addHandler(stats);

			server.start();
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error("jetty 启动失败！");
		}
		return true;
	}
	
	public boolean stop()  {
		try {
			server.stop();
		} catch (Exception ex) {
		}
		return true;
	}

}
