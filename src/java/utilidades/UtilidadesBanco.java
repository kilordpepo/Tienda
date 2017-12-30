/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/**
 *
 * @author pepo
 */
public class UtilidadesBanco {
    private String tarjeta;
    private String clave;
    
  public UtilidadesBanco(String tarjeta,String clave){
      
      this.clave=clave;
      this.tarjeta=tarjeta;
  }
  
  public String RealizarCompra(double monto){
  
      try {
            String certificateName = "myTrustStore";
            String path = "/home/pepo/Documents/workspace/Seguridad/" + certificateName;
            String passphrase = "arepas95";

            System.setProperty("javax.net.ssl.trustStore", path);
            System.setProperty("javax.net.ssl.trustStorePassword", passphrase);

            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket sslsocket = (SSLSocket) factory.createSocket("200.8.215.117", 8189);

            DataOutputStream out = new DataOutputStream(sslsocket.getOutputStream());
            DataInputStream in = new DataInputStream(sslsocket.getInputStream());
            System.out.println("Conexion realizada..");
            
            out.writeUTF(tarjeta);
            out.writeUTF(clave);
            out.writeDouble(monto);
            int resultado = in.readInt();
            
            switch(resultado){
            
                case 1:{
                    return "Compra exitosa";
                }
                case 2:{
                return "Saldo insuficiente";
                }
                case 3: {
                return "Datos incorrectos";
                }
                default:{
                return "Error desconocido";
                }
            }
            
        } catch (UnknownHostException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
  }
    
}
