/**
 * 
 */
package com.cslc.eils.gameControl.util;

/**
 * @author qierfei
 *
 */
public final class Constants {
	
	/**
	 * ��Ϸ״̬����
	 */
	/*�Ѵ���*/
	public final static int GAME_STATUS_CREATED = 0;
	/*����Ʊ*/
	public final static int GAME_STATUS_SELLING = 1;
	/*��ͣ��Ʊ*/
	public final static int GAME_STATUS_PAUSING = 2;
	/*�淨ͣ��*/
	public final static int GAME_STATUS_STOPED = 3;
	
	/**
	 * ����״̬����
	 */
	/*�Ѵ���*/
	public final static int GROUP_STATUS_CREATED = 0;
	/*��Ʊ��*/
	public final static int GROUP_STATUS_CREATETICKET_ONGOING = 1;
	/*��Ʊ���*/
	public final static int GROUP_STATUS_CREATETICKET_COMPLETE = 2;
	/*�����*/
	public final static int GROUP_STATUS_AUDITED = 3;
	/*������*/
	public final static int GROUP_STATUS_IMPORTTICKET_ONGOING =4;
	/*����*/
	public final static int GROUP_STATUS_ONSALE =5;
	/*����*/
	public final static int GROUP_STATUS_SOLD_OUT=6;
	/*ֹ��*/
	public final static int GROUP_STATUS_DELETE=7;
	/*��Ʊ�쳣*/
	public final static int GROUP_STATUS_CREATETICKET_FAILURE=20;
	/*����ʧ��*/
	public final static int GROUP_STATUS_IMPORTICKET_FAILURE=21;
	
	/**
	 * Ʊ״̬����
	 */
	/*δ��*/
	public final static int TICKET_STATUS_ONSALE = 0;
	/*����*/
	public final static int TICKET_STATUS_SOLD = 1;
	/*��ʧЧ*/
	public final static int TICKET_STATUS_INVALID = 2;
	
	/**
	 * ��Ϸ����
	 */
	/*��ͳ��������Ϸ*/
	public final static int GAME_TYPE_TRADITIONAL = 1;
	/*���ʼ�ʱ�� ������Ϸ*/
	public final static int GAME_TYPE_INSTANT_SINGLE = 2;
	/*���ʼ�ʱ�� �ಽ��Ϸ*/
	public final static int GAME_TYPE_INSTANT_MULTI = 3;
	
	/**
	 * ��Ϣ����
	 */
	/*���ݵ�������*/
	public final static int MESSAGE_TYPE_REQUEST_IMPORT_DATA = 101;
	/*���ݵ�����Ӧ*/
	public final static int MESSAGE_TYPE_RESPONSE_IMPORT_DATA = 201;
	/*��Ϸ��������*/
	public final static int MESSAGE_TYPE_REQUEST_START_GAME = 102;
	/*��Ϸ������Ӧ*/
	public final static int MESSAGE_TYPE_RESPONSE_START_GAME = 202;
	/*��Ϸ��ͣ����*/
	public final static int MESSAGE_TYPE_REQUEST_STOP_GAMET = 103;
	/*��Ϸ��ͣ��Ӧ*/
	public final static int MESSAGE_TYPE_RESPONSE_STOP_GAME = 203;
	/*ɾ����������*/
	public final static int MESSAGE_TYPE_REQUEST_DELETE_GROUP = 104;
	/*ɾ��������Ӧ*/
	public final static int MESSAGE_TYPE_RESPONSE_DELETE_GROUP  = 204;
	/*�������*/
	public final static int MESSAGE_TYPE_REQUEST_MONITOR = 105;
	/*�����Ӧ*/
	public final static int MESSAGE_TYPE_RESPONSE_MONITOR  = 205;
	/*��������*/
	public final static int MESSAGE_TYPE_REQUEST_WARNING = 106;
	/*������Ӧ*/
	public final static int MESSAGE_TYPE_RESPONSE_WARNING  = 206;
	
	/*�������ڽ�����*/
	public final static int  TASK_STATUS_ONGOING = 0;
	/*���������*/
	public final static int  TASK_STATUS_COMPLETED = 1;
	
	/**
	 * ϵͳԤ������
	 */
	/*Ʊ�أ�Ʊ����*/
	public final static int SYS_WARNING_TYPE_TICKET_LACKING = 1;
	
	
}
