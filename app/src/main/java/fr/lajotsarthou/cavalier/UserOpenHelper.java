package fr.lajotsarthou.cavalier;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;

public class UserOpenHelper extends SQLiteOpenHelper {
    private static final int BASE_VERSION = 1;
    private static final String BASE_NOM = "userdb";

    private static final String TABLE_USER = "table_user";
    public static final String COLONNE_IDUSER = "idUser";
    public static final int COLONNE_IDUSER_ID = 0;
    public static final String COLONNE_USERNAME = "username";
    public static final int COLONNE_USERNAME_ID = 1;
    public static final String COLONNE_PASSWORD = "password";
    public static final int COLONNE_PASSWORD_ID = 2;

    private static final String TABLE_CAVALIER = "table_cavalier";
    public static final String COLONNE_IDCAVALIER = "idCavalier";
    public static final int COLONNE_IDCAVALIER_ID = 0;
    public static final String COLONNE_NOM = "nom";
    public static final int COLONNE_NOM_ID = 1;
    public static final String COLONNE_PRENOM = "prenom";
    public static final int COLONNE_PRENOM_ID = 2;
    public static final String COLONNE_AGE = "age";
    public static final int COLONNE_AGE_ID = 3;
    public static final String COLONNE_SEXE = "sexe";
    public static final int COLONNE_SEXE_ID = 4;
    public static final String COLONNE_NIVEAU = "niveau";
    public static final int COLONNE_NIVEAU_ID = 5;
    public static final String COLONNE_NUMLICENCE = "numLicence";
    public static final int COLONNE_NUMLICENCE_ID = 6;
    public static final String COLONNE_PHOTO = "photo";
    public static final int COLONNE_PHOTO_ID = 7;


    private static final String REQUETE_CREATION_BD = "create table " + TABLE_USER + " ( "
            + COLONNE_IDUSER + " INTEGER primary key autoincrement, "
            + COLONNE_USERNAME + " TEXT not null, "
            + COLONNE_PASSWORD + " text not null);"
            + " create table " + TABLE_CAVALIER + " ( "
            + COLONNE_IDCAVALIER + " integer, "
            + COLONNE_NOM + " text not null, "
            + COLONNE_PRENOM + " text not null, "
            + COLONNE_AGE + " integer not null, "
            + COLONNE_SEXE + " text not null, "
            + COLONNE_NIVEAU + " text not null, "
            + COLONNE_NUMLICENCE + " text primary key not null, "
            + COLONNE_PHOTO + " text);";


    private SQLiteDatabase userDb;

    public UserOpenHelper(Context context) {
        super(context, BASE_NOM, null, BASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(REQUETE_CREATION_BD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table " + TABLE_USER + ";");
        onCreate(db);
    }

    public Boolean insertValue(EditText username, EditText password){
        ContentValues valeur = new ContentValues();

        valeur.put(UserOpenHelper.COLONNE_USERNAME,username.getText().toString());
        valeur.put(UserOpenHelper.COLONNE_PASSWORD,password.getText().toString());

        getWritableDatabase().insert(UserOpenHelper.TABLE_USER, null, valeur);
        return true;
    }

    public Boolean insertValueRider(int id, String nom, String prenom, int age, String sexe
    , String niveau, String numLicence, String lienPhoto){
        ContentValues v = new ContentValues();

        v.put(UserOpenHelper.COLONNE_IDCAVALIER, id);
        v.put(UserOpenHelper.COLONNE_NOM, nom);
        v.put(UserOpenHelper.COLONNE_PRENOM, prenom);
        v.put(UserOpenHelper.COLONNE_AGE, age);
        v.put(UserOpenHelper.COLONNE_SEXE, sexe);
        v.put(UserOpenHelper.COLONNE_NIVEAU, niveau);
        v.put(UserOpenHelper.COLONNE_NUMLICENCE, numLicence);
        v.put(UserOpenHelper.COLONNE_PHOTO, lienPhoto);

        getWritableDatabase().insert(UserOpenHelper.TABLE_CAVALIER, null, v);
        return true;
    }
}
