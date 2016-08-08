/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.util.HashMap;
import java.nio.*;
import java.nio.channels.*;
import java.util.Iterator;

/**
 *
 * @author abelk
 */
public class whole {
    static{
        _whole = new HashMap();
    }
    
    public static void reg_client(SocketChannel sc, client c){
        _whole.put(sc, c);
    }
    
    public static void unreg_cient(SocketChannel sc){
        _whole.remove(sc);
    }
    
    public static void broadcast(byte[] data){
        for (SocketChannel sc : _whole.keySet()) {
            ByteBuffer buffer = ByteBuffer.wrap(data);
            buffer.flip();
            try{
                while(buffer.hasRemaining()){
                    sc.write(buffer);
                }
            }catch(Exception e){
                System.out.println("socket write exception " + e.getMessage());
            }
        }
    }
    
    private static final HashMap<SocketChannel, client> _whole;
}
