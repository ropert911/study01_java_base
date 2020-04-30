package com.study.ovsdb.demo2_ssl;

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


    public static void init(SslUtil.SelfSignedSslContextPair sslContextPair) {
        SslContext serverSslCtx = sslContextPair.getServerSslCtx();
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
