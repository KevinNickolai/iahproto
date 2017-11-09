package com.example.kevin.iahproto;

import android.app.ListActivity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.List;

/**
 * The activity in which we display the list of applications to disable
 */
public class AppListActivity extends ListActivity {

    PackageManager m_pm;
    List<ApplicationInfo> m_blockedApps;
    ListView m_appsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_list);

        m_pm = getPackageManager();

        //the list of installed apps we're going to check for blocking
        List<ApplicationInfo> allApps = m_pm.getInstalledApplications(0);

        //get our ListView
        m_appsList = findViewById(R.id.listViewAppList);

        
        m_appsList.setAdapter(new ListAdapter() {
            @Override
            public boolean areAllItemsEnabled() {
                return false;
            }

            @Override
            public boolean isEnabled(int i) {
                return false;
            }

            @Override
            public void registerDataSetObserver(DataSetObserver dataSetObserver) {

            }

            @Override
            public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

            }

            @Override
            public int getCount() {
                return 0;
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @Override
            public boolean hasStableIds() {
                return false;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                return null;
            }

            @Override
            public int getItemViewType(int i) {
                return 0;
            }

            @Override
            public int getViewTypeCount() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }
        });
        for(int i = 0; i < allApps.size();++i)
        {
            //display application name in selection list of apps to block

        }
    }

    public void onOkPressed(View view)
    {
        //iterate through selection list of apps

        //m_blockedApps.add(app);
    }

    public void onStartAssignment(View view)
    {
        //iterate through the blocked apps and disable each icon(?)
        for(int i = 0; i < m_blockedApps.size();++i)
        {
            try{
                //freeze all blocked applications.
                m_pm.setApplicationEnabledSetting(m_blockedApps.get(i).packageName,
                        PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                        PackageManager.DONT_KILL_APP);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public void onEndAssignment(View view)
    {
        for(int i = 0; i < m_blockedApps.size(); ++i)
        {
            m_pm.setApplicationEnabledSetting(m_blockedApps.get(i).packageName,
                    PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                    PackageManager.DONT_KILL_APP);
        }
    }
}
