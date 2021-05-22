package com.study.ovsdb.demo3_sslwithfile;

import com.study.ovsdb.clientEmulator.ActiveOvsdbServerEmulator;
import com.study.ovsdb.clientEmulator.SslUtil;
import com.vmware.ovsdb.jsonrpc.v1.util.JsonUtil;
import com.vmware.ovsdb.protocol.operation.Select;
import com.vmware.ovsdb.protocol.operation.notation.Function;
import com.vmware.ovsdb.protocol.util.OvsdbConstant;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author sk-qianxiao
 * @date 2020/4/30
 */
public class SslClient {
    private static final ActiveOvsdbServerEmulator activeOvsdbServer = new ActiveOvsdbServerEmulator(Constans.HOST, Constans.PORT);

    public static void init() throws Exception {
        testSelectTransact();

//        SslContext clientSslCtx = SslContextBuilder.forClient()
//                .keyManager(new File("C:\\Users\\sk-qianxiao\\Desktop\\cert_test\\client.crt"),
//                        new File("C:\\Users\\sk-qianxiao\\Desktop\\cert_test\\client.pkcs8"))
//                .trustManager(new File("C:\\Users\\sk-qianxiao\\Desktop\\cert_test\\server.crt"))
//                .build();

//        InputStream client_crt = new ClassPathResource("client.crt").getInputStream();
//        InputStream client_pkcs8 = new ClassPathResource("client.pkcs8").getInputStream();
        InputStream server_crt = new ClassPathResource("server.crt").getInputStream();
        SslContext clientSslCtx = SslContextBuilder.forClient()
//                .keyManager(client_crt, client_pkcs8)
                .trustManager(server_crt)
                .build();
        activeOvsdbServer.connectWithSsl(clientSslCtx).join();
    }

    private static void testSelectTransact() {
        Select select = new Select("Logical_Switch").where("name", Function.EQUALS, "ls1");
        String expectedRequest = getJsonRequestString(OvsdbConstant.TRANSACT, "hardware_vtep", select);

        setupOvsdbEmulator(expectedRequest,
                "[{\"rows\":[{\"name\":\"demo3 name\",\"description\":\"demo3 description\",\"tunnel_key\":5001}]" + "}]", null
        );
    }

    private static final AtomicInteger id = new AtomicInteger(0);

    private static String getJsonRequestString(String method, Object... params) {
        String strParams = Arrays.stream(params).map(
                JsonUtil::serializeNoException).collect(
                Collectors.toList()).toString().replace(", ", ",");
        return "{\"method\":\"" + method
                + "\",\"params\":" + strParams + ",\"id\":\"" + id.get() + "\"}";
    }

    private static void setupOvsdbEmulator(String request, String result, String error) {
        activeOvsdbServer.registerReadCallback(msg -> {
            if (msg.equals(request)) {
                activeOvsdbServer.write(
                        "{\"id\":\"" + (id.getAndIncrement()) + "\", \"result\":" + result + ", "
                                + "\"error\":" + error + "}");
            }
        });
    }
}
