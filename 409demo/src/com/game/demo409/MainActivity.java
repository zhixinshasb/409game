package com.game.demo409;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.text.format.Time;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.residemenutest.R;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

public class MainActivity extends FragmentActivity implements OnClickListener{
	private ResideMenu resideMenu;
	private MainActivity mContext;
	
	private ResideMenuItem mHome;
	private ResideMenuItem mSetting;
	private ResideMenuItem mExit;
	
	private ResideMenuItem mNavigation;
	private ResideMenuItem mAround;
	private ResideMenuItem mQuetions;
	
	private Time nowTime;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		  this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
	                WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);
		nowTime = new Time();
		nowTime.setToNow();  
		System.out.println(nowTime.hour);
		mContext = this;
		setUpMenu();
		changeFragment(new HomeFragment());
	}
	public void setUpMenu() {
		resideMenu = new ResideMenu(this);
		resideMenu.setBackground(R.drawable.background);
		resideMenu.setUserId("FZU-泡面Eric");
		resideMenu.setGreetTime(nowTime);
        resideMenu.setUserLevel(7);
        resideMenu.setUserGoald(678);
		resideMenu.attachToActivity(this);
		resideMenu.setDirectionDisable(resideMenu.DIRECTION_RIGHT);
		resideMenu.setMenuListener(menuListener);
		
		mHome = new ResideMenuItem(this, R.drawable.home, "地图");
		mNavigation = new ResideMenuItem(this, R.drawable.navigation, "校内导航");
		mAround = new ResideMenuItem(this,R.drawable.around,"周边");
		mQuetions = new ResideMenuItem(this, R.drawable.quetion,"问题");
		
		mHome.setOnClickListener(this);
		mNavigation.setOnClickListener(this);
		mAround.setOnClickListener(this);
		mQuetions.setOnClickListener(this);
		
		resideMenu.addMenuItem(mHome, ResideMenu.DIRECTION_LEFT);
		resideMenu.addMenuItem(mNavigation,ResideMenu.DIRECTION_LEFT);
		resideMenu.addMenuItem(mAround,ResideMenu.DIRECTION_LEFT);
		resideMenu.addMenuItem(mQuetions,ResideMenu.DIRECTION_LEFT);
		this.findViewById(R.id.left_button).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
				
			}
		});
	
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public void onClick(View v) {
		if(v==mHome) {
			changeFragment(new HomeFragment());
		}else if(v==mSetting) {
			changeFragment(new SetFragment());
		}else if(v==mNavigation) {
			changeFragment(new NavigationFragment());
		}else if(v==mAround) {
			changeFragment(new AroundFragment());
		}else if(v==mQuetions) {
			changeFragment(new QuetionsFragment());
		}
		
		 resideMenu.closeMenu();
	}
	 private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
	        @Override
	        public void openMenu() {
	            Toast.makeText(mContext, "Menu is opened!", Toast.LENGTH_SHORT).show();
	        }

	        @Override
	        public void closeMenu() {
	            Toast.makeText(mContext, "Menu is closed!", Toast.LENGTH_SHORT).show();
	        }
	};
	private void changeFragment(Fragment targetFragment) {
		 resideMenu.clearIgnoredViewList();
	        getSupportFragmentManager()
	                .beginTransaction()
	                .replace(R.id.main_fragment, targetFragment, "fragment")
	                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
	                .commit();
	}
    public ResideMenu getResideMenu(){
        return resideMenu;
    }
   
}
