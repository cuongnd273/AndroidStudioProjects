package cuongnguyen.app.com.musicworld.DAO;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.util.ArrayList;

import cuongnguyen.app.com.musicworld.model.Album;
import cuongnguyen.app.com.musicworld.model.Artist;
import cuongnguyen.app.com.musicworld.model.Song;

import static android.R.id.list;

/**
 * Created by CuongNguyen on 4/6/2017.
 */

public class GetMusicDAO {
    private ContentResolver resolver;
    private Context context;

    public GetMusicDAO(Context context) {
        this.context = context;
        this.resolver=context.getContentResolver();
    }
    public ArrayList<Song> getAllSong(){
        ArrayList<Song> list=new ArrayList<>();
        Uri uri=MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor cursor=this.resolver.query(uri,null,MediaStore.Audio.Media.IS_MUSIC+"!=0",null,null);
        int indexKey=cursor.getColumnIndex(MediaStore.Audio.Media.TITLE_KEY);
        int indexTitle=cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
        int indexPath=cursor.getColumnIndex(MediaStore.Audio.Media.DATA);
        while(cursor.moveToNext())
        {
            Song song=new Song(cursor.getString(indexKey),cursor.getString(indexTitle),cursor.getString(indexPath));
            list.add(song);
        }
        return list;
    }
    public ArrayList<Album> getAllAlbum(){
        ArrayList<Album> list=new ArrayList<>();
        Uri uri=MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI;
        Cursor cursor=this.resolver.query(uri,null,null,null,null);
        int indexTitle=cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM);
        int indexKey=cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM_KEY);
        int indexPath=cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ART);
        while(cursor.moveToNext()){
            Album album=new Album(cursor.getString(indexKey),cursor.getString(indexTitle),cursor.getString(indexPath));
            list.add(album);
        }
        return list;
    }
    public ArrayList<Artist> getAllArtist(){
        ArrayList<Artist> list=new ArrayList<>();
        Uri uri=MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI;
        Cursor cursor=this.resolver.query(uri,null,null,null,null);
        int indexTitle=cursor.getColumnIndex(MediaStore.Audio.Artists.ARTIST);
        int indexKey=cursor.getColumnIndex(MediaStore.Audio.Artists.ARTIST_KEY);
        while(cursor.moveToNext()){
            Artist artist=new Artist(cursor.getString(indexKey),cursor.getString(indexTitle));
            list.add(artist);
        }
        return list;
    }
    public ArrayList<Song> getSongOfAlbum(String albumKey){
        ArrayList<Song> list=new ArrayList<>();
        Uri uri=MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor cursor=this.resolver.query(uri,null,MediaStore.Audio.Media.IS_MUSIC+"!=0 and "+MediaStore.Audio.Media.ALBUM_KEY+"=\""+albumKey+"\"",null,null);
        int indexKey=cursor.getColumnIndex(MediaStore.Audio.Media.TITLE_KEY);
        int indexTitle=cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
        int indexPath=cursor.getColumnIndex(MediaStore.Audio.Media.DATA);
        while(cursor.moveToNext())
        {
            Song song=new Song(cursor.getString(indexKey),cursor.getString(indexTitle),cursor.getString(indexPath));
            list.add(song);
        }
        return list;
    }
    public ArrayList<Song> getSongOfArtist(String artistKey){
        ArrayList<Song> list=new ArrayList<>();
        Uri uri=MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor cursor=this.resolver.query(uri,null,MediaStore.Audio.Media.IS_MUSIC+"!=0 and "+MediaStore.Audio.Media.ARTIST_KEY+"=\""+artistKey+"\"",null,null);
        int indexKey=cursor.getColumnIndex(MediaStore.Audio.Media.TITLE_KEY);
        int indexTitle=cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
        int indexPath=cursor.getColumnIndex(MediaStore.Audio.Media.DATA);
        while(cursor.moveToNext())
        {
            Song song=new Song(cursor.getString(indexKey),cursor.getString(indexTitle),cursor.getString(indexPath));
            list.add(song);
        }
        return list;
    }
    public Song getSong(String songKey){
        Uri uri=MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor cursor=this.resolver.query(uri,null,MediaStore.Audio.Media.IS_MUSIC+"!=0 and "+MediaStore.Audio.Media.TITLE_KEY+"=\""+songKey+"\"",null,null);
        int indexTitle=cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
        int indexAlbum=cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM);
        int indexArtist=cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
        int indexPath=cursor.getColumnIndex(MediaStore.Audio.Media.DATA);
        cursor.moveToFirst();
        Song song=new Song(cursor.getString(indexTitle),cursor.getString(indexAlbum),cursor.getString(indexArtist),cursor.getString(indexPath));
        return song;
    }
}
