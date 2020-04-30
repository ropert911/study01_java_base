package com.study.ovsdb.demo1_tcp;

import com.study.ovsdb.clientEmulator.ActiveOvsdbServerEmulator;
import com.vmware.ovsdb.jsonrpc.v1.util.JsonUtil;
import com.vmware.ovsdb.protocol.operation.Select;
import com.vmware.ovsdb.protocol.operation.notation.Function;
import com.vmware.ovsdb.protocol.util.OvsdbConstant;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author sk-qianxiao
 * @date 2020/4/30
 */
public class Client {
    static final String HOST = "127.0.0.1";
    static final int PORT = 6641;

    private static final ActiveOvsdbServerEmulator activeOvsdbServer = new ActiveOvsdbServerEmulator(HOST, PORT);

    public static void main(String[] args) {
        testSelectTransact();
        activeOvsdbServer.connect().join();
    }

    private static void testSelectTransact() {
        Select select = new Select("Logical_Switch").where("name", Function.EQUALS, "ls1");
        String expectedRequest = getJsonRequestString(OvsdbConstant.TRANSACT, "hardware_vtep", select);

        setupOvsdbEmulator(expectedRequest,
                "[{\"rows\":[{\"name\":\"name_test\",\"description\":\"this is test description\",\"tunnel_key\":5001}]" + "}]", null
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
