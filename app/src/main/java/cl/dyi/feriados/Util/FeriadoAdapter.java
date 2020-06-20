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

import cl.dyi.feriados.Model.Feriado;
import cl.dyi.feriados.R;

public class FeriadoAdapter extends ArrayAdapter<Feriado> {

    private ArrayList<Feriado> feriadoList;
    private Context context;
    private int layoutId;


    public FeriadoAdapter(@NonNull Context context, int resource, @NonNull List<Feriado> feriados) {
        super(context, resource, feriados);

        this.context = context;
        this.layoutId = resource;
        this.feriadoList = new ArrayList<>(feriados);
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

        Feriado feriado = feriadoList.get(position);
        viewHolder.fecha.setText(feriado.getFecha());
        viewHolder.nombre.setText(feriado.getNombre());
        return convertView;
        //return super.getView(position, convertView, parent);


    }

    private class ViewHolder{
        public TextView fecha;
        public TextView nombre;
    }
}
