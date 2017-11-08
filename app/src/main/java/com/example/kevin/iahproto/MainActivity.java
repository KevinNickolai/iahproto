package com.example.kevin.iahproto;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.ComponentName;


import java.util.List;

public class MainActivity extends AppCompatActivity {

    PackageManager m_pm;
    List<ApplicationInfo> m_blockedApps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        m_pm = getPackageManager();
        List<ApplicationInfo> allApps = m_pm.getInstalledApplications(0);

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
                //the name of the component we're blocking
                ComponentName cn = new ComponentName(m_blockedApps.get(i).packageName,m_blockedApps.get(i).className);
                m_pm.setComponentEnabledSetting(cn,
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
            ComponentName cn = new ComponentName((m_blockedApps.get(i).packageName,m_blockedApps.get(i).className));
            m_pm.setComponentEnabledSetting(cn,
                    PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                    PackageManager.DONT_KILL_APP);
        }
    }
}