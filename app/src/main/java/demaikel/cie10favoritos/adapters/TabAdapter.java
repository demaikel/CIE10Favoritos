package demaikel.cie10favoritos.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import demaikel.cie10favoritos.R;
import demaikel.cie10favoritos.views.main.cie10List.Cie10ListFragment;
import demaikel.cie10favoritos.views.main.cie10List.FavoriteFragment;

/**
 * Created by Michael on 28-11-2016.
 */

public class TabAdapter extends FragmentPagerAdapter{
    private Context context;


    public TabAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    public TabAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 :
                return Cie10ListFragment.newInstance();
            case 1 :
                return FavoriteFragment.newInstance();
        }
        return null;
    }


    @Override
    public int getCount() {
        return 2 ;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        //This is why we need the context, so we can get the string from the strings.xml file
        switch (position) {
            case 0:
                return context.getString(R.string.cie10_List);
            case 1:
                return context.getString(R.string.favorites);
            default:
                return context.getString(R.string.cie10_List);
        }
    }
}
