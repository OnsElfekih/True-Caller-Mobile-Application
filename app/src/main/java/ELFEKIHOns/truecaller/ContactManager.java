package ELFEKIHOns.truecaller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ContactManager {
    SQLiteDatabase db = null;
    Context con;

    public ContactManager(Context con) {
        this.con = con;
    }

    public void Ouvrir() {
        ContactHelper helper = new ContactHelper(con, "contacts.db", null, 1);
        db = helper.getWritableDatabase();
    }

    public long ajout(String first, String last, String phone) {
        ContentValues values = new ContentValues();
        values.put(ContactHelper.col_first, first);
        values.put(ContactHelper.col_last, last);
        values.put(ContactHelper.col_phone, phone);
        values.put(ContactHelper.col_fav, 0); // Par défaut non favori
        return db.insert(ContactHelper.table_contacts, null, values);
    }

    public ArrayList<Contact> getAllContact() {
        return getContactByValue(null);
    }

    public ArrayList<Contact> getContactByValue(String val) {
        ArrayList<Contact> l = new ArrayList<>();
        String selection = null;
        String[] selectionArgs = null;

        if (val != null && !val.isEmpty()) {
            selection = ContactHelper.col_first + " LIKE ? OR " +
                        ContactHelper.col_last + " LIKE ? OR " +
                        ContactHelper.col_phone + " LIKE ?";
            selectionArgs = new String[]{"%" + val + "%", "%" + val + "%", "%" + val + "%"};
        }

        Cursor cr = db.query(ContactHelper.table_contacts,
                new String[]{ContactHelper.col_id,
                        ContactHelper.col_first,
                        ContactHelper.col_last,
                        ContactHelper.col_phone,
                        ContactHelper.col_fav}, // Lecture de la colonne favori
                selection, selectionArgs, null, null, null);

        if (cr.moveToFirst()) {
            do {
                int id = cr.getInt(0);
                String first = cr.getString(1);
                String last = cr.getString(2);
                String phone = cr.getString(3);
                int isFav = cr.getInt(4); // Récupération de l'état favori
                l.add(new Contact(id, first, last, phone, isFav));
            } while (cr.moveToNext());
        }
        cr.close();
        return l;
    }

    public int changerFavori(int id, int nouvelEtat) {
        ContentValues values = new ContentValues();
        values.put(ContactHelper.col_fav, nouvelEtat);
        return db.update(ContactHelper.table_contacts, values, 
                         ContactHelper.col_id + "=?", new String[]{String.valueOf(id)});
    }

    public int supprimer(int id) {
        return db.delete(ContactHelper.table_contacts, ContactHelper.col_id + "=?", new String[]{String.valueOf(id)});
    }

    public int modifier(int id, String first, String last, String phone) {
        ContentValues values = new ContentValues();
        values.put(ContactHelper.col_first, first);
        values.put(ContactHelper.col_last, last);
        values.put(ContactHelper.col_phone, phone);
        return db.update(ContactHelper.table_contacts, values, ContactHelper.col_id + "=?", new String[]{String.valueOf(id)});
    }

    public void fermer() {
        if (db != null && db.isOpen()) {
            db.close();
        }
    }
}
