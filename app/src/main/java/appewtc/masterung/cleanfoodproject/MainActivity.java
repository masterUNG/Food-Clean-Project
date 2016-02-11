package appewtc.masterung.cleanfoodproject;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    //Explicit
    private MyManage objMyManage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create & Connected
        objMyManage = new MyManage(this);

        //Test Add Value
        //testAddValue();

        //Delete All SQLite
        deleteAllSQLite();


    }   // Main Method

    public void clickSignUpMain(View view) {

        startActivity(new Intent(MainActivity.this, RegisterActivity.class));

    }   // clickSignUpMain

    private void deleteAllSQLite() {
        SQLiteDatabase objSqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                MODE_PRIVATE, null);
        objSqLiteDatabase.delete(MyManage.table_user, null, null);
        objSqLiteDatabase.delete(MyManage.table_recipe, null, null);
        objSqLiteDatabase.delete(MyManage.table_restaurant, null, null);
    }

    private void testAddValue() {

        objMyManage.addUser("user", "password", "name");
        objMyManage.addrecipe("recipe", "ingredient", "how to", "descrip", "image",
                "name", "comment");
        objMyManage.addRestaurant("Res", "Image", "Phone", "Address",
                "Web", "Lat", "Lng");

    }   // testAddValue

}   // Main Class
