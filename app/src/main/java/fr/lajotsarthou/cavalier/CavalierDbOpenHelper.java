package fr.lajotsarthou.cavalier;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.EditText;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import fr.lajotsarthou.cavalier.modele.CavalierModele;
import fr.lajotsarthou.cavalier.modele.Equide;
import fr.lajotsarthou.cavalier.modele.User;
import fr.lajotsarthou.cavalier.modele.UserModele;

public class CavalierDbOpenHelper extends SQLiteOpenHelper {
    private static final int BASE_VERSION = 1;
    private static final String BASE_NOM = "cavalier.db";

    //table 1 - enregistrement des cavaliers
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

    //table 2 - enregistrement des équidés
    private static final String TABLE_EQUIDE = "table_equide";
    public static final String COLONNE_IDEQUIDE = "idEquide";
    public static final int COLONNE_IDEQUIDE_ID = 0;
    public static final String COLONNE_NOMCOMPLET = "nomComplet";
    public static final int COLONNE_NOMECOMPLET_ID = 1;
    public static final String COLONNE_NOMECURIE = "nomEcurie";
    public static final int COLONNE_NOMECURIE_ID = 2;
    public static final String COLONNE_AGEEQ = "age";
    public static final int COLONNE_AGEEQ_ID = 3;
    public static final String COLONNE_SEXEEQ = "sexe";
    public static final int COLONNE_SEXEEQ_ID = 4;
    public static final String COLONNE_RACE = "race";
    public static final int COLONNE_RACE_ID = 5;
    public static final String COLONNE_ROBE = "robe";
    public static final int COLONNE_ROBE_ID = 6;
    public static final String COLONNE_NUMSIRE = "numeroSire";
    public static final int COLONNE_NUMSIRE_ID = 7;
    public static final String COLONNE_PROPRIETAIRE = "proprietaire";

    //table 3 - enregistrement des engagements / résultats des compétitions
    private static final String TABLE_ENGAGEMENT = "table_engagement";
    public static final String COLONNE_IDENGAGEMENT = "idEngagement";
    public static final int COLONNE_IDENGAGEMENT_ID = 1;
    public static final String COLONNE_NUMENGAGEMENT = "numEngagement";
    public static final int COLONNE_NUMENGAGEMENT_ID = 2;
    public static final String COLONNE_LIEUXCONCOURS = "lieu";
    public static final int COLONNE_LIEUCONCOURS_ID = 3;
    public static final String COLONNE_DISCIPLINE = "discipline";
    public static final int COLONNE_DISCIPLINE_ID = 4;
    public static final String COLONNE_CATEGORIE = "categorie";
    public static final int COLONNE_CATEGORIE_ID = 5;
    public static final String COLONNE_NOMCONCOURS = "nomConcours";
    public static final int COLONNE_NOMCONCOURS_ID = 6;
    public static final String COLONNE_CLASSEMENT = "classement";
    public static final int COLONNE_CLASSEMENT_ID = 7;
    public static final String COLONNE_NUMLICENCECONCOURS = "numLicence";
    public static final int COLONNE_NUMLICENCECONCOURS_ID = 8;
    public static final String COLONNE_IDCHEVAL = "IdCheval";
    public static final int COLONNE_IDCHEVAL_ID = 9;
    public static final String FK_EQUIDE_ID = "fk_equide_id";
    public static final String FK_CAVALIER_NUMLICENCE = "fk_cavalier_numlicence";

    //table 4 - utilisateur
    private static final String TABLE_USER = "table_user";
    public static final String COLONNE_IDUSER = "idUser";
    public static final int COLONNE_IDUSER_ID = 0;
    public static final String COLONNE_USERNAME = "username";
    public static final int COLONNE_USERNAME_ID = 1;
    public static final String COLONNE_PASSWORD = "password";
    public static final int COLONNE_PASSWORD_ID = 2;

    private static final String REQUETE_CREATION_BD_USER = "create table " + TABLE_USER + " ( "
            + COLONNE_IDUSER + " INTEGER primary key autoincrement, "
            + COLONNE_USERNAME + " TEXT not null, "
            + COLONNE_PASSWORD + " text not null);";


