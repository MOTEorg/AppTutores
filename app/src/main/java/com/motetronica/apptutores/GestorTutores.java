package com.motetronica.apptutores;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by FELIPE on 26/11/2016.
 */

public class GestorTutores {
    //Hash map hace las busquedas sencillas con la cedula
    private HashMap<String, Tutor> tutores = new HashMap<>();
    private static GestorTutores gestor = new GestorTutores();

    public static GestorTutores getInstance() {
        return gestor;
    }
    public static GestorTutores getInstance(String query) {
        gestor = new GestorTutores(query);
        return gestor;
    }
    //Constructor de prueba
    private GestorTutores(){
    saveTutor(new Tutor("Alexander Pierrot", "CEO", "0102030405","Secundaria","foto0"));
    saveTutor(new Tutor("Felipe Torres", "Electronica", "0105702355","Tercer Nivel","foto1"));
    }
    private GestorTutores(String resultado){
        ArrayList<ArrayList<String>> lista_tutores=LeerQuery(resultado);
        for (int i=0; i<lista_tutores.size(); i++){
            String nivel="Primaria";
            switch (Integer.parseInt(lista_tutores.get(i).get(4))){
                case 1:
                    nivel="Primaria";
                    break;
                case 2:
                    nivel="Secundaria";
                            break;
                case 3:
                    nivel="Universidad";
                    break;
                case 4:
                    nivel="Postgrado";
                    break;
                default:
                    break;
            }
            saveTutor(new Tutor(lista_tutores.get(i).get(1)+" "+lista_tutores.get(i).get(2),lista_tutores.get(i).get(0),Double.parseDouble(lista_tutores.get(i).get(3)),nivel,lista_tutores.get(i).get(5)));
        }
    }

    //Poner un nuevo tutor
    private void saveTutor(Tutor tut) {
        tutores.put(tut.getCedula(), tut);
    }

    //Retornar los tutores existentes
    public List<Tutor> getTutores() {
        return new ArrayList<>(tutores.values());
    }

    private ArrayList<ArrayList<String>> LeerQuery( String csvcontent){
        BufferedReader br = null;
        String line = "";
        String separador_campos = ",";
        String separador_registros = ";";
        ArrayList<ArrayList<String>> resultado=new ArrayList<>();
        try {

            br = new BufferedReader(new StringReader(csvcontent));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] registros = line.split(separador_registros);
                for (int i=0; i<registros.length; i++){

                    br = new BufferedReader(new StringReader(registros[i]));
                    line = br.readLine();
                    String[] campos=line.split(separador_campos);
                    ArrayList<String> campos1 =new ArrayList<>();
                    for (int j=0; j<campos.length; j++){
                        campos1.add(campos[j]);
                    }
                    resultado.add(campos1);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return resultado;
    }
}
