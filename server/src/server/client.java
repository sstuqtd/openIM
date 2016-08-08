/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.nio.channels.*;
import java.net.*;

/**
 *
 * @author abelk
 */
public class client {
    public client(SocketChannel _sc){
        sc = _sc;
    }
    
    private final SocketChannel sc;
}