    private static final String REQUETE_CREATION_DB_TABLECAV = "create table " + TABLE_CAVALIER + " ( "
            + COLONNE_IDCAVALIER + " integer auto_increment, "
            + COLONNE_NOM + " text not null, "
            + COLONNE_PRENOM + " text not null, "
            + COLONNE_AGE + " integer not null, "
            + COLONNE_SEXE + " text not null, "
            + COLONNE_NIVEAU + " text not null, "
            + COLONNE_NUMLICENCE + " text primary key not null, "
            + COLONNE_PHOTO + " text) ";

    private static final String REQUETE_CREATION_BD_TABLEEQ = "create table " + TABLE_EQUIDE + " ( "
            + COLONNE_IDEQUIDE + " INTEGER primary key autoincrement, "
            + COLONNE_NOMCOMPLET + " TEXT not null, "
            + COLONNE_NOMECURIE + " text not null, "
            + COLONNE_AGEEQ + " integer not null, "
            + COLONNE_SEXEEQ + " text not null, "
            + COLONNE_RACE + " text not null, "
            + COLONNE_ROBE + " text, "
            + COLONNE_NUMSIRE + " integer, "
            + COLONNE_PROPRIETAIRE + " text not null) ";

    private static final String REQUETE_CREATION_BD_TABLEENG = "create table " + TABLE_ENGAGEMENT + " ( "
            + COLONNE_IDENGAGEMENT + " INTEGER primary key autoincrement, "
            + COLONNE_NUMENGAGEMENT + " INTEGER NOT NULL, "
            + COLONNE_LIEUXCONCOURS + " TEXT NOT NULL, "
            + COLONNE_DISCIPLINE + " TEXT NOT NULL, "
            + COLONNE_CATEGORIE + " TEXT NOT NULL, "
            + COLONNE_NOMCONCOURS + " TEXT NOT NULL, "
            + COLONNE_CLASSEMENT + " INTEGER, "
            + COLONNE_NUMLICENCECONCOURS + " TEXT NOT NULL, "
            + COLONNE_IDCHEVAL + " INTEGER NOT NULL, "
            + "FOREIGN KEY(" + COLONNE_NUMLICENCECONCOURS + ") REFERENCES "+ TABLE_CAVALIER + " (" + COLONNE_NUMLICENCE + "), "
            + "FOREIGN KEY(" + COLONNE_IDCHEVAL + ") REFERENCES "+ TABLE_EQUIDE + " (" + COLONNE_IDEQUIDE + ")) ";


    private static final String REQUETE_CREATION= REQUETE_CREATION_BD_TABLEEQ + REQUETE_CREATION_DB_TABLECAV + REQUETE_CREATION_BD_TABLEENG;

    private static SQLiteDatabase bCavalier;



