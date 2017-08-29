package com.example.gameapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by vudinhai on 6/23/17.
 */

public class DBHelper {
    String DATABASE_NAME = "GameDatabase.sqlite";
    private static final String DB_PATH_SUFFIX = "/databases/";
    SQLiteDatabase db = null;

    Context context;

    public DBHelper(Context context) {
        this.context = context;

        processSQLite();
    }

    private void processSQLite() {
        File dbFile = context.getDatabasePath(DATABASE_NAME);
        if(!dbFile.exists()){
            try{
                CopyDatabaseFromAsset();
                Toast.makeText(context, "Load successful !!!", Toast.LENGTH_SHORT).show();

            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

    private void CopyDatabaseFromAsset() {
        try{
            InputStream databaseInputStream = context.getAssets().open(DATABASE_NAME);

            String outputStream = getPathDatabaseSystem();

            File file = new File(context.getApplicationInfo().dataDir + DB_PATH_SUFFIX);
            if(!file.exists()){
                file.mkdir();
            }

            OutputStream databaseOutputStream = new FileOutputStream(outputStream);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = databaseInputStream.read(buffer)) > 0){
                databaseOutputStream.write(buffer,0,length);
            }


            databaseOutputStream.flush();
            databaseOutputStream.close();
            databaseInputStream.close();

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private String getPathDatabaseSystem() {
        return context.getApplicationInfo().dataDir + DB_PATH_SUFFIX + DATABASE_NAME;
    }


    public ArrayList<Game> getAllGame(){
        ArrayList<Game> arrayList = new ArrayList<>();

        db = context.openOrCreateDatabase(DATABASE_NAME,context.MODE_PRIVATE,null);

        String sql = "SELECT * FROM Game";

        Cursor cursor = db.rawQuery(sql,null);

        while (cursor.moveToNext()){

            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String description = cursor.getString(2);
            String image = cursor.getString(3);
            float rating = cursor.getFloat(4);
            String date = cursor.getString(5);
            String platform = cursor.getString(6);
            String dev = cursor.getString(7);
            String trailer = cursor.getString(8);
            String imgrv = cursor.getString(9);
            String rv = cursor.getString(10);

            arrayList.add(new Game(id, name, description, image, rating, date, platform, dev, trailer,
                    imgrv, rv));
        }
        return arrayList;
    }

    public ArrayList<Game> getPS4Games() {
        ArrayList<Game> arrayList = new ArrayList<>();
        db = context.openOrCreateDatabase(DATABASE_NAME, context.MODE_PRIVATE, null);
        String sql = "SELECT * FROM Game WHERE Platform = 'PS4'";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String description = cursor.getString(2);
            String image = cursor.getString(3);
            float rating = cursor.getFloat(4);
            String date = cursor.getString(5);
            String platform = cursor.getString(6);
            String dev = cursor.getString(7);

            String trailer = cursor.getString(8);

            String imgrv = cursor.getString(9);
            String rv = cursor.getString(10);

            arrayList.add(new Game(id, name, description, image, rating, date, platform, dev, trailer,
                    imgrv, rv));
        }
        return arrayList;
    }

        public ArrayList<Game> getPCGames() {
            ArrayList<Game> arrayList = new ArrayList<>();
            db = context.openOrCreateDatabase(DATABASE_NAME, context.MODE_PRIVATE, null);

            String sql = "SELECT * FROM Game WHERE Platform = 'PC'";
            Cursor cursor = db.rawQuery(sql,null);
            while (cursor.moveToNext()){

                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String description = cursor.getString(2);
                String image = cursor.getString(3);
                float rating = cursor.getFloat(4);
                String date = cursor.getString(5);
                String platform = cursor.getString(6);
                String dev = cursor.getString(7);

                String trailer = cursor.getString(8);

                String imgrv = cursor.getString(9);
                String rv = cursor.getString(10);

                arrayList.add(new Game(id, name, description, image, rating, date, platform, dev, trailer,
                        imgrv, rv));
            }
            return arrayList;
        }

    public ArrayList<Game> getPSVITAGames() {
        ArrayList<Game> arrayList = new ArrayList<>();
        db = context.openOrCreateDatabase(DATABASE_NAME, context.MODE_PRIVATE, null);

        String sql = "SELECT * FROM Game WHERE Platform = 'PSVITA'";
        Cursor cursor = db.rawQuery(sql,null);
        while (cursor.moveToNext()){

            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String description = cursor.getString(2);
            String image = cursor.getString(3);
            float rating = cursor.getFloat(4);
            String date = cursor.getString(5);
            String platform = cursor.getString(6);
            String dev = cursor.getString(7);

            String trailer = cursor.getString(8);

            String imgrv = cursor.getString(9);
            String rv = cursor.getString(10);

            arrayList.add(new Game(id, name, description, image, rating, date, platform, dev, trailer,
                    imgrv, rv));
        }
        return arrayList;
    }

    public ArrayList<Game> getXB1Games() {
        ArrayList<Game> arrayList = new ArrayList<>();
        db = context.openOrCreateDatabase(DATABASE_NAME, context.MODE_PRIVATE, null);

        String sql = "SELECT * FROM Game WHERE Platform = 'XBOX One'";
        Cursor cursor = db.rawQuery(sql,null);
        while (cursor.moveToNext()){

            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String description = cursor.getString(2);
            String image = cursor.getString(3);
            float rating = cursor.getFloat(4);
            String date = cursor.getString(5);
            String platform = cursor.getString(6);
            String dev = cursor.getString(7);

            String trailer = cursor.getString(8);

            String imgrv = cursor.getString(9);
            String rv = cursor.getString(10);

            arrayList.add(new Game(id, name, description, image, rating, date, platform, dev, trailer,
                    imgrv, rv));
        }
        return arrayList;
    }

    public ArrayList<Game> getClassicGames() {
        ArrayList<Game> arrayList = new ArrayList<>();
        db = context.openOrCreateDatabase(DATABASE_NAME, context.MODE_PRIVATE, null);

        String sql = "SELECT * FROM Game WHERE Platform = 'PS2'";
        Cursor cursor = db.rawQuery(sql,null);
        while (cursor.moveToNext()){

            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String description = cursor.getString(2);
            String image = cursor.getString(3);
            float rating = cursor.getFloat(4);
            String date = cursor.getString(5);
            String platform = cursor.getString(6);
            String dev = cursor.getString(7);

            String trailer = cursor.getString(8);

            String imgrv = cursor.getString(9);
            String rv = cursor.getString(10);

            arrayList.add(new Game(id, name, description, image, rating, date, platform, dev, trailer,
                    imgrv, rv));
        }
        return arrayList;
    }



    public void insertStudent(Game game){
        db = context.openOrCreateDatabase(DATABASE_NAME,context.MODE_PRIVATE,null);

        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", game.getID());
        contentValues.put("Name", game.getName());
        contentValues.put("Description", game.getDescription());
        contentValues.put("Image", game.getImage());
        contentValues.put("Rating", game.getRating());
        contentValues.put("Date", game.getDate());
        contentValues.put("Platform", game.getPlatform());

        if(db.insert("Game",null,contentValues) > 0){
            Toast.makeText(context, "Insert Successfully!!!", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateStudent(Game game){
        db = context.openOrCreateDatabase(DATABASE_NAME,context.MODE_PRIVATE,null);

        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", game.getID());
        contentValues.put("Name", game.getName());
        contentValues.put("Description", game.getDescription());
        contentValues.put("Image", game.getImage());
        contentValues.put("Rating", game.getRating());
        contentValues.put("Date", game.getDate());
        contentValues.put("Platform", game.getPlatform());

        if(db.update("Game",contentValues,"ID = "+ game.getID(),null) > 0){
            Toast.makeText(context, "Update Successfully!!!", Toast.LENGTH_SHORT).show();
        }
    }


    public void deleteStudent(int id){
        db = context.openOrCreateDatabase(DATABASE_NAME,context.MODE_PRIVATE,null);

        if(db.delete("Game","ID=" +id,null) > 0){
            Toast.makeText(context, "Update Successfully!!!", Toast.LENGTH_SHORT).show();
        }
    }
}
