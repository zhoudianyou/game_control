package com.cslc.eils.gameControl.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import com.cslc.eils.gameControl.core.InitSystem;
import com.cslc.eils.gameControl.dto.Group;
import com.cslc.eils.gameControl.dto.GroupRes;
import com.cslc.eils.gameControl.pojo.Game;

public class FTPControlUtil {

	
	private static final String ip = InitSystem.getFtpProperty("ip");
	private static final int port = Integer.parseInt(InitSystem.getFtpProperty("port"));
	private static final String username = InitSystem.getFtpProperty("username");
	private static final String password = InitSystem.getFtpProperty("password");
	private static final String remoteBasePath = InitSystem.getFtpProperty("remoteBasePath");	
	private static final String localBasePath= InitSystem.getFtpProperty("localBasePath");	
	private static final String fileSuffix = InitSystem.getFtpProperty("fileSuffix");	
	
	
	
	public static String getFileNameFromFtp(){
		return "";
	}
	
	/**
	 * 从ftp下载奖组文件
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean copyFileFormFtp(Group group){

		String fileName = group.getGroupSn()+fileSuffix;
		
		// 初始表示下载失败
		boolean success = false;
		
		// 创建FTPClient对象
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			// 连接FTP服务器
			ftp.connect(ip, port);
			// 登录ftp
			ftp.login(username, password);
			reply = ftp.getReplyCode();
			
			if (FTPReply.isPositiveCompletion(reply)) {
				// 转到指定下载目录
				ftp.changeWorkingDirectory(getFileDir(group));
				
				// 列出该目录下所有文件
				FTPFile[] fs = ftp.listFiles();
				int fileLength = fs.length;
				if(0 < fileLength){
					// 遍历所有文件，找到指定的文件
					for (FTPFile ff : fs) {
						if (ff.getName().equals(fileName)) {
							// 根据绝对路径初始化文件
							File localDir = new File(localBasePath+File.separator+getFileDir(group));
							if(!localDir.exists()){
								localDir.mkdirs();
							}
							// 输出流
							OutputStream is = new FileOutputStream(localDir+File.separator+ff.getName());
							// 下载文件
							ftp.retrieveFile(ff.getName(), is);
							is.close();
						}
					}
					// 退出ftp
					ftp.logout();
					// 下载成功
					success = true;
					}
			} 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return success;
	}
	
	/**
	 * 判断奖组文件是否存在
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean existsFileFormFtp(Group group){

		
		String fileName = group.getGroupSn()+fileSuffix;
		
		// 是否存在文件
		boolean success = false;
		
		// 创建FTPClient对象
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			// 连接FTP服务器
			ftp.connect(ip, port);
			// 登录ftp
			ftp.login(username, password);
			reply = ftp.getReplyCode();
			
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return success;
			} 
			
			// 转到指定下载目录
			ftp.changeWorkingDirectory(getFileDir(group));
			
			// 列出该目录下所有文件
			FTPFile[] fs = ftp.listFiles();
			if(0 < fs.length){
				// 遍历所有文件，找到指定的文件
				for (FTPFile ff : fs) {
					if (ff.getName().equals(fileName)) {
						success = true;
						ftp.logout();
						return success;
					}
				}
			}			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return success;
	}
	
	/**
	 * 读文件
	 * 如果flag=true,读取全文
	 * 如果flag=flase,读除最后一行外，其他文件行
	 * 
	 * @param fullFilePath
	 * @param flag 
	 * @return
	 */
	public static String getStrFromFile(String fullFilePath,boolean includeLastLine){
		
		//是否包含文件最后一行
		int n = 1;
		if(includeLastLine){
			n = 0;
		}
		
		//读取文件
		List<String> fileLs = getFileLs(fullFilePath);
	
		//文件信息数据
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<fileLs.size()-n;i++){
			sb.append(fileLs.get(i));
		}
		
		return sb.toString();
	}
	
	/**
	 * 获取文件摘要
	 * 
	 * @param fullFilePath
	 * @return
	 */
	public static String getFileDiges(String fullFilePath){
		//读取文件
		List<String> fileLs = getFileLs(fullFilePath);
		return fileLs.get(fileLs.size()-1);
	}
	
	/**
	 * 读取文件
	 * 根据文件行返回列表
	 * 
	 * @param fullFilePath
	 * @return
	 */
	public static List<String> getFileLs(String fullFilePath){
		
		List<String> fileLs = new ArrayList<String>();
		
		//读取文件
		File f = new File(fullFilePath);
		try {
			
			if(!f.exists()){
				return fileLs;
			}
			
			fileLs = FileUtils.readLines(f, "UTF-8");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileLs;
	}
	/**
	 * 获得文件目录
	 * @param group
	 * @return
	 */
	public static String getFileDir(Group group){
		
		String fileDirPath = group.getGameId()+File.separator+group.getBetValue()+File.separator+group.getGroupId();
		return fileDirPath;
	}
	/**
	 * 获得文件全路径（目录+文件名称）
	 * @param group
	 * @param isLocal 是本地还是ftp
	 * @return
	 */
	public static String getFullFilePath(Group group,boolean isLocal){
		
		String localDirPath = localBasePath+File.separator+getFileDir(group);
		String remoteDirPath = remoteBasePath+File.separator+getFileDir(group);
	
		String fileSuffix = InitSystem.getFtpProperty("fileSuffix");
		
		String fileName = group.getGroupSn()+fileSuffix;
		
		String fileFullPath = remoteDirPath+File.separator+fileName;
		
		if(isLocal){
			fileFullPath = localDirPath+File.separator+fileName;
		}	
		return fileFullPath;
	}
	
	/**
	 * 获得文件全路径（目录+文件名称）
	 * @param group
	 * @param isLocal 是本地还是ftp
	 * @return
	 */
	public static String getFullFilePath(GroupRes groupRes,boolean isLocal){
		
		String localDirPath = localBasePath+File.separator+groupRes.getGameId()+File.separator+groupRes.getBetValue()+File.separator+groupRes.getGroupId();
		String remoteDirPath = remoteBasePath+File.separator+groupRes.getGameId()+File.separator+groupRes.getBetValue()+File.separator+groupRes.getGroupId();
	
		String fileSuffix = InitSystem.getFtpProperty("fileSuffix");
		
		String fileName = groupRes.getGroupSn()+fileSuffix;
		
		String fileFullPath = remoteDirPath+File.separator+fileName;
		
		if(isLocal){
			fileFullPath = localDirPath+File.separator+fileName;
		}	
		return fileFullPath;
	}
	/**
	 * 返回固定长度的字符串
	 * 
	 * @param n
	 * @param length
	 * @return
	 */
	public static String fixedLengthString(int n,int length){
		StringBuffer sb = new StringBuffer();
		String str = String.valueOf(n);
		for(int i=0;i<length-str.length();i++){
			sb.append(0);
		}
		sb.append(str);
		return sb.toString();
	}
	
	/**
	 * 从票文件中获取票的记录列表
	 * 
	 * @param fullFilePath
	 * @return
	 */
	public static List<String> getTicketListForFile(String fullFilePath){		
		
		//票数据列表
		List<String> ticketLs = new ArrayList<String>();
		
		//票文件列表
		List<String> fileLs = getFileLs(fullFilePath);
		
		if(fileLs.size()>0){
			ticketLs = fileLs.subList(1, fileLs.size()-2);
		}
		
		return ticketLs;

	}
}
