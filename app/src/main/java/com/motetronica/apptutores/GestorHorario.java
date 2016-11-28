package com.motetronica.apptutores;

import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Created by FELIPE on 27/11/2016.
 */

public class GestorHorario {
    //Plantilla
    private HashMap<String,Integer> horarios_template = new HashMap<>();

    //Variable para cada tutor
    private HashMap<String,Integer> horarios = new HashMap<>();

    /**
     * Constructor de uso preferible
     */
    public GestorHorario(String response){
        loadHorarios();
        ArrayList<String> lista_horarios=LeerQuery(response);
        for (int j=0; j<lista_horarios.size(); j++){
            Log.d("CodigoHorario",lista_horarios.get(j));
            putHorario(lista_horarios.get(j));
        }
    }

    /**
     * Constructor basico
     */
    public GestorHorario(){
        loadHorarios();
    }
    //Poner un nuevo horario

    /**
     * Colocar un horario a la lista;
     * @param code
     */
    public void putHorario(String code){
        horarios.put(code,horarios_template.get(code));
    }
    public ArrayList<Integer> getHorarios(){
        return new ArrayList<>(horarios.values());
    }
    public ArrayList<Integer> getPlantilla(){
        return new ArrayList<>(horarios_template.values());
    }
    //Plantilla de horarios
    private void loadHorarios() {
        horarios_template.put("1007",R.id.txtHora1);
        horarios_template.put("2007",R.id.txtHora2);
        horarios_template.put("3007",R.id.txtHora3);
        horarios_template.put("4007",R.id.txtHora4);
        horarios_template.put("5007",R.id.txtHora5);
        horarios_template.put("6007",R.id.txtHora61);

        horarios_template.put("1008",R.id.txtHora6);
        horarios_template.put("2008",R.id.txtHora7);
        horarios_template.put("3008",R.id.txtHora8);
        horarios_template.put("4008",R.id.txtHora9);
        horarios_template.put("5008",R.id.txtHora10);
        horarios_template.put("6008",R.id.txtHora62);

        horarios_template.put("1009",R.id.txtHora11);
        horarios_template.put("2009",R.id.txtHora12);
        horarios_template.put("3009",R.id.txtHora13);
        horarios_template.put("4009",R.id.txtHora14);
        horarios_template.put("5009",R.id.txtHora15);
        horarios_template.put("6009",R.id.txtHora63);

        horarios_template.put("1010",R.id.txtHora16);
        horarios_template.put("2010",R.id.txtHora17);
        horarios_template.put("3010",R.id.txtHora18);
        horarios_template.put("4010",R.id.txtHora19);
        horarios_template.put("5010",R.id.txtHora20);
        horarios_template.put("6010",R.id.txtHora64);

        horarios_template.put("1011",R.id.txtHora21);
        horarios_template.put("2011",R.id.txtHora22);
        horarios_template.put("3011",R.id.txtHora23);
        horarios_template.put("4011",R.id.txtHora24);
        horarios_template.put("5011",R.id.txtHora25);
        horarios_template.put("6011",R.id.txtHora65);

        horarios_template.put("1012",R.id.txtHora26);
        horarios_template.put("2012",R.id.txtHora27);
        horarios_template.put("3012",R.id.txtHora28);
        horarios_template.put("4012",R.id.txtHora29);
        horarios_template.put("5012",R.id.txtHora30);
        horarios_template.put("6012",R.id.txtHora66);

        horarios_template.put("1013",R.id.txtHora31);
        horarios_template.put("2013",R.id.txtHora32);
        horarios_template.put("3013",R.id.txtHora33);
        horarios_template.put("4013",R.id.txtHora34);
        horarios_template.put("5013",R.id.txtHora35);
        horarios_template.put("6013",R.id.txtHora67);

        horarios_template.put("1014",R.id.txtHora36);
        horarios_template.put("2014",R.id.txtHora37);
        horarios_template.put("3014",R.id.txtHora38);
        horarios_template.put("4014",R.id.txtHora39);
        horarios_template.put("5014",R.id.txtHora40);
        horarios_template.put("6014",R.id.txtHora68);

        horarios_template.put("1015",R.id.txtHora41);
        horarios_template.put("2015",R.id.txtHora42);
        horarios_template.put("3015",R.id.txtHora43);
        horarios_template.put("4015",R.id.txtHora44);
        horarios_template.put("5015",R.id.txtHora45);
        horarios_template.put("6015",R.id.txtHora69);

        horarios_template.put("1016",R.id.txtHora46);
        horarios_template.put("2016",R.id.txtHora47);
        horarios_template.put("3016",R.id.txtHora48);
        horarios_template.put("4016",R.id.txtHora49);
        horarios_template.put("5016",R.id.txtHora50);
        horarios_template.put("6016",R.id.txtHora70);

        horarios_template.put("1017",R.id.txtHora51);
        horarios_template.put("2017",R.id.txtHora52);
        horarios_template.put("3017",R.id.txtHora53);
        horarios_template.put("4017",R.id.txtHora54);
        horarios_template.put("5017",R.id.txtHora55);
        horarios_template.put("6017",R.id.txtHora71);

        horarios_template.put("1018",R.id.txtHora56);
        horarios_template.put("2018",R.id.txtHora57);
        horarios_template.put("3018",R.id.txtHora58);
        horarios_template.put("4018",R.id.txtHora59);
        horarios_template.put("5018",R.id.txtHora60);
        horarios_template.put("6018",R.id.txtHora72);
    }

    private ArrayList<String> LeerQuery( String csvcontent){
        BufferedReader br = null;
        String line = "";
        String separador_campos = ",";
        String separador_registros = ";";
        ArrayList<String> resultado=new ArrayList<>();
        try {

            br = new BufferedReader(new StringReader(csvcontent));
            while ((line = br.readLine()) != null) {
                // use semicolon as separator
                String[] registros = line.split(separador_registros);
                for (int i=0; i<registros.length; i++){
                    resultado.add(registros[i]);
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
