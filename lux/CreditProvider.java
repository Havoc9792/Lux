package gpapp.hku.lux;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

public class CreditProvider extends ContentProvider{

	private static final int accuracy = 1;
	private static final int play_ID = 2;
	
	private static final UriMatcher u_matcher;
	static
	{
		u_matcher = new UriMatcher(UriMatcher.NO_MATCH);
		u_matcher.addURI(CreditContract.authority, CreditContract.Credits.path, accuracy);
		u_matcher.addURI(CreditContract.authority, CreditContract.Credits.path + "/#", play_ID);
	}
	
	private static String LOG_TAG = "lux";
	private SQLiteDatabase lux_db;
	
	private static final String DATABASE_NAME = "play_record";
	private static final String DATABASE_TABLE = "AverageHit";
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_CREATE = 
			"create table " + DATABASE_TABLE + " (" + CreditContract.Credits._ID + " integer primary key autoincrement, " + CreditContract.Credits.Acc + " text not null, " + CreditContract.Credits.cID + " text not null);";
	
	private static class DatabaseHelper extends SQLiteOpenHelper{
		DatabaseHelper(Context context){
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase arg0) {
			// TODO Auto-generated method stub
			arg0.execSQL(DATABASE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
			// TODO Auto-generated method stub
			Log.w(LOG_TAG, "upgrading database from" + arg1 + " to " + arg2);
			arg0.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(arg0);
		}
	}
	
	@Override
	public int delete(Uri arg0, String arg1, String[] arg2) {
		// TODO Auto-generated method stub
		int count=0;
		switch(u_matcher.match(arg0)){
		case accuracy:
			count = lux_db.delete(DATABASE_TABLE, arg1, arg2);
			break;
		case play_ID:
			String id = arg0.getPathSegments().get(1);
			count = lux_db.delete(DATABASE_TABLE, CreditContract.Credits._ID + " = " + id + (!TextUtils.isEmpty(arg1)?" AND (" + arg1 + ")" : ""), arg2);
			break;
		default:
			throw new IllegalArgumentException("unknow uri" + arg0);
		}
		getContext().getContentResolver().notifyChange(arg0, null);
		return count;
	}

	@Override
	public String getType(Uri arg0) {
		// TODO Auto-generated method stub
		switch(u_matcher.match(arg0)){
		case accuracy:
			return CreditContract.Credits.CONTENT_DIR_TYPE;
		case play_ID:
			return CreditContract.Credits.CONETENT_ITEM_TYPE;
		default:
			throw new IllegalArgumentException("unsupported uri: " + arg0);
		}
	}

	@Override
	public Uri insert(Uri arg0, ContentValues arg1) {
		// TODO Auto-generated method stub
		long row_id = lux_db.insert(DATABASE_TABLE, "", arg1);
		if(row_id>0){
			Uri _uri = ContentUris.withAppendedId(CreditContract.Credits.CONTENT_URI, row_id);
			return _uri;
		}
		else{
			throw new SQLException("failed to insert " + arg0);
		}
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		Context context = getContext();
		DatabaseHelper db_helper = new DatabaseHelper(context);
		lux_db = db_helper.getWritableDatabase();
		return (lux_db == null?false:true);
	}

	@Override
	public Cursor query(Uri arg0, String[] arg1, String arg2, String[] arg3,
			String arg4) {
		// TODO Auto-generated method stub
		SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
		builder.setTables(DATABASE_TABLE);
		
		if(u_matcher.match(arg0)==play_ID){
			builder.appendWhere(CreditContract.Credits._ID + " = " + arg0.getPathSegments().get(1));
		}
		if(arg4 == null || arg4 ==""){
				arg4 = CreditContract.Credits.Acc;
		}
		
		Cursor cursor = builder.query(lux_db, arg1, arg2, arg3, null, null, arg4);
		cursor.setNotificationUri(getContext().getContentResolver(), arg0);
		return cursor;
	}

	@Override
	public int update(Uri arg0, ContentValues arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		int count=0;
		switch(u_matcher.match(arg0)){
		case accuracy:
			count = lux_db.update(DATABASE_TABLE, arg1, arg2, arg3);
			break;
		case play_ID:
			count = lux_db.update(DATABASE_TABLE, arg1, CreditContract.Credits._ID + " = " + arg0.getPathSegments().get(1) + (!TextUtils.isEmpty(arg2)?" AND (" + arg2 + ")":""), arg3);
			
		default:
			throw new IllegalArgumentException("unknownn uri: " + arg0);
		}
		getContext().getContentResolver().notifyChange(arg0, null);
		return count;
	} 

}
