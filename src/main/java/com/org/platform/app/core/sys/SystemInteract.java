package com.org.platform.app.core.sys;

import java.io.IOException;
import java.net.ServerSocket;

public class SystemInteract {
    public int getFreePort() throws IOException
    {
        ServerSocket socket = new ServerSocket(0);
        socket.setReuseAddress(true);
        int port = socket.getLocalPort();
        if(socket != null)
        {
            try
            {
                socket.close();
            }
            catch (IOException e)
            {
                System.out.println(e);
            }
        }
        return port;
    }
}
