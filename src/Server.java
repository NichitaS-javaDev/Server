import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends JFrame {

    private ServerSocket server;
    private Socket client;
    //private BufferedWriter writer;
    private BufferedReader reader;
    private FileWriter writer;

    Server() throws IOException {
       createGUI();
       createServer();
    }

    public static void main(String[] args) throws IOException {
        new Server();
    }
    public void createServer() throws IOException {
        server = new ServerSocket(8000);
        System.out.println("Server is waiting for client");

        while (true) {

            client = server.accept();
            System.out.println("Client Accepted !");

            reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            //writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            writer = new FileWriter("R.txt",true);
            String message = reader.readLine();
            System.out.println("Client send you a message : " + message);
            writer.write(message);
            writer.write("\n");
            //writer.write("Your message is " + message.length() + " chr long\n");
            //writer.flush();

            writer.close();
            client.close();
        }
    }

    public void createGUI() {

        setLocationRelativeTo(null);
        setSize(new Dimension(500,500));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(new JPanel(new BorderLayout()));

        JLabel label = new JLabel();
        label.setText("Server is running...");
        label.setFont(new Font("",Font.BOLD,24));
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setHorizontalAlignment(SwingConstants.CENTER);


        getContentPane().add(label);
        setVisible(true);
    }

}
