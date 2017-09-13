package cuongnguyen.app.com.mymusic.presenters.PlayOnline;

import java.util.ArrayList;

import td.quang.vnplayer.models.objects.OnlineSong;

/**
 * Created by Quang_TD on 1/14/2017.
 */

public interface OnQueryFinishedListener {

    void onQuerySuccess(ArrayList<OnlineSong> onlineSongs);
    void onQueryFail();
}
