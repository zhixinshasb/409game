package com.game.demo409;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import com.example.residemenutest.R;
import com.special.ResideMenu.ResideMenu;


public class HomeFragment extends Fragment {
	private View parentView;
	private ResideMenu resideMenu;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		parentView = inflater.inflate(R.layout.home, container,false);
		setUpViews();
		return parentView;
	}
	private void setUpViews() {
		MainActivity parentActivity = (MainActivity)getActivity();
		resideMenu = parentActivity.getResideMenu();
		
	}
}
