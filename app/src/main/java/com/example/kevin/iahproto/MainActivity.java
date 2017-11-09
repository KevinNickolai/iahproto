package com.example.kevin.iahproto;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.ComponentName;
import android.widget.ExpandableListView;


import java.util.List;

public class MainActivity extends AppCompatActivity {

    PackageManager m_pm;
    List<ApplicationInfo> m_blockedApps;
    ExpandableListView m_appsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get the pm
        m_pm = getPackageManager();
        List<ApplicationInfo> allApps = m_pm.getInstalledApplications(0);

        m_appsList = findViewById(R.id.expandListViewAppsList);

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