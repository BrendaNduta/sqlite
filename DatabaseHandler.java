package innovaters.com.sqlitelab2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brenda on 10/25/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION= 1;
    private static final String DATABASE_NAME="contactsmanager";
    private static final String TABLE_CONTACTS="contacts";
    private static final String TABLE_EMAILS= "emails";
    private static final String KEY_ID ="id";
    private static final String KEY_NAME ="name";
    private static final String KEY_PH_NO ="phone_number";
    private static final String KEY_EMAIL ="emails";
public  DatabaseHandler (Context context)
{
    super(context, DATABASE_NAME, null, DATABASE_VERSION);

}
    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE" + TABLE_CONTACTS + "(" + KEY_ID  + " INTEGER PRIMARY KEY, " + KEY_NAME + "" +
                " TEXT," + KEY_PH_NO + " TEXT" + ")";
        String CREATE_EMAILS_TABLE = "CREATE TABLE" + TABLE_EMAILS + "(" + KEY_ID  + " INTEGER PRIMARY KEY, " + KEY_NAME + "" +
                " TEXT," + KEY_PH_NO + " TEXT" + KEY_EMAIL + " TEXT" + ")";

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
     db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMAILS);

        onCreate(db);
    }
        //table contact
    public void addContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values= new ContentValues();
        values.put( KEY_NAME, contact.getName());
        values.put( KEY_PH_NO, contact.getPhone_number());

        db.insert(TABLE_CONTACTS, null, values);
        db.close();
    }

    public Contact getContact(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID, KEY_NAME, KEY_PH_NO,  }, KEY_ID + "=?", new String[]
                { String.valueOf(id) }, null, null, null, null);
        if (cursor != null);
        cursor.moveToFirst();

        Contact contact = new Contact(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));
        return  contact;

    }
     public List<Contact> getAllContact() {
         List<Contact> contactList = new ArrayList<>();
         String selectQuery = "select * from " + TABLE_CONTACTS;
         SQLiteDatabase db = this.getWritableDatabase();
         Cursor cursor = db.rawQuery(selectQuery, null);

         if(cursor.moveToFirst()) {
             do {
                 Contact contact = new Contact();
                 contact.setId(Integer.parseInt(cursor.getString(0)));
                 contact.setName(cursor.getString(1));
                 contact.setPhone_number(cursor.getString(2));
                 contactList.add(contact);
             } while (cursor.moveToNext());
         }
         return contactList;
     }

    public int getContactsCount (){
        String countQuery = "select * from " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }

    public int updateContact (Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PH_NO, contact.getPhone_number());

        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?", new String[] { String.valueOf(contact.getId())});
    }
    public void deleteContact (Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?", new String[] {String.valueOf(contact.getId())});
        db.close();
    }


    //table email
    public void addEmail(Contact emails){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values= new ContentValues();
        values.put( KEY_NAME, emails.getName());
        values.put( KEY_PH_NO, emails.getPhone_number());
        values.put( KEY_EMAIL, emails.getPhone_number());


        db.insert(TABLE_EMAILS, null, values);
        db.close();
    }

    public Contact getEmails(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_EMAILS, new String[] { KEY_ID, KEY_NAME, KEY_EMAIL}, KEY_ID + "=?",
                new String[]{ String.valueOf(id)}, null, null, null);
        if(cursor != null) {
            cursor.moveToFirst();
        }

        Contact emails = new Contact(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));
        return emails;
    }
    public List<Contact> getAllEmails() {
        List<Contact> contactList = new ArrayList<>();
        String selectQuery = "select * from " + TABLE_EMAILS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhone_number(cursor.getString(2));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        return contactList;

    }

    public int getEmailsCount (){
        String countQuery = "select * from " + TABLE_EMAILS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }

    public int updateEmails (Contact emails){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, emails.getName());
        values.put(KEY_PH_NO, emails.getPhone_number());

        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?", new String[] { String.valueOf(emails.getId())});
    }


    public void deleteEmails (Contact emails){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_EMAILS, KEY_ID + " = ?", new String[] {String.valueOf(emails.getId())});
        db.close();
    }
    }


















