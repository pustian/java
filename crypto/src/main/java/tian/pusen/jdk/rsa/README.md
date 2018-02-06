 keytool -genkey -alias jfpalkey -keyalg RSA -keysize 2048 -keypass passwdJfpal -validity 3650 -dname "CN=open-unitedbank, OU=jfpal, O=com, L=pudong, ST=shanghai, C=cn" -keystore c:\workspace\keypair\jfpal.jks -storepass passwdJfpal

查看证书
keytool -list -v -keystore c:\workspace\keypair\jfpal.jks -storepass passwdJfpal

导出证书
 keytool -export -alias jfpalkey -keystore c:\workspace\keypair\jfpal.jks -file c:\workspace\keypair\jfpal.cer -storepass passwdJfpal

导入名另中密码使用的是keystore文件的密码 导入证书命令
 keytool -import -alias cacerts 　-keystore C:\jdk1.5\jre\lib\security\cacerts 　　　　　　　　-file c:\workspace\keypair\jfpal.cer 　　　　　　　　-trustcacerts 此时命令行会提示你输入cacerts证书库的密码，你敲入changeit就行了，这是java中cacerts证书库的默认密码，然后就好了。

删除命令
 keytool -delete -alias emailcert -keystore "C:\Program Files (x86)\Java\jre6\lib\security\cacerts" -storepass changeit