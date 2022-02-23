package fr.lajotsarthou.cavalier;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CavalierDbOpenHelper extends SQLiteOpenHelper {
    private static final int BASE_VERSION = 1;
    private static final String BASE_NOM = "cavalier";

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
    public static final int COLONNE_PROPRIETAIRE_ID = 8;

    //table 3 - enregistrement des engagements / résultats des compétitions
    private static final String TABLE_ENGAGEMENT = "table_engagement";
    public static final String COLONNE_IDENGAGEMENT = "idEngagement";
    public static final int COLONNE_IDENGAGEMENT_ID = 0;
    public static final String COLONNE_NUMENGAGEMENT = "numEngagement";
    public static final int COLONNE_NUMENGAGEMENT_ID = 1;
    public static final String COLONNE_LIEUXCONCOURS = "lieu";
    public static final int COLONNE_LIEUCONCOURS_ID = 2;
    public static final String COLONNE_DISCIPLINE = "discipline";
    public static final int COLONNE_DISCIPLINE_ID = 3;
    public static final String COLONNE_CATEGORIE = "categorie";
    public static final int COLONNE_CATEGORIE_ID = 4;
    public static final String COLONNE_NOMCONCOURS = "nomConcours";
    public static final int COLONNE_NOMCONCOURS_ID = 5;
    public static final String COLONNE_CLASSEMENT = "classement";
    public static final int COLONNE_CLASSEMENT_ID = 6;
    public static final String COLONNE_NUMLICENCECONCOURS = "numLicence";
    public static final int COLONNE_NUMLICENCECONCOURS_ID = 7;
    public static final String COLONNE_IDCHEVAL = "IdCheval";
    public static final int COLONNE_IDCHEVAL_ID = 8;
    public static final String FK_EQUIDE_ID = "fk_equide_id";
    public static final String FK_CAVALIER_NUMLICENCE = "fk_cavalier_numlicence";



    private static final String REQUETE_CREATION_DB_TABLECAV = "create table " + TABLE_CAVALIER + " ( "
            + COLONNE_IDCAVALIER + " integer autoincrement, "
            + COLONNE_NOM + " text not null, "
            + COLONNE_PRENOM + " text not null, "
            + COLONNE_AGE + " integer not null, "
            + COLONNE_SEXE + " text not null, "
            + COLONNE_NIVEAU + " text not null, "
            + COLONNE_NUMLICENCE + " text primary key not null, "
            + COLONNE_PHOTO + " text);";

    private static final String REQUETE_CREATION_BD_TABLEEQ = " create table " + TABLE_EQUIDE + " ( "
            + COLONNE_IDEQUIDE + " INTEGER primary key autoincrement, "
            + COLONNE_NOMCOMPLET + " TEXT not null, "
            + COLONNE_NOMECURIE + " text not null, "
            + COLONNE_AGEEQ + " integer not null, "
            + COLONNE_SEXEEQ + " text not null, "
            + COLONNE_RACE + " text not null, "
            + COLONNE_ROBE + " text, " + COLONNE_NUMSIRE + " integer, "
            + COLONNE_PROPRIETAIRE + " text not null);";

    private static final String REQUETE_CREATION_BD_TABLEENG = " create table " + TABLE_ENGAGEMENT + " ( "
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
            + "FOREIGN KEY(" + COLONNE_IDCHEVAL + ") REFERENCES "+ TABLE_EQUIDE + " (" + COLONNE_IDEQUIDE + "));";

    private static final String REQUETE_CREATION_BD = REQUETE_CREATION_BD_TABLEEQ + REQUETE_CREATION_DB_TABLECAV + REQUETE_CREATION_BD_TABLEENG;

    private SQLiteDatabase bCavalier;



    public CavalierDbOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(REQUETE_CREATION_BD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table " + TABLE_EQUIDE + ";");
        db.execSQL("drop table " + TABLE_EQUIDE + ";");
        db.execSQL(" drop table " + TABLE_ENGAGEMENT + ";");
        onCreate(db);
    }
}
