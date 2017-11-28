package com.example.kevin.iahproto;

import android.app.ListActivity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
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

        ArrayAdapter<ApplicationInfo> adapter = new ArrayAdapter<ApplicationInfo>(this, android.R.layout.simple_list_item_1,allApps);
        m_appsList.setAdapter(adapter);
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
