package cl.dyi.feriados.Util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import cl.dyi.feriados.Model.Specialty;
import cl.dyi.feriados.R;

public class FeriadoAdapter extends ArrayAdapter<Specialty> {

    private ArrayList<Specialty> specialtyList;
    private Context context;
    private int layoutId;


    public FeriadoAdapter(@NonNull Context context, int resource, @NonNull List<Specialty> specialties) {
        super(context, resource, specialties);

        this.context = context;
        this.layoutId = resource;
        this.specialtyList = new ArrayList<>(specialties);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;

        if( convertView == null ){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(layoutId, null);
            viewHolder = new ViewHolder();

            viewHolder.fecha = (TextView) convertView.findViewById(R.id.feriado_list_item_fecha);
            viewHolder.nombre = (TextView) convertView.findViewById(R.id.feriado_list_item_nombre);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Specialty specialty = specialtyList.get(position);
        viewHolder.fecha.setText(specialty.getId());
        viewHolder.nombre.setText(specialty.getName());
        return convertView;
        //return super.getView(position, convertView, parent);
    }

    private class ViewHolder{
        public TextView fecha;
        public TextView nombre;
    }
}
