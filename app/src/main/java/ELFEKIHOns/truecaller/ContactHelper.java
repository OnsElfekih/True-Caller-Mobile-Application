package ELFEKIHOns.truecaller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ContactHelper extends SQLiteOpenHelper {
    public static final String table_contacts = "Contacts";
    public static final String col_id = "ID";
    public static final String col_first = "First_Name";
    public static final String col_last = "Last_Name";
    public static final String col_phone = "Phone";

    String requete = "CREATE TABLE " + table_contacts + " ("
            + col_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + col_first + " TEXT NOT NULL, "
            + col_last + " TEXT NOT NULL, "
            + col_phone + " TEXT NOT NULL);";

    public ContactHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(requete);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + table_contacts);
        onCreate(sqLiteDatabase);
    }
}
