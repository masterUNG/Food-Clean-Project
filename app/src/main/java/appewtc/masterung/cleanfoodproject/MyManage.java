package appewtc.masterung.cleanfoodproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by masterUNG on 2/11/16 AD.
 */
public class MyManage {

    //Explicit
    private MyOpenHelper objMyOpenHelper;
    private SQLiteDatabase writeSqLiteDatabase, readSqLiteDatabase;

    public static final String table_user = "userTABLE";
    public static final String table_recipe = "recipeTABLE";
    public static final String table_restaurant = "restaurantTABLE";

    public static final String column__id = "_id";
    public static final String column_User = "User";
    public static final String column_Password = "Password";
    public static final String column_Name = "Name";
    public static final String column_Recipe = "Recipe";
    public static final String column_Ingredients = "Ingredients";
    public static final String column_HowTo = "HowTo";
    public static final String column_Descrip = "Descrip";
    public static final String column_ImageRecipe = "ImageRecipe";
    public static final String column_NameComment = "NameComment";
    public static final String column_Comment = "Comment";
    public static final String column_Restaurant = "Restaurant";
    public static final String column_ImageRestaurant = "ImageRestaurant";
    public static final String column_Phone = "Phone";
    public static final String column_Address = "Address";
    public static final String column_Web = "Web";
    public static final String column_Lat = "Lat";
    public static final String column_Lng = "Lng";

    public MyManage(Context context) {

        //Connected Database
        objMyOpenHelper = new MyOpenHelper(context);
        writeSqLiteDatabase = objMyOpenHelper.getWritableDatabase();
        readSqLiteDatabase = objMyOpenHelper.getReadableDatabase();

    }   // Constructor

    public long addRestaurant(String strRestaurant,
                              String strImage,
                              String strPhone,
                              String strAddress,
                              String strWeb,
                              String strLat,
                              String strLng) {

        ContentValues objContentValues = new ContentValues();
        objContentValues.put(column_Restaurant, strRestaurant);
        objContentValues.put(column_ImageRestaurant, strImage);
        objContentValues.put(column_Phone, strPhone);
        objContentValues.put(column_Address, strAddress);
        objContentValues.put(column_Web, strWeb);
        objContentValues.put(column_Lat, strLat);
        objContentValues.put(column_Lng, strLng);

        return writeSqLiteDatabase.insert(table_restaurant, null, objContentValues);
    }


    public long addrecipe(String strRecipe,
                          String strIngredient,
                          String strHowTo,
                          String strDescrip,
                          String strImage,
                          String strName,
                          String strComment) {

        ContentValues objContentValues = new ContentValues();
        objContentValues.put(column_Recipe, strRecipe);
        objContentValues.put(column_Ingredients, strIngredient);
        objContentValues.put(column_HowTo, strHowTo);
        objContentValues.put(column_Descrip, strDescrip);
        objContentValues.put(column_ImageRecipe, strImage);
        objContentValues.put(column_NameComment, strName);
        objContentValues.put(column_Comment, strComment);

        return writeSqLiteDatabase.insert(table_recipe, null, objContentValues);
    }

    public long addUser(String strUser,
                        String strPassword,
                        String strName) {

        ContentValues objContentValues = new ContentValues();
        objContentValues.put(column_User, strUser);
        objContentValues.put(column_Password, strPassword);
        objContentValues.put(column_Name, strName);

        return writeSqLiteDatabase.insert(table_user, null, objContentValues);
    }

}   // Main Class
