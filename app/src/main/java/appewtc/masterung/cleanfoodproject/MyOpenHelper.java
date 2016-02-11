package appewtc.masterung.cleanfoodproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by masterUNG on 2/11/16 AD.
 */
public class MyOpenHelper extends SQLiteOpenHelper{

    //Explicit
    public static final String database_name = "food.db";
    private static final int database_version = 1;

    private static final String create_userTABLE = "create table userTABLE (" +
            "_id integer primary key, " +
            "User text, " +
            "Password text, " +
            "Name text);";

    private static final String create_recipeTABLE = "create table recipeTABLE (" +
            "_id integer primary key, " +
            "Recipe text, " +
            "Ingredients text, " +
            "HowTo text, " +
            "Descrip text, " +
            "ImageRecipe text, " +
            "NameComment text, " +
            "Comment text);";

    private static final String create_restaurantTABLE = "create table restaurantTABLE (" +
            "_id integer primary key, " +
            "Restaurant text, " +
            "ImageRestaurant text, " +
            "Phone text, " +
            "Address text, " +
            "Web text, " +
            "Lat text, " +
            "Lng text);";


    public MyOpenHelper(Context context) {
        super(context, database_name, null, database_version);
    }   // Constructor

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(create_recipeTABLE);
        sqLiteDatabase.execSQL(create_restaurantTABLE);
        sqLiteDatabase.execSQL(create_userTABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}   // Main Class
