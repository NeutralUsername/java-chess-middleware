import java.net.InetAddress;

public class Server {
    public static void main(String[] args) {
        try {
            String name = "playnodge.com";
            System.out.println("Host: " + name);
            InetAddress host = InetAddress.getByName(name);
            String address = host.getHostAddress();
            System.out.println("IP des Hosts: " + address);
            byte[] ip = host.getAddress();
            InetAddress inetaddress = InetAddress.getByAddress(ip);
            System.out.println("Hostname aus IP: " +
                    inetaddress.getHostName());
        } catch (Exception e) {
            // Generelles Exception-Handling zu Demo-Zwecken
            e.printStackTrace();
        }
    }
}
