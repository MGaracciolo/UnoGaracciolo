package com.nela.unogaracciolo.request;

import android.content.Context;
import android.content.SharedPreferences;

import com.nela.unogaracciolo.models.Usuario;


public class ApiClient {

    private static SharedPreferences sp;
    //garantiza que exista  una sola instancia del sp
    private static SharedPreferences conectar(Context context) {
        if (sp == null) {
            //si el archivo no existe lo crea
            //el 0 es pq el archivo sea privado y accesible solo para la app
            sp = context.getSharedPreferences("datos", 0);
        }
        return sp;
    }

    public static void guardar(Context context, Usuario usuario){

        SharedPreferences sp=conectar(context);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("dni",usuario.getDni());
        editor.putString("apellido",usuario.getApellido());
        editor.putString("nombre",usuario.getNombre());
        editor.putString("email",usuario.getEmail());
        editor.putString("clave",usuario.getClave());
        editor.commit();
    }

    public static Usuario leerDatos(Context context){
        SharedPreferences sp=conectar(context);
        //El segundo valor es lo que devuelve por defecto si no encuentra un dni
        String dni = sp.getString("dni","-1");
        String apellido = sp.getString("apellido","-1");
        String nombre = sp.getString("nombre","-1");
        String email = sp.getString("email","-1");
        String clave = sp.getString("clave","-1");
        Usuario usuario=new Usuario(dni,apellido,nombre,email,clave);
        return usuario;
    }

    public static Usuario login(Context context,String email,String contrasena){

        Usuario usuario=null;

        SharedPreferences sp=conectar(context);
        String dni = sp.getString("dni","-1");
        String apellido = sp.getString("apellido","-1");
        String nombre = sp.getString("nombre","-1");
        String mail = sp.getString("email","-1");
        String clave = sp.getString("clave","-1");

        if (mail.equals(email) && clave.equals(contrasena)){
            usuario=new Usuario(dni,apellido,nombre,mail,clave);

        }

        return usuario;
    }
}
