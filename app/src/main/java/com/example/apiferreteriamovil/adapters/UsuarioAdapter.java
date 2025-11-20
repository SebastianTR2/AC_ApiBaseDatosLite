package com.example.apiferreteriamovil.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apiferreteriamovil.R;
import com.example.apiferreteriamovil.models.Usuario;

import java.util.List;

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioAdapter.ViewHolder> {

    private List<Usuario> lista;
    private OnUsuarioClickListener listener;

    public UsuarioAdapter(List<Usuario> lista) {
        this.lista = lista;
    }

    public interface OnUsuarioClickListener {
        void onLongClick(Usuario usuario);
    }

    public void setOnUsuarioClickListener(OnUsuarioClickListener l) {
        this.listener = l;
    }

    // Crear holder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_usuario, parent, false);
        return new ViewHolder(view);
    }

    // Poner datos
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Usuario u = lista.get(position);

        holder.txtNombre.setText(u.nombre);
        holder.txtCorreo.setText(u.correo);

        // LONG CLICK
        holder.itemView.setOnLongClickListener(v -> {
            if (listener != null) listener.onLongClick(u);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombre, txtCorreo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNombre = itemView.findViewById(R.id.txtNombre);
            txtCorreo = itemView.findViewById(R.id.txtCorreo);
        }
    }
}
