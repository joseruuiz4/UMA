import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class ClienteUDP {
    public static void main(String[] args) throws IOException {
        DatagramSocket s = new DatagramSocket();
        System.out.println(s.getLocalPort());

        String texto = "Esto es un mensaje de prueba";

        DatagramPacket dp = new DatagramPacket(
            texto.getBytes(StandardCharsets.UTF_8), // Datos
            texto.getBytes(StandardCharsets.UTF_8).length, // Longitud de los datos
            InetAddress.getByName("127.0.0.1"), // IP del servidor
            2000 // puerto del servidor
        );

        s.send(dp);
        System.out.println("Texto enviado con éxito");

        s.close();
        
    }
}