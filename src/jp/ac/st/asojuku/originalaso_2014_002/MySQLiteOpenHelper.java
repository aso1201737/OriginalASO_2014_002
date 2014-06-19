package jp.ac.st.asojuku.originalaso_2014_002;


import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

	/**
	 * @param context
	 * @param name
	 * @param factory
	 * @param version
	 */
	public MySQLiteOpenHelper (Context context) {

		super(context,"20140021201737.sqlite3",null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO 自動生成されたメソッド・スタブ
		db.execSQL("CREATE TABLE IF NOT EXISTS " +
				"Hitokoto ( _id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , phrase TEXT )");
	}


	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO 自動生成されたメソッド・スタブ
		db.execSQL("drop table Hitokoto");
		onCreate(db);
	}

	/**
	 * 引数のフレーズをHitokotoテーブルにインサートするプライベートメソッド
	 * @param SQLiteDatabase
	 * @param inputMsg
	 */
	public void insertHikotoko(SQLiteDatabase db, String inputMsg){

		String sqlstr = "insert into Hitokoto (phrase) values('" + inputMsg + "');";
			try {
				db.beginTransaction();
				db.execSQL(sqlstr);
				db.setTransactionSuccessful();
			} catch (SQLException e){
				Log.e("ERROR",e.toString());
			}finally {
				db.endTransaction();
			}
		return;
	}


		/**
		 * 引数のフレーズをHitokotoテーブルにインサートするプライベートメソッド
		 * @param SQLiteDatabase
		 * @param inputMsg
		 */
		public String selectRandomHitokoto(SQLiteDatabase db){

			String rtString = null;

			String sqlstr = " SELECT _id, phrase FROM Hitokoto ORDER BY RANDOM(); ";
					try {
						SQLiteCursor cursor = (SQLiteCursor)db.rawQuery(sqlstr,  null);
						if(cursor.getCount()!=0){
							cursor.moveToFirst();
							rtString = cursor.getString(1);
						}
						cursor.close();
					} catch (SQLException e) {
						Log.e("ERROR", e.toString());
					}finally {

				}
			return rtString;
		}


			/**
			* 引数のフレーズをHitokotoテーブルにインサートするプライベートメソッド
			* @param SQLiteDatabase
			* @param inputMsg
			*/
			public SQLiteCursor selectHitokotoLite(SQLiteDatabase db){

				SQLiteCursor cursor = null;

				String sqlstr = " SELECT _id, phrase FROM Hitokoto ORDER BY _id; ";
			try {
				cursor = (SQLiteCursor)db.rawQuery(sqlstr,  null);
				if(cursor.getCount()!=0){
					cursor.moveToFirst();}
				}

				catch (SQLException e) {
				Log.e("ERROR", e.toString());
			}finally {

			}
			return cursor;
		}
	
		/**
		 * 引数のフレーズをHitokotoテーブルにインサートするプライベートメソッド
		 * @param SQLiteDatabase
		 * @param inputMsg
		 */
		public void deleteHitokoto(SQLiteDatabase db, int id){

			String sqlstr = " DELETE FROM Hitokoto where _id = " + id + " ;";
		try {
			db.beginTransaction();
			db.execSQL(sqlstr);
			db.setTransactionSuccessful();
		}
			catch (SQLException e) {
			Log.e("ERROR", e.toString());
			}finally {

			}
			db.endTransaction();;
		}
	}
