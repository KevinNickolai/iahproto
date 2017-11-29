package com.example.kevin.iahproto;

import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * The activity in which we display the list of applications to disable
 */
public class AppListActivity extends ListActivity {

    PackageManager m_pm;
    List<ApplicationInfo> m_blockedApps;
    List<ApplicationInfo> m_allApps;
    List<String> m_allAppNames;
    ListView m_appsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_list);

        m_pm = getPackageManager();

        //the list of installed apps we're going to check for blocking
        m_allApps = m_pm.getInstalledApplications(PackageManager.GET_META_DATA);

        m_allAppNames = new ArrayList<String>();
        m_blockedApps = new ArrayList<ApplicationInfo>();

        for (int i = 0; i < m_allApps.size(); ++i)
        {
            m_allAppNames.add((m_allApps.get(i)).packageName);
        }



        //get our ListView
        m_appsListView = getListView();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,m_allAppNames);
        m_appsListView.setAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // Do something when a list item is clicked

        ApplicationInfo appInfo = m_allApps.get(m_allAppNames.indexOf(m_appsListView.getItemAtPosition(position)));
        //if the blocked apps contains the item we've clicked on, deselect the item clicked

        if(m_blockedApps.contains(appInfo))
        {
            m_blockedApps.remove(appInfo);
        }
        else //blocked apps doesn't contain it, add and mark it.
        {
            m_blockedApps.add(appInfo);
        }
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
