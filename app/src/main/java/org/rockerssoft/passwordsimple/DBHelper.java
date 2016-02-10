package org.rockerssoft.passwordsimple;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 103instructor on 2/9/2016.
 */
public class DBHelper extends SQLiteOpenHelper {

	public static final String DATABASE = "Userlist.db";
	public static final String TABLE = "logins";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_PASS = "password";

	public DBHelper(Context context){ super(context, DATABASE, null, 1);}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table " + TABLE +
			"(id integer primary key," +
			COLUMN_NAME + " text," +
			COLUMN_PASS + " text)");

		insertUser("bobby","isawesome");
		insertUser("katherine", "isnotfeelingwell");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE);
		onCreate(db);
	}

	public boolean insertUser(String name, String password){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cValues = new ContentValues();
		cValues.put(COLUMN_NAME, name);
		cValues.put(COLUMN_PASS, password);
		db.insert(TABLE, null, cValues);
		return true;
	}

	public String getPassFromUser(String name) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor res = db.rawQuery("select * " +
			"from " + TABLE +
			"where " + COLUMN_NAME +
			"=" + name + "", null);

		res.moveToFirst();
		return res.getString(res.getColumnIndex(COLUMN_PASS));
	}
}