    public CavalierDbOpenHelper(Context context) {
        super(context, BASE_NOM, null, BASE_VERSION);
    }


    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(REQUETE_CREATION_BD_TABLEEQ);
        db.execSQL(REQUETE_CREATION_BD_TABLEENG);
        db.execSQL(REQUETE_CREATION_DB_TABLECAV);
        db.execSQL(REQUETE_CREATION_BD_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_EQUIDE + ";");
        db.execSQL("drop table if exists " + TABLE_EQUIDE + ";");
        db.execSQL("drop table if exists " + TABLE_USER + ";");
        db.execSQL("drop table if exists " + TABLE_ENGAGEMENT + ";");
        onCreate(db);
    }

    public void insertValuesCavalier(EditText nom, EditText prenom, EditText age, EditText sexe, EditText niveau, EditText numLicence){
        ContentValues valeur = new ContentValues();

        valeur.put(CavalierDbOpenHelper.COLONNE_NOM, nom.getText().toString());
        valeur.put(CavalierDbOpenHelper.COLONNE_PRENOM, prenom.getText().toString());
        valeur.put(CavalierDbOpenHelper.COLONNE_AGE, age.getText().toString());
        valeur.put(CavalierDbOpenHelper.COLONNE_SEXE, sexe.getText().toString());
        valeur.put(CavalierDbOpenHelper.COLONNE_NIVEAU, niveau.getText().toString());
        valeur.put(CavalierDbOpenHelper.COLONNE_NUMLICENCE, numLicence.getText().toString());

        getWritableDatabase().insert(CavalierDbOpenHelper.TABLE_CAVALIER, null, valeur);
    }

    public void insertValueEquide(EditText nomComplet, EditText nomEcurie, EditText age, EditText sexe, EditText race, EditText robe, EditText propriétaire){
        ContentValues valeur = new ContentValues();

        valeur.put(CavalierDbOpenHelper.COLONNE_NOMCOMPLET, nomComplet.getText().toString());
        valeur.put(CavalierDbOpenHelper.COLONNE_NOMECURIE, nomEcurie.getText().toString());
        valeur.put(CavalierDbOpenHelper.COLONNE_AGEEQ, age.getText().toString());
        valeur.put(CavalierDbOpenHelper.COLONNE_SEXEEQ, sexe.getText().toString());
        valeur.put(CavalierDbOpenHelper.COLONNE_RACE, race.getText().toString());
        valeur.put(CavalierDbOpenHelper.COLONNE_ROBE, robe.getText().toString());
        valeur.put(CavalierDbOpenHelper.COLONNE_PROPRIETAIRE, propriétaire.getText().toString());

        getWritableDatabase().insert(CavalierDbOpenHelper.TABLE_EQUIDE, null, valeur);
    }

    public void insertValueEng(EditText numEngagement, EditText lieu, EditText discipline, EditText cat, EditText nom, EditText classement, String numL, int idCheval){
        ContentValues values = new ContentValues();

        values.put(CavalierDbOpenHelper.COLONNE_NUMENGAGEMENT, numEngagement.getText().toString());
        values.put(CavalierDbOpenHelper.COLONNE_LIEUXCONCOURS, lieu.getText().toString());
        values.put(CavalierDbOpenHelper.COLONNE_DISCIPLINE, discipline.getText().toString());
        values.put(CavalierDbOpenHelper.COLONNE_CATEGORIE, cat.getText().toString());
        values.put(CavalierDbOpenHelper.COLONNE_NOMCONCOURS, nom.getText().toString());
        values.put(CavalierDbOpenHelper.COLONNE_CLASSEMENT, classement.getText().toString());
        values.put(CavalierDbOpenHelper.COLONNE_NUMLICENCECONCOURS, numL);
        values.put(CavalierDbOpenHelper.COLONNE_IDCHEVAL, idCheval);

        getWritableDatabase().insert(CavalierDbOpenHelper.TABLE_ENGAGEMENT, null, values);

    }
    // procédure permettant l'enregistrement des données d'inscription dans la bdd
    public Boolean insertValue(EditText username, EditText password){
        ContentValues valeur = new ContentValues();

        valeur.put(CavalierDbOpenHelper.COLONNE_USERNAME, username.getText().toString());
        valeur.put(CavalierDbOpenHelper.COLONNE_PASSWORD, password.getText().toString());

        getWritableDatabase().insert(CavalierDbOpenHelper.TABLE_USER, null, valeur);
        return true;
    }

   public UserModele getAllUser(){
       UserModele user  = new UserModele();
       bCavalier = getReadableDatabase();

       Log.d("TEST", "Démarrage de la fonction");

       Cursor c = bCavalier.query(TABLE_USER,
               new String[]{COLONNE_USERNAME, COLONNE_PASSWORD},
               null,
               null, null , null, null);
       Log.d("TEST", "getUser: ce que retourne la requête");

       try {
           if (c != null && c.moveToFirst())
           {

               user.setUsername(c.getString(0));
               user.setPassword(c.getString(1));
               Log.d("TEST", "getUser: ce que retourne le ViewModele");

               return user;
           }
       } catch (Exception e) {
           e.printStackTrace();
       } finally {
           if (c != null)
               System.out.println("Cursor non vide");
               c.close();
       }
       return null;
   }

    public Cursor getByUser(){
        UserModele user  = new UserModele();
        bCavalier = getReadableDatabase();

        Log.d("TEST", "Démarrage de la fonction");


        Cursor c = bCavalier.query(TABLE_USER,
                new String[]{COLONNE_IDUSER,COLONNE_USERNAME, COLONNE_PASSWORD},
                null,
                null, null , null, null);
        Log.d("TEST", "getUser: ce que retourne la requête");

        return c;
    }

    public Boolean checkAccount(String user, String password){
        Cursor c = getByUser();
        Log.d("Check", user + " , " + password);
        int i;
        try {

            if (c != null && c.moveToFirst())

                Log.d("Check", "Cursor non vide");
            {
                for (i = 0; i < getByUser().getCount(); i++){
                    Log.d("Check", "La boucle commence");
                    if (c.getString(1).equals(user) && c.getString(2).equals(password)) {
                        Log.d("Check", "La boucle retourne quelque chose");
                        return true;
                    } else {
                        return false;
                    }
                }
                Log.d("Check", "La boucle se termine");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (c != null)
                System.out.println("Cursor va se fermer");
            c.close();
        }
        return false;
    }

    public List<User> getAllUsers(){
        ArrayList<User> userList = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USER+ ";",null);
        Log.d("List", "Requête prise en compte");

        if (cursor.moveToFirst()) {
            Log.d("List", "Début du curseur");
            do {
                User user = new User();
                user.setIdUser(cursor.getInt(0));
                user.setNom(cursor.getString(1));
                Log.d("List", user.getNom());
                user.setMdp(cursor.getString(2));
                Log.d("List", "fermeture du curseur");

                userList.add(user);

                Log.d("List", "Liste ajoutée" + userList.toString());
            } while (cursor.moveToNext());
            Log.d("List", "fermeture du curseur");
        }
        db.close();
        Log.d("List", "fermeture de la base de donnée");

        return userList;
    }

    public List<CavalierModele> getAllCavalier(){
        ArrayList<CavalierModele> cavalierList = new ArrayList<>();

        SQLiteDatabase db = CavalierDbOpenHelper.this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CAVALIER + ";",null);
        Log.d("List", "Requête prise en compte");

        if (cursor.moveToFirst()) {
            Log.d("List", "Début du curseur");
            do {
                CavalierModele cavalier = new CavalierModele();
                cavalier.setId(cursor.getInt(0));
                cavalier.setNomFamille(cursor.getString(1));
                Log.d("List", " Nom de famille du cavalier " + cavalier.getNomFamille());
                cavalier.setPrenom(cursor.getString(2));
                cavalier.setAge(cursor.getInt(3));
                cavalier.setSexe(cursor.getString(4));
                cavalier.setNiveau(cursor.getString(5));
                cavalier.setNumLicence(cursor.getString(6));
                Log.d("List", "fermeture du curseur");

                cavalierList.add(cavalier);

                Log.d("List", "Liste ajoutée : " + cavalierList.toString());
            } while (cursor.moveToNext());
            Log.d("List", "fermeture du curseur");
        }
        db.close();
        Log.d("List", "fermeture de la base de donnée");

        return cavalierList;
    }

    public CavalierModele getCavalierByName(String prenom) {
        List<CavalierModele> cavaliers = getAllCavalier();
        CavalierModele res = null;
        for (CavalierModele cavalier : cavaliers) {
            if (cavalier.getPrenom().equals(prenom)) {
                Log.d("Cavalier", "prenom trouvé" + cavalier.getPrenom());
                res = new CavalierModele();
                res.setId(cavalier.getId());
                res.setNomFamille(cavalier.getNomFamille());
                res.setPrenom(cavalier.getPrenom());
                res.setAge(cavalier.getAge());
                res.setSexe(cavalier.getSexe());
                res.setNiveau(cavalier.getNiveau());
                res.setNumLicence(cavalier.getNumLicence());
                Log.d("Cavalier", "Cavalier changer" + cavalier.toString());
                break;
            }
        }
        if (res == null) {
            Log.d("NULL", "Réponse nulle");
            return null;
        } else {
            return res;
        }

    }
}
