CREATE DATABASE day28;
USE day28;

#�û���
CREATE TABLE S_User(
	userID INT  NOT NULL AUTO_INCREMENT, #����ID
	userName VARCHAR(50)   NULL,  #�û�����
	logonName VARCHAR(50)   NULL, #��¼��
	logonPwd VARCHAR(50)  NULL,   #����#
	gender VARCHAR(10)  NULL,     #�Ա����磺�У�Ů��
	birthday VARCHAR(50) NULL,    #��������
	education VARCHAR(20)  NULL,  #ѧ�������磺�о��������ơ�ר�ơ����У�
	telephone VARCHAR(50)  NULL,  #�绰 
	hobby VARCHAR(20)  NULL,   #��Ȥ���ã����磺���������Ρ���֣�
	path VARCHAR(500)  NULL,      #�ϴ�·����path·����
	filename VARCHAR(100)  NULL,  #�ϴ��ļ����ƣ��ļ�����
	remark VARCHAR(500)  NULL,    #��ע
	PRIMARY KEY (userID)
) ;

#��ʼ�����ݣ�Ĭ���û�����������admin
INSERT INTO s_user (userID,userName,logonName,logonPwd) VALUES (1,'��������Ա','admin','admin')
