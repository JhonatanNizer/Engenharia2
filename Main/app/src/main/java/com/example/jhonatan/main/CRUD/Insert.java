package com.example.jhonatan.main.CRUD;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.jhonatan.main.Pessoa;

public class Insert extends SQLiteOpenHelper {

    private static final String NOME_DB = "MEU_DB";
    private static final int VERSAO_DB = 1;
    private static final String PATH_DB = "/data/user/0/com.example.jhonatan.main/database/MEU_DB";

    private Context nContext;
    private SQLiteDatabase db;

    public Insert(Context context) {
        super(context, NOME_DB, null, VERSAO_DB);
        this.nContext = context;
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //logica para atualizar db (versões)
    }

    public boolean inserirPessoa (Pessoa p){
        openDB();
        try {
            ContentValues cv = new ContentValues();
            cv.put("nome", p.getNome());
            cv.put("genero", p.getGenero());
            cv.put("email", p.getEmail());
            cv.put("genero", p.getSenha());
            db.insert("pessoa", null, cv);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            db.close();
        }
    }

    private void openDB(){
        if(!db.isOpen()){
            db = nContext.openOrCreateDatabase(PATH_DB, SQLiteDatabase.OPEN_READWRITE, null);
        }
    }

}
