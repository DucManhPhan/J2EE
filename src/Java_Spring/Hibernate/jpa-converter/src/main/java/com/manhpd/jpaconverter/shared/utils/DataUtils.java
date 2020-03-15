package com.manhpd.jpaconverter.shared.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.Query;
import javax.management.QueryExp;
import javax.servlet.http.HttpServletRequest;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.security.SignatureException;
import java.util.*;

public class DataUtils {

    private static final Logger logger = LogManager.getLogger(DataUtils.class);

    private static final String[] IP_HEADER_CANDIDATES = {
            "X-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR",
            "HTTP_FORWARDED",
            "HTTP_VIA",
            "REMOTE_ADDR"
    };

    public static String getIpAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (Objects.isNull(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }

        return ipAddress;
    }

    public static String getIpTomcatServer() {
        try {
            MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
            QueryExp subQuery1 = Query.match(Query.attr("protocol"), Query.value("HTTP/1.1"));
            QueryExp subQuery2 = Query.anySubString(Query.attr("protocol"), Query.value("Http11"));
            QueryExp query = Query.or(subQuery1, subQuery2);
            Set<ObjectName> objs = mbs.queryNames(new ObjectName("*:type=Connector,*"), query);
            String hostname = InetAddress.getLocalHost().getHostName();
            InetAddress[] addresses = InetAddress.getAllByName(hostname);
            for (Iterator<ObjectName> i = objs.iterator(); i.hasNext();) {
                ObjectName obj = i.next();
                String port = obj.getKeyProperty("port");
                for (InetAddress addr : addresses) {
                    if (addr.isAnyLocalAddress() || addr.isLoopbackAddress()
                            || addr.isMulticastAddress()) {
                        continue;
                    }
                    String host = addr.getHostAddress();
                    String ip = host + ":" + port;

                    return ip;
                }
            }
            return "";
        } catch (Exception e) {
            logger.info("isMasterRunning exception: " + e);
            return "";
        }
    }

    public static String getIpAddressRequest() {
        if (Objects.isNull(RequestContextHolder.getRequestAttributes())) {
            return "0.0.0.0";
        }

        String ip;
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        for (String header : IP_HEADER_CANDIDATES) {
            String ipList = request.getHeader(header);
            if (ipList != null && ipList.length() != 0 && !"unknown".equalsIgnoreCase(ipList)) {
                ip = ipList.split(",")[0];
                return ip;
            }
        }

        ip = request.getRemoteAddr();
        if ("0:0:0:0:0:0:0:1".equals(ip)) {
            ip = "127.0.0.1";
        }

        return ip;
    }

    public static TransactionStatus createTransStatus(PlatformTransactionManager transactionManager) {
        TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);

        return transactionStatus;
    }

}
