package com.motetronica.apptutores;

/**
 * Created by KS on 19/11/2016.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 *
 * @author FELIPE
 */
public class GeneradorConsultas {
    String servidor="http://www.motetronica.com/";
    String respuesta;




    public Handler h_consultas;

    public GeneradorConsultas(Handler handler){
        this.h_consultas=handler;

    }
    public String formarPares(ArrayList<String> claves, ArrayList<String> valores){
        String resultado="";
        if (claves.size()==valores.size()){
            for (int i=0; i<valores.size(); i++){
                resultado+=claves.get(i)+"="+valores.get(i)+"&";
            }
        }
        else{
            return "error";
        }
        return resultado;
    }

    public String selectFrom(String tabla, ArrayList<String> campos_consultados,ArrayList<String> campos,ArrayList<String> relacionadores,ArrayList<String> valores ){
        String resultado="SELECT ";
        for (int i=0; i<campos_consultados.size()-1; i++){
            resultado+=campos_consultados.get(i)+",";
        }
        resultado+=campos_consultados.get(campos_consultados.size()-1)+" FROM "+ tabla+" WHERE ";
        if (campos.size()==valores.size()){
            for (int i=0; i<valores.size()-1; i++){
                resultado+=campos.get(i)+relacionadores.get(i)+valores.get(i) + " AND ";
            }
            resultado+=campos.get(campos.size()-1)+relacionadores.get(campos.size()-1)+valores.get(campos.size()-1)+";";
        }
        else{
            return "error";
        }
        return resultado;
    }

    public void sendConsultaPost(String url_php, String consulta) {
        final String dataUrl = servidor+url_php;
        final String dataUrlParameters = consulta;
        String responseStr="";

        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
// Create connection
                    URL url = new URL(dataUrl);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    connection.setRequestProperty("Content-Length", "" + Integer.toString(dataUrlParameters.getBytes().length));
                    connection.setRequestProperty("Content-Language", "en-US");
                    connection.setUseCaches(false);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
// Send request
                    DataOutputStream wr = new DataOutputStream(
                            connection.getOutputStream());
                    wr.writeBytes(dataUrlParameters);
                    wr.flush();
                    wr.close();
// Get Response
                    InputStream is = connection.getInputStream();
                    BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                    String line;
                    StringBuffer response = new StringBuffer();
                    while ((line = rd.readLine()) != null) {
                        response.append(line);
                        response.append('\r');
                    }
                    rd.close();
                    Message msg = new Message();
                    msg.obj = response.toString();
                    h_consultas.sendMessage(msg);
                }

                catch (Exception e){
                    Log.d("Internet", "compruebe la conexion");
                    Message msg = new Message();
                    msg.obj = "Error de conexion en la red";
                    msg.what=2;
                    h_consultas.sendMessage(msg);
                    e.printStackTrace();
                    }

                finally {

                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        });
        thread.start();


    }





}
