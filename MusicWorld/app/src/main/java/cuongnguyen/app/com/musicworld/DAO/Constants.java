package cuongnguyen.app.com.musicworld.DAO;

/**
 * Created by CuongNguyen on 4/11/2017.
 */

public class Constants {
    public interface ACTION_NOTIFICATION{
        public static String SERVICE_NAME="cuongnguyen.app.com.musicworld.services.ServiceMusic";
        public static String STARTFOREGROUND_START="cuongnguyen.app.com.musicworld.startmusic";
        public static String PREV="cuongnguyen.app.com.musicworld.prev_notification";
        public static String PLAY="cuongnguyen.app.com.musicworld.play_notification";
        public static String NEXT="cuongnguyen.app.com.musicworld.next_notification";
        public static String CLOSE="cuongnguyen.app.com.musicworld.close_notification";
        public static String DESTROY="cuongnguyen.app.com.musicworld.destroy_notification";
    }
    public interface ACTION{
        public static String PLAY="cuongnguyen.app.com.musicworld.play";
        public static String UPDATE_TIME="cuongnguyen.app.com.musicworld.updatetime";
        public static String UPDATE_UI="cuongnguyen.app.com.musicworld.updateui";
    }
}
