@echo off
::ת����ǰ�̷�
%~d0
::�򿪵�ǰĿ¼
cd %~dp0
::��������JAR����·��
set MainJar=meizuane.jar
::������JAR����·��
set ExternalJar=MzGameCenterLib_1.1.5_p.jar
::������JAR������������
set packageName=com
echo =========== start combin ==============
::��ѹ��������
jar -xf %ExternalJar%
::�ϲ���JAR��
jar -uf %MainJar% %packageName% 
echo =========== over ==============
echo �ٵ�һ�¾ͽ�����--
pause