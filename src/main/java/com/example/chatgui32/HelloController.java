package com.example.chatgui32;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class HelloController {
    DataOutputStream out;
    @FXML
    TextField messageField;

    @FXML
    TextArea textArea;

    @FXML
    protected void sendHandler() throws IOException {
        String message = messageField.getText();
        messageField.clear();
        messageField.requestFocus(); // возврат каретки
        textArea.appendText(message + "\n");
        out.writeUTF(message);
    }

    @FXML
    protected void connect(){
        try {
            Socket socket = new Socket("127.0.0.1", 9446);
            out = new DataOutputStream(socket.getOutputStream());
            DataInputStream is = new DataInputStream(socket.getInputStream());

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String responce = is.readUTF();
                            textArea.appendText(responce + "\n");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });
            thread.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}