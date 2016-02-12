package appewtc.masterung.cleanfoodproject;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

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

        //Synchronize JSON To SQLite
        synJSONtoSQLite();


    }   // Main Method

    private void synJSONtoSQLite() {

        //Change Policy
        StrictMode.ThreadPolicy myPolicy = new StrictMode.ThreadPolicy
                .Builder().permitAll().build();
        StrictMode.setThreadPolicy(myPolicy);

        int intTABLE = 0;
        while (intTABLE <= 2) {

            //1. InputStream
            InputStream objInputStream = null;
            String[] urlStrings = new String[3];
            urlStrings[0] = "http://swiftcodingthai.com/tan/php_get_user_master.php";
            urlStrings[1] = "http://swiftcodingthai.com/tan/php_get_recipe_master.php";
            urlStrings[2] = "http://swiftcodingthai.com/tan/php_get_restaurant_master.php";
            String tag = "CleanFood";

            try {

                HttpClient objHttpClient = new DefaultHttpClient();
                HttpPost objHttpPost = new HttpPost(urlStrings[intTABLE]);
                HttpResponse objHttpResponse = objHttpClient.execute(objHttpPost);
                HttpEntity objHttpEntity = objHttpResponse.getEntity();
                objInputStream = objHttpEntity.getContent();

            } catch (Exception e) {
                Log.d(tag, "InputStream ==> " + e.toString());
            }

            //2. JSON String
            String strJSON = null;
            try {

                BufferedReader objBufferedReader = new BufferedReader(new InputStreamReader(objInputStream, "UTF-8"));
                StringBuilder objStringBuilder = new StringBuilder();
                String strLine = null;

                while ((strLine = objBufferedReader.readLine()) != null) {
                    objStringBuilder.append(strLine);
                }   //while

                objInputStream.close();
                strJSON = objStringBuilder.toString();

            } catch (Exception e) {
                Log.d(tag, "strJSON ==> " + e.toString());
            }

            //3. Update SQLite
            try {

                JSONArray objJsonArray = new JSONArray(strJSON);
                for (int i=0;i<objJsonArray.length();i++) {

                    JSONObject jsonObject = objJsonArray.getJSONObject(i);

                    switch (intTABLE) {
                        case 0:
                            //userTABlE
                            String strUser = jsonObject.getString(MyManage.column_User);
                            String strPassword = jsonObject.getString(MyManage.column_Password);
                            String strName = jsonObject.getString(MyManage.column_Name);
                            objMyManage.addUser(strUser, strPassword, strName);

                            break;
                        case 1:
                            //recipeTABLE
                            String strRecipe = jsonObject.getString(MyManage.column_Recipe);
                            String strIngredient = jsonObject.getString(MyManage.column_Ingredients);
                            String strHowTo = jsonObject.getString(MyManage.column_HowTo);
                            String strDescrip = jsonObject.getString("Description");
                            String strImage = jsonObject.getString(MyManage.column_ImageRecipe);
                            String strNameComment = jsonObject.getString(MyManage.column_NameComment);
                            String strComment = jsonObject.getString(MyManage.column_Comment);
                            objMyManage.addrecipe(strRecipe, strIngredient,
                                    strHowTo, strDescrip, strImage, strNameComment, strComment);

                            break;
                        case 2:
                            //restaurantTABLE
                            String strRestaurant = jsonObject.getString(MyManage.column_Restaurant);
                            String strImageRestaurant = jsonObject.getString(MyManage.column_ImageRestaurant);
                            String strPhone = jsonObject.getString(MyManage.column_Phone);
                            String strAddress = jsonObject.getString(MyManage.column_Address);
                            String strWeb = jsonObject.getString("Website");
                            String strLat = jsonObject.getString(MyManage.column_Lat);
                            String strLng = jsonObject.getString(MyManage.column_Lng);
                            objMyManage.addRestaurant(strRestaurant, strImageRestaurant,
                                    strPhone, strAddress, strWeb, strLat, strLng);

                     break;
                    }
                }   // for

            } catch (Exception e) {
                Log.d(tag, "Update ==> " + e.toString());
            }


            intTABLE += 1;
        }   // while


    }   // synJSONtoSQLite

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
