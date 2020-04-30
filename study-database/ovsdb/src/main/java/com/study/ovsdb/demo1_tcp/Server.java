package com.study.ovsdb.demo1_tcp;

import com.google.common.collect.ImmutableList;
import com.vmware.ovsdb.callback.ConnectionCallback;
import com.vmware.ovsdb.exception.OvsdbClientException;
import com.vmware.ovsdb.protocol.operation.Select;
import com.vmware.ovsdb.protocol.operation.notation.Function;
import com.vmware.ovsdb.protocol.operation.result.OperationResult;
import com.vmware.ovsdb.service.OvsdbClient;
import com.vmware.ovsdb.service.OvsdbPassiveConnectionListener;
import com.vmware.ovsdb.service.impl.OvsdbPassiveConnectionListenerImpl;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author sk-qianxiao
 * @date 2020/4/30
 */
public class Server {
    static final int PORT = 6641;
    static OvsdbClient gOvsdbClient = null;
    final static ConnectionCallback connectionCallback = new ConnectionCallback() {
        @Override
        public void connected(OvsdbClient ovsdbClient) {
            gOvsdbClient = ovsdbClient;
            testSelectTransact();
        }

        @Override
        public void disconnected(OvsdbClient ovsdbClient) {
            gOvsdbClient = null;
        }
    };
    static final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);
    private static final OvsdbPassiveConnectionListener passiveListener = new OvsdbPassiveConnectionListenerImpl(executorService);

    public static void main(String[] args) {
        passiveListener.startListening(PORT, connectionCallback).join();
        try {
            Thread.sleep(200000);
        } catch (Exception e) {

        }
    }

    private static void testSelectTransact() {
        try {
            Select select = new Select("Logical_Switch").where("name", Function.EQUALS, "ls1");
            CompletableFuture<OperationResult[]> f = gOvsdbClient.transact("hardware_vtep", ImmutableList.of(select));
            OperationResult[] ops = f.join();
            System.out.println("read result========");
            System.out.println(ops.toString());
        } catch (OvsdbClientException e) {
            e.printStackTrace();
        }
    }
}
