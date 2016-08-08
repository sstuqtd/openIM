/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.util.*; 
import java.nio.channels.*;
import java.net.*;

/**
 *
 * @author abelk
 */
public class Server {
    public Server(String _ip, short _port){
        ip = _ip;
        port = _port;
    }
    
    public void poll(){
        try{
            Selector _selector = Selector.open();
            
            ServerSocketChannel _ssc = ServerSocketChannel.open();
            InetSocketAddress address = new InetSocketAddress(ip, port); 
            _ssc.socket().bind(address);

            _ssc.configureBlocking(false);

            //向Selector注册Channel及我们有兴趣的事件
            _ssc.register(_selector, SelectionKey.OP_ACCEPT);
            
            int nKeys = _selector.select();
            if (nKeys > 0){
                Set selectedKeys = _selector.selectedKeys();
                Iterator i = selectedKeys.iterator();
                while(i.hasNext()){
                    SelectionKey s = (SelectionKey) i.next();
                    
                    i.remove();
                    if(s.isAcceptable()){
                        Socket socket = ((ServerSocketChannel)s.channel()).accept().socket();
                        SocketChannel sc = socket.getChannel();
                        sc.configureBlocking(true);
                        sc.register(_selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                        
                        whole.reg_client(sc, new client(sc));
                    }else if (s.isReadable()){
                        Socket socket = ((ServerSocketChannel)s.channel()).accept().socket();
                        SocketChannel sc = socket.getChannel();
                        
                        sc.read(dst);
                    }
                }
            }
        }catch(Exception e){
            System.out.println("exception " + e.getMessage());
        }
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String ip = args[0];
        short port = (short)Integer.parseInt(args[1]);
        
        Server _server = new Server(ip, port);
        
        while(true){
            _server.poll();
        }
    }
    
    private final String ip;
    private final short port;
    
}
