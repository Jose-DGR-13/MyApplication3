package com.example.estudiante.myapplication;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    Button down = (Button) findViewById(R.id.down);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new EnviarDatos().execute();
            }
        });

    }

    public class EnviarDatos extends AsyncTask<String,Integer,String>{

        @Override
        protected String doInBackground(String... strings) {
            String fail = "No joda";
            try {
                InetAddress miHost = InetAddress.getLocalHost();
                String host = miHost.getHostAddress().toString();
                InetAddress ipServidor[] = InetAddress.getAllByName(host);
                for (int i = 0; i<ipServidor.length;i++) {
                    try {
                        DatagramSocket buzon = new DatagramSocket();
                        String mensaje = "Hello World";
                        byte[] datos = mensaje.getBytes();
                        DatagramPacket paquete = new DatagramPacket(datos, 0, datos.length, ipServidor[i], 5000);
                        buzon.send(paquete);
                    } catch (java.io.IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            return fail;
        }

    }
}
