package gpapp.hku.lux;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public class CreditContract {
	
	public static final String authority = "gpapp.hku.lux.creditsave";
	public static final Uri content_u = Uri.parse("content://" + authority);
	
	public static final class Credits implements BaseColumns{
		
	public static final String path = "creditsave";
	public static final Uri CONTENT_URI = Uri.withAppendedPath(CreditContract.content_u, path);
	public static final String CONTENT_DIR_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + authority + path;
	public static final String CONETENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + authority + path;
	public static final String Acc = "acc";
	public static final String cID = "cid";
	public static final String[] PROJECTION_ALL = {_ID,Acc,cID};
	public static final String SORT_ORDER_DEFAUT = Acc + " DESC";
	
	}
}
