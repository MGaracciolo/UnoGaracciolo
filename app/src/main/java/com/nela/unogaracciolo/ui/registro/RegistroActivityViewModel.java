package com.nela.unogaracciolo.ui.registro;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.nela.unogaracciolo.models.Usuario;
import com.nela.unogaracciolo.request.ApiClient;
import com.nela.unogaracciolo.ui.login.MainActivity;

public class RegistroActivityViewModel extends AndroidViewModel {
    private Context contexto;
    private MutableLiveData<Usuario> usuarioMutable;
    public RegistroActivityViewModel(@NonNull Application application) {
        super(application);
        contexto=application.getApplicationContext();
    }

    public LiveData<Usuario> getUsuarioMutable() {
        if(usuarioMutable==null){
            usuarioMutable = new MutableLiveData<>();
        }
        return usuarioMutable;
    }

    public void mostrar(Intent intent){
        boolean ingresado = intent.getBooleanExtra("login",false);
        Log.d("errorcito",ingresado+"");
        if(ingresado){
            usuarioMutable.setValue(ApiClient.leerDatos(contexto));
        }else{
            usuarioMutable.setValue(new Usuario());
        }

    }

    public void crear(Usuario usuario){
        ApiClient.guardar(contexto,usuario);
        Toast.makeText(contexto, "Usuario guardado", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(contexto, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        contexto.startActivity(intent);
    }
}
