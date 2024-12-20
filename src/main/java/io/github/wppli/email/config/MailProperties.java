package io.github.wppli.email.config;

import org.springframework.boot.context.properties.ConfigurationProperties;


// io:
//  github:
//    wppli:
//      mail:
//        # 配置 SMTP 服务器地址
//        host: smtp.qq.com
//        # 发送者邮箱
//        username: xxxx@qq.com
//        # 配置密码，注意不是真正的密码，而是刚刚申请到的授权码
//        password: xxxxxx
//        # 端口号465或587
//        port: 587
//        # 默认的邮件编码为UTF-8
//        default-encoding: UTF-8
//        # 配置SSL 加密工厂
//        properties: # 设置邮件超时时间防止服务器阻塞
//          timeout: 5000
//          connection-timeout: 5000
//          write-timeout: 5000
//          mail:
//            smtp:
//              socketFactoryClass: javax.net.ssl.SSLSocketFactory
//            #表示开启 DEBUG 模式，这样，邮件发送过程的日志会在控制台打印出来，方便排查错误
//            debug: true
/**
 * @author li--jiaqiang 2024−12−20
 */

@ConfigurationProperties(prefix = "io.github.wppli.email", ignoreInvalidFields = true)
public class MailProperties {
    /** SMTP 服务器地址 */
    private String host = "smtp.qq.com";
    /** 发送者邮箱 */
    private String username = "2148660566@qq.com";
    /** 注意不是真正的密码，而是申请到的授权码 */
    private String password = "hodmnimzeejrdiej";
    /** 端口号465或587 */
    private int port = 587;
    /** 默认的邮件编码为UTF-8 */
    private String defaultEncoding = "UTF-8";
    /** 其他属性 */
    private Properties properties = new Properties();

    // Getters and Setters
    public String getHost() {
        return host;
    }
    public void setHost(String host) {
        this.host = host;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public int getPort() {
        return port;
    }
    public void setPort(int port) {
        this.port = port;
    }

    public String getDefaultEncoding() {
        return defaultEncoding;
    }
    public void setDefaultEncoding(String defaultEncoding) {
        this.defaultEncoding = defaultEncoding;
    }

    public Properties getProperties() {
        return properties;
    }
    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public static class Properties {
        /** 设置邮件超时时间防止服务器阻塞 */
        private int timeout = 5000;
        private int connectionTimeout = 5000;
        private int writeTimeout = 5000;

        /** 邮件配置 */
        private Mail mail = new Mail();

        // Getters and Setters
        public int getTimeout() {
            return timeout;
        }
        public void setTimeout(int timeout) {
            this.timeout = timeout;
        }

        public int getConnectionTimeout() {
            return connectionTimeout;
        }
        public void setConnectionTimeout(int connectionTimeout) {
            this.connectionTimeout = connectionTimeout;
        }

        public int getWriteTimeout() {
            return writeTimeout;
        }
        public void setWriteTimeout(int writeTimeout) {
            this.writeTimeout = writeTimeout;
        }

        public Mail getMail() {
            return mail;
        }
        public void setMail(Mail mail) {
            this.mail = mail;
        }

        public static class Mail {

            private String transportProtocol = "smtp";

            private Smtp smtp = new Smtp();
            // Getters and Setters
            public Smtp getSmtp() {
                return smtp;
            }
            public void setSmtp(Smtp smtp) {
                this.smtp = smtp;
            }

            public String getTransportProtocol() {
                return transportProtocol;
            }
            public void setTransportProtocol(String transportProtocol) {
                this.transportProtocol = transportProtocol;
            }

            public static class Smtp {
                private String auth = "true";
                private String starttlsEnable = "true";
                private String starttlsRequired = "true";
                private String socketFactoryClass = "javax.net.ssl.SSLSocketFactory";
                /** #表示开启 DEBUG 模式，这样，邮件发送过程的日志会在控制台打印出来，方便排查错误 */
                private boolean debug = true;

                // Getters and Setters
                public String getSocketFactoryClass() {
                    return socketFactoryClass;
                }
                public void setSocketFactoryClass(String socketFactoryClass) {
                    this.socketFactoryClass = socketFactoryClass;
                }

                public String getAuth() {
                    return auth;
                }
                public void setAuth(String auth) {
                    this.auth = auth;
                }

                public String getStarttlsEnable() {
                    return starttlsEnable;
                }
                public void setStarttlsEnable(String starttlsEnable) {
                    this.starttlsEnable = starttlsEnable;
                }

                public String getStarttlsRequired() {
                    return starttlsRequired;
                }
                public void setStarttlsRequired(String starttlsRequired) {
                    this.starttlsRequired = starttlsRequired;
                }

                public boolean isDebug() {
                    return debug;
                }
                public void setDebug(boolean debug) {
                    this.debug = debug;
                }
            }
        }
    }
}