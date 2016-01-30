/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test_py4j_server;

import py4j.GatewayServer;

/**
 *
 * @author jupiter
 */
public class Test_py4j_server {

    /**
     * @param args the command line arguments
     */
    public int addition(int first, int second) {
        return first + second;
    }
    public static void main(String[] args) {
        // TODO code application logic here
        Test_py4j_server app = new Test_py4j_server();
        // app is now the gateway.entry_point
        GatewayServer server = new GatewayServer(app);
        server.start();
    }
    
}
