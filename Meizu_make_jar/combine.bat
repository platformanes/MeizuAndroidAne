@echo off
::转到当前盘符
%~d0
::打开当前目录
cd %~dp0
::你做的主JAR包的路径
set MainJar=meizuane.jar
::第三方JAR包的路径
set ExternalJar=MzGameCenterLib_1.1.5_p.jar
::第三方JAR包顶级包名称
set packageName=com
echo =========== start combin ==============
::解压第三方包
jar -xf %ExternalJar%
::合并主JAR包
jar -uf %MainJar% %packageName% 
echo =========== over ==============
echo 再点一下就结束了--
pause