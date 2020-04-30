package com.study.ovsdb.demo3_sslwithfile;

import com.google.common.collect.ImmutableList;
import com.study.ovsdb.clientEmulator.SslUtil;
import com.vmware.ovsdb.callback.ConnectionCallback;
import com.vmware.ovsdb.exception.OvsdbClientException;
import com.vmware.ovsdb.protocol.operation.Select;
import com.vmware.ovsdb.protocol.operation.notation.Function;
import com.vmware.ovsdb.protocol.operation.result.OperationResult;
import com.vmware.ovsdb.service.OvsdbClient;
import com.vmware.ovsdb.service.OvsdbPassiveConnectionListener;
import com.vmware.ovsdb.service.impl.OvsdbPassiveConnectionListenerImpl;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.InputStream;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author sk-qianxiao
 * @date 2020/4/30
 */
public class SslServer {
    private static final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);
    private static final OvsdbPassiveConnectionListener passiveListener = new OvsdbPassiveConnectionListenerImpl(executorService);

    final static ConnectionCallback connectionCallback = new ConnectionCallback() {
        @Override
        public void connected(OvsdbClient ovsdbClient) {
            testSelectTransact(ovsdbClient);
        }

        @Override
        public void disconnected(OvsdbClient ovsdbClient) {
        }
    };


    public static void init() throws Exception {
//        SslContext serverSslCtx = SslContextBuilder.forServer(
//                new File("C:\\Users\\sk-qianxiao\\Desktop\\cert_test\\server.crt"),
//                new File("C:\\Users\\sk-qianxiao\\Desktop\\cert_test\\server.pkcs8"))
//                .trustManager(new File("C:\\Users\\sk-qianxiao\\Desktop\\cert_test\\client.crt"))
//                .build();

        InputStream server_crt = new ClassPathResource("server.crt").getInputStream();
        InputStream server_pkcs8 = new ClassPathResource("server.pkcs8").getInputStream();
        InputStream client_crt = new ClassPathResource("client.crt").getInputStream();
        SslContext serverSslCtx = SslContextBuilder.forServer(server_crt, server_pkcs8)
                .trustManager(client_crt)
                .build();
        passiveListener.startListeningWithSsl(Constans.PORT, serverSslCtx, connectionCallback).join();
    }

    private static void testSelectTransact(OvsdbClient ovsdbClient) {
        try {
            Select select = new Select("Logical_Switch").where("name", Function.EQUALS, "ls1");
            CompletableFuture<OperationResult[]> f = ovsdbClient.transact("hardware_vtep", ImmutableList.of(select));
            OperationResult[] ops = f.join();
            System.out.println("read result========");
            System.out.println(ops.toString());
        } catch (OvsdbClientException e) {
            e.printStackTrace();
        }
    }
}
