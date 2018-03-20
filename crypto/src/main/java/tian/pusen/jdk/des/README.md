Des 加解密
1，约定密钥
2，加解密

Aes/Desede 加解密
1, 需要先生成key initKey()
2, 加密/解密

算法: DES/ AES/ DESede
模式：CBC/ ECB/ CFB/ OFB/ PCBC
填充: NoPadding/ PKCS5Padding/ ISO10126Padding

// AES
//    算法/模式/填充                16字节加密后数据长度        不满16字节加密后长度
//    AES/CBC/NoPadding             16                          不支持
//    AES/CBC/PKCS5Padding          32                          16
//    AES/CBC/ISO10126Padding       32                          16
//    AES/CFB/NoPadding             16                          原始数据长度
//    AES/CFB/PKCS5Padding          32                          16
//    AES/CFB/ISO10126Padding       32                          16
//    AES/ECB/NoPadding             16                          不支持
//    AES/ECB/PKCS5Padding          32                          16
//    AES/ECB/ISO10126Padding       32                          16
//    AES/OFB/NoPadding             16                          原始数据长度
//    AES/OFB/PKCS5Padding          32                          16
//    AES/OFB/ISO10126Padding       32                          16
//    AES/PCBC/NoPadding            16                          不支持
//    AES/PCBC/PKCS5Padding         32                          16
//    AES/PCBC/ISO10126Padding      32                          16