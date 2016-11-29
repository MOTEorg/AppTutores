package com.motetronica.apptutores;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.*;
import java.util.List;

/**
 * Created by FELIPE on 21/11/2016.
 */

public class AdaptadorLista extends ArrayAdapter<Tutor> {
    public AdaptadorLista(Context context, List<Tutor> objects) {
        super(context, 0, objects);
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtener inflater.
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // ¿Existe el view actual?
        if (null == convertView) {
            convertView = inflater.inflate(
                    R.layout.list_item_tutor,
                    parent,
                    false);
        }
        // Referencias UI.
        ImageView avatar = (ImageView) convertView.findViewById(R.id.foto_tutor);
        TextView name = (TextView) convertView.findViewById(R.id.nombre_tutor);
        TextView title = (TextView) convertView.findViewById(R.id.profesion_tutor);


        // Lead actual.
        Tutor tutor = getItem(position);

        // Setup.
        //avatar.setImageDrawable();
        name.setText(tutor.getNombre());
        title.setText(String.valueOf(tutor.getCalificacion()));
        if (tutor.getFoto().compareTo("null.jpg")==0){
            Glide.with(getContext()).load("http://www.motetronica.com/images/"+tutor.getFoto()).into(avatar);
        }else{
            Glide.with(getContext()).load("https://cdn4.iconfinder.com/data/icons/ionicons/512/icon-image-128.png").into(avatar);
        }

        return convertView;
    }
}
