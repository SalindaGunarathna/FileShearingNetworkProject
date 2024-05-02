package org.example;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.Scanner;




public class Client {



    private Socket socket;

    private DataInputStream in;

    private DataOutputStream out;

    private Scanner scanner;


    public Client() {

        try {
           socket = new Socket("127.0.0.1",Server.PORT);
            in = new DataInputStream( new BufferedInputStream(socket.getInputStream()));
            out = new DataOutputStream(socket.getOutputStream());

            scanner = new Scanner(System.in);

            getFile();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private  void getFile() throws IOException {
        String filesLenth = in.readUTF();

        int maxFiles = Integer.parseInt(filesLenth);
        String menu  =in.readUTF();

        System.out.println(menu);

        int userSelection = 1;
        boolean isSelectionCorrect = false;
        while (!isSelectionCorrect) {
            System.out.println("Select File number: ");
            userSelection = scanner.nextInt();
            isSelectionCorrect  = userSelection > 0 && userSelection <= maxFiles;
        }

        out.writeUTF(""+userSelection);
        String fileContent = in.readUTF();
        System.out.println("File Content: ");

        System.out.println(fileContent);

        System.out.println("File Downloaded Successfully");

    }

    public static void main(String[] args) {
        new Client();
    }

}
