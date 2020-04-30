初始化
	touch /etc/pki/CA/index.txt
	echo 00 > /etc/pki/CA/serial
	
生成服务器的自签证书
	openssl genrsa -out server.key 1024
	openssl req -new -x509 -key server.key -out server.crt
		Country Name (2 letter code) [XX]:CN
		State or Province Name (full name) []:shichuan
		Locality Name (eg, city) [Default City]:chendu
		Organization Name (eg, company) [Default Company Ltd]:skspruce
		Organizational Unit Name (eg, section) []:ism
		Common Name (eg, your name or your server's hostname) []:dcmserver
		Email Address []:robertxiao@skspruce.net
	openssl pkcs8 -topk8 -in server.key -out server.pkcs8 -nocrypt				私钥转为pkcs8格式

生成客户端的自签证书
	openssl genrsa -out client.key 1024
	openssl req -new -x509 -key client.key -out client.crt
		Country Name (2 letter code) [XX]:CN
		State or Province Name (full name) []:shichuan
		Locality Name (eg, city) [Default City]:chendu
		Organization Name (eg, company) [Default Company Ltd]:skspruce
		Organizational Unit Name (eg, section) []:ism
		Common Name (eg, your name or your server's hostname) []:dcmserver
		Email Address []:robertxiao@skspruce.net
	openssl pkcs8 -topk8 -in client.key -out client.pkcs8 -nocrypt				私钥转为pkcs8格式
	
client.crt			公钥
client.key			私钥
client.pkcs8		私钥-pkcs8格式
server.crt			公钥
server.key			私钥
server.pkcs8		私钥-pkcs8格式